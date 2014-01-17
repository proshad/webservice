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
    <title>Edit Category</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#divCategory").hide();

            $("#category").change(function () {
                var catID = $('#category').val();
                if (catID > 0) {
                    var formURL = "../category/getDetails/"+catID;
                    $.ajax({
                        url: formURL,
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            $("#divCategory").show();
                            $("#categoryID").val(data.catID);
                            $("#parentCatID").val(data.parentID);
                            $("#categoryName").val(data.name);
                            $("#categoryDescription").val(data.description);
                            $("#categoryNote").val(data.notes);
                        },
                        error: function (xhr, error) {
                            console.log(error);
                        }
                    });
                } else {
                    $("#divCategory").hide();
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
            var formObj = $("#frmCategory");
            var formURL = formObj.attr("action");
            var catID = $('#category').val();

            if (catID > 0) {
                if (type == 1) {  //delete
                    formURL = formURL.replace("saveOrUpdate", "delete/");
                    formURL += $('#category').val();
                }

                formObj.attr("action", formURL);
                formObj.submit();
            } else {
                alert("Please select correct category to edit or delete");
            }

        }
    </script>
</head>
<body class="form-page" data-type="category">
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
        <div class="form-container">
            <form id="frmCategory" action="../category/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group" id="divCategory">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryID">Category ID:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="categoryID" id="categoryID" readonly="true" />
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="parentCatID">Parent Category ID:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="parentCatID" id="parentCatID" readonly="true" />
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryName">Category Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="categoryName" id="categoryName" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryDescription">Category Description:</label>
                            </div>
                            <div class="field">
                                <textarea name="categoryDescription" id="categoryDescription" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryNote">Category Note:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="categoryNote" id="categoryNote" class="form-control"/>
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