<%--
  User: proshad
  Time: 10:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        function init() {
            var categories = '${categories}';
            var arrCategories = jQuery.parseJSON(categories);
            for (var i = 0; i < arrCategories.length; i++) {
                var category = arrCategories[i];
                var option = $('<option/>');
                option.attr({ 'value': category.catID }).text(category.name);
                $('#categoryID').append(option);
            }
        }
    </script>
</head>
<body class="form-page" data-type="service" onload="init()">
<div id="body">
    <div class="container">
        <h3>Add Service</h3>

        <div class="form-container">
            <form id="frmAddCategory" action="../service/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group advanced-field-group">
                    <div class="collection-field-group-container">
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
                                <input type="text" name="noOfTimeSlot" id="noOfTimeSlot"  maxlength="4" size="4"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="categoryID">Category:</label>
                            </div>
                            <div class="field period-field">
                                <select class="catDropDown form-control" name="categoryID" id="categoryID">
                                    <option value="-1" selected="selected">Select</option>
                                </select>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="field-container">
                            <div class="label">
                                <label></label>
                            </div>
                            <div class="field">
                                <input type="submit" value="Submit"/>
                            </div>
                        </div>

                    </div>

                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>