<%--
  User: Proshad
  Date: 1/17/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Service Image</title>
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

                }
            });

            $("#service").change(function () {
                var serviceID = $('#service').val();
                if (serviceID > 0) {
                 $("#productID").val(serviceID);
                }
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

    </script>
</head>
<body class="form-page" data-type="service">

<div id="body">
    <c:if test="${fn:length(messages) > 0}">
        <ul>
            <c:forEach items="${messages}" var="msg">
                <li><c:out value="${msg}" /></li>
            </c:forEach>
        </ul>
    </c:if>
    <div class="container">
        <h3>Add Service Image</h3>

        <div class="row">
            <p class="category">
                <span class="label">Select Category of the service</span>
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
            <form:form enctype="multipart/form-data" modelAttribute="uploadItem" method="post" action="../image/upload">
                <form:label for="fileData" path="fileData">File:</form:label>
                <form:input path="fileData" type="file" />
                <input type="hidden" name="productID" id="productID"/>
                <input type="submit" value="Upload" />
            </form:form>
        </div>
    </div>
</div>
</body>
</html>