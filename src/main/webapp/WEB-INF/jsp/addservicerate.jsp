<%--
  User: proshad
  Date: 5/29/14
  Time: 10:37 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add rate to service</title>
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
                            if (arrService != undefined) {
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


            $('input#btnSubmit').click(function (event) {
                event.preventDefault();
                var formObj = $("#frmServiceRate");
                var serviceID = $('#service').val();
                var rateId = $('#rate').val();
                $('#rateID').val(rateId);
                $('#productID').val(serviceID);
                if(rateId=="-1"){
                    alert("please select valid data");
                    return;
                }
                var formURL = formObj.attr("action");
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
            });


            var categories = '${categories}';
            var arrCategories = jQuery.parseJSON(categories);
            for (var i = 0; i < arrCategories.length; i++) {
                var category = arrCategories[i];
                var option = $('<option/>');
                option.attr({ 'value': category.catID }).text(category.name);
                $('#category').append(option);
            }

            var rates = '${rates}';
            var arrRates = jQuery.parseJSON(rates);
            for (var i = 0; i < arrRates.length; i++) {
                var rate = arrRates[i];
                var option = $('<option/>');
                option.attr({ 'value': rate.ID }).text(rate.name);
                $('#rate').append(option);
            }
        });


    </script>
</head>
<body class="form-page" data-type="service">
<div id="body">

    <div class="container">
        <h3>Add rate to Service</h3>


        <div class="row">
            <p class="category">
                <span class="label">Select Category</span>
            </p>
            <select class="catDropDown form-control" name="category" id="category">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
        <div class="row">
            <p class="service">
                <span class="label">Select Service</span>
            </p>
            <select class="serviceDropDown form-control" name="service" id="service">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
        <div class="row">
            <p class="rate">
                <span class="label">Select Rate</span>
            </p>
            <select class="serviceDropDown form-control" name="rate" id="rate">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
        <form id="frmServiceRate" action="../rate/productRate" method="POST">
            <div class="field-container">
                <div class="label">
                    <label></label>
                    <input type="hidden" id="rateID" name="rateID"/>
                    <input type="hidden" id="productID" name="productID"/>
                </div>
                <div class="buttons">
                    <input type="button" name="submit" id="btnSubmit" class="button submit" value="Submit"/>
                </div>
                <div class="clearfix"></div>
            </div>
        </form>

    </div>
</div>
</body>
</html>