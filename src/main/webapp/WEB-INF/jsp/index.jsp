<%--
  User: proshad
  Time: 10:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title></title>
    <script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        function init(){
            var categories = '${categories}';
            alert("categories: "+categories);
        }
    </script>
</head>
<body onload="init()">
       asdfds
</body>
</html>