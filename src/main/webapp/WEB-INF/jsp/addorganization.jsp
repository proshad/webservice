<%--
  User: proshad
  Date: 6/1/14
  Time: 1:38 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Organization</title>
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
                var formObj = $("#frmAddOrganization");
                var formURL = formObj.attr("action");
                $.ajax({
                    type: "POST",
                    url: formURL,
                    dataType: 'json',
                    data: formObj.serialize()
                }).done(function (data) {
                            alert(data.message);
                            $("#name").val("");
                            $("#description").val("");
                            $("#contactDetails").val("");
                            $("#location").val("");
                            $("#tradeLicense").val("");
                            $("#tradingHour").val("");
                            $("#timeSlotDuration").val("");
                            location.reload();
                        })
                        .fail(function(data) {
                            alert("SORRY, Internal server error!");
                        });
            });
        });
    </script>
</head>
<body class="form-page" data-type="user">
<div id="body">
    <div class="container">
        <h3>Add Organization</h3>

        <div class="form-container">
            <form id="frmAddOrganization" action="../organization/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group advanced-field-group">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label for="name">Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="name" id="name" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="description">Description:</label>
                            </div>
                            <div class="field">
                                <textarea name="description" id="description" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="contactDetails">Contact Details:</label>
                            </div>
                            <div class="field">
                                <textarea name="contactDetails" id="contactDetails" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="location">Location:</label>
                            </div>
                            <div class="field">
                                <textarea name="location" id="location" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="tradeLicense">Trade License:</label>
                            </div>
                            <div class="field">
                                <textarea name="tradeLicense" id="tradeLicense" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="tradingHour">Trading Hour:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="tradingHour" id="tradingHour" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="field-container">
                            <div class="label">
                                <label for="timeSlotDuration">Time Slot Duration:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="timeSlotDuration" id="timeSlotDuration" class="form-control"/>
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