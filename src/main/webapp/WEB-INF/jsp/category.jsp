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
    <title>Category</title>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        function init() {
            var categories = '${categories}';

            var option = $('<option/>');
            option.attr({ 'value': -1 }).text('Select');
            $('#parentCatID').append(option);

            var arrCategories = jQuery.parseJSON(categories);
            for(var i=0; i<arrCategories.length; i++){
                var category = arrCategories[i];
                var option = $('<option/>');
                option.attr({ 'value': category.catID }).text(category.name);
                $('#parentCatID').append(option);


            }



        }
    </script>
</head>
<body onload="init()">
<div id="mainContent">
    <h3>Add Category</h3>

    <form id="frmAddCategory" action="../category/saveOrUpdate" method="POST">
        <div style="clear: both">
            <label>Category Name: </label>
            <input type="text" value="" name="categoryName" id="categoryName"/>

            <label>Category Description: </label>
            <input type="text" value="" name="categoryDescription" id="categoryDescription"/>

            <label>Category Note: </label>
            <input type="text" value="" name="categoryNote" id="categoryNote"/>

            <label>Parent Category: </label>
            <select name="parentCatID" id="parentCatID"/>

            <input type="submit" value="Submit"/>

        </div>
    </form>
</div>
</body>
</html>