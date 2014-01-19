<%--
  User: Proshad
  Date: 1/17/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#divService").hide();

            $("#category").change(function () {
                $('#service option[value!="-1"]').remove();
                var catID = $('#category').val();
                if (catID > 0) {
                    var serviceUrl = "../category/getAllSubCategoriesAndServices?categoryID=" + catID;
                    $.ajax({
                        url: serviceUrl,
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            var arrService = data.result.services;
                            if(arrService!=undefined){
                            for (var i = 0; i < arrService.length; i++) {
                                var service = arrService[i];
                                var option = $('<option/>');
                                option.attr({ 'value': service.serviceID }).text(service.name);
                                $('#service').append(option);
                            }
                            }
                        },
                        error: function (xhr, error) {
                            console.log(error);
                        }
                    });

                } else {
                    $("#divService").hide();
                }
            });

            $("#service").change(function () {
                var serviceID = $('#service').val();
                if (serviceID > 0) {
                    var formURL = "../service/getDetailOfAService?serviceID=" + serviceID;
                    $.ajax({
                        url: formURL,
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            data = data.result;
                            $("#divService").show();
                            $("#categoryID").val(data.parentID);
                            $("#productID").val(data.serviceID);
                            $("#productName").val(data.name);
                            $("#productDescription").val(data.description);
                            $("#productNote").val(data.notes);
                            $("#noOfTimeSlot").val(data.noOfSlot);
                        },
                        error: function (xhr, error) {
                            console.log(error);
                        }
                    });
                } else {
                    $("#divService").hide();
                }
            });

            $('input#btnEdit').click(function (event) {
                event.preventDefault();
                submitForm(0);  // 0 is for edit
            });
            $('input#btnDelete').click(function (event) {
                event.preventDefault();
                submitForm(1);  // 1 is for delete
            });

            var categories = '${categories}';
            var arrCategories = jQuery.parseJSON(categories);
            for (var i = 0; i < arrCategories.length; i++) {
                var category = arrCategories[i];
                var option = $('<option/>');
                option.attr({ 'value': category.catID }).text(category.name);
                $('#category').append(option);
            }
        });

        function submitForm(type) {
            var formObj = $("#frmService");
            var formURL = formObj.attr("action");
            var catID = $('#category').val();
            var serviceID = $('#service').val();

            if (catID > 0 && serviceID > 0) {
                if (type == 1) {  //delete
                    formURL = formURL.replace("saveOrUpdate", "delete/");
                    formURL += serviceID;
                }
                $.ajax({
                    type: "POST",
                    url: formURL,
                    dataType: 'json',
                    data: formObj.serialize()
                }).done(function (data) {
                            alert(data.message);
                            location.reload();
                        })
                        .fail(function (data) {
                            alert("SORRY, Internal server error!");
                        });

            } else {
                alert("Please select correct category to edit or delete");
            }

        }
    </script>
</head>
<body class="form-page" data-type="service">
<div id="body">
    <div class="container">
        <h3>Edit Category</h3>

        <div class="row">
            <p class="category">
                <span class="label">Select Category to Edit</span>
            </p>
            <select class="catDropDown form-control" name="category" id="category">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
        <div class="row">
            <p class="service">
                <span class="label">Select Service to Edit</span>
            </p>
            <select class="serviceDropDown form-control" name="service" id="service">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
        <div class="form-container">
            <form id="frmService" action="../service/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group" id="divService">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryID">Category ID:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="categoryID" id="categoryID" readonly="true"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="productID">Service ID:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="productID" id="productID" readonly="true"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="productName">Service Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="productName" id="productName" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="productDescription">Service Description:</label>
                            </div>
                            <div class="field">
                                <textarea name="productDescription" id="productDescription" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="productNote">Service Note:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="productNote" id="productNote" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="noOfTimeSlot">No of Time Slot:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="noOfTimeSlot" id="noOfTimeSlot" maxlength="4" size="4"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="field-container">
                            <div class="label">
                                <label></label>
                            </div>
                            <div class="buttons">
                                <input type="button" name="edit" id="btnEdit" class="button edit" value="Edit"/>
                                <input type="button" name="delete" id="btnDelete" class="button delete" value="Delete"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </div>

                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>