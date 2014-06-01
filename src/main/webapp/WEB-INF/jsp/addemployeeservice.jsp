<%--
  User: proshad
  Date: 5/29/14
  Time: 11:19 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Assign Employee to Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
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

                }
            });


            // populate employee
            var employees = '${employees}';
            var arrEmployees = jQuery.parseJSON(employees);
            for (var i = 0; i < arrEmployees.length; i++) {
                var employee = arrEmployees[i];
                var option = $('<option/>');
                option.attr({ 'value': employee.ID }).text(employee.name);
                $('#employee').append(option);
            }

            // populate category
            var categories = '${categories}';
            var arrCategories = jQuery.parseJSON(categories);
            for (var i = 0; i < arrCategories.length; i++) {
                var category = arrCategories[i];
                var option = $('<option/>');
                option.attr({ 'value': category.catID }).text(category.name);
                $('#category').append(option);
            }

            $('input#btnSubmit').click(function (event) {
                event.preventDefault();
                var productID = $('#service').val();
                var employeeID = $('#employee').val();
                $('#productID').val(productID);
                $('#employeeID').val(employeeID);
                if(productID=="-1" || employeeID=="-1"){
                    alert("please select valid data");
                    return;
                }
                var formObj = $("#frmEmployeeService");
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

        });


    </script>
</head>
<body class="form-page" data-type="service">
<div id="body">
    <div class="container">
        <h3>Assign employee to service</h3>

        <div class="row">
            <p class="employee">
                <span class="label">Select Employee</span>
            </p>
            <select class="catDropDown form-control" name="employee" id="employee">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>
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
        <div class="form-container">
            <form id="frmEmployeeService" action="../employee/assignService" method="POST">
                <div class="field-group collection-field-group" id="divService">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label></label>
                                <input type="hidden" id="productID" name="productID"/>
                                <input type="hidden" id="employeeID" name="employeeID"/>
                            </div>
                            <div class="buttons">
                                <input type="button" name="submit" id="btnSubmit" class="button submit" value="Submit"/>
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