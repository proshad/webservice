<%--
  User: proshad
  Date: 5/29/14
  Time: 10:16 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add rate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            $('input#btnSubmit').click(function (event) {
                event.preventDefault();
                var formObj = $("#frmAddRate");
                var formURL = formObj.attr("action");
                $.ajax({
                    type: "POST",
                    url: formURL,
                    dataType: 'json',
                    data: formObj.serialize()
                }).done(function (data) {
                            alert(data.message);
                            $("#rateName").val("");
                            $("#rateDescription").val("");
                            $("#price").val("");
                            location.reload();
                        })
                        .fail(function(data) {
                            alert("SORRY, Internal server error!");
                        });
            });
        });
    </script>
</head>
<body class="form-page" data-type="employee">
<div id="body">
    <div class="container">
        <h3>Add Rate</h3>

        <div class="form-container">
            <form id="frmAddRate" action="../rate/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group advanced-field-group">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label for="rateName">Rate Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="rateName" id="rateName" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="field-container">
                            <div class="label">
                                <label for="rateDescription">Description:</label>
                            </div>
                            <div class="field">
                                <textarea name="rateDescription" id="rateDescription" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="price">Price:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="price" id="price" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="field-container">
                            <div class="label">
                                <label></label>
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