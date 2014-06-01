<%--
  User: proshad
  Date: 5/29/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Employee</title>
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
                var formObj = $("#frmAddEmployee");
                var formURL = formObj.attr("action");
                $.ajax({
                    type: "POST",
                    url: formURL,
                    dataType: 'json',
                    data: formObj.serialize()
                }).done(function (data) {
                            alert(data.message);
                            $("#firstName").val("");
                            $("#lastName").val("");
                            $("#designation").val("");
                            $("#address").val("");
                            $("#contactNo").val("");
                            $("#imageUrl").val("");
                            $("#joiningDate").val("");
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
        <h3>Add Employee</h3>

        <div class="form-container">
            <form id="frmAddEmployee" action="../employee/saveOrUpdate" method="POST">

                <div class="field-group collection-field-group advanced-field-group">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label for="firstName">First Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="firstName" id="firstName" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="lastName">Last Name:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="lastName" id="lastName" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="designation">Designation:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="designation" id="designation" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="address">Address:</label>
                            </div>
                            <div class="field">
                                <textarea name="address" id="address" class="form-control"
                                          rows="5"></textarea>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="contactNo">Contact Number:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="contactNo" id="contactNo" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="field-container">
                            <div class="label">
                                <label for="imageUrl">Image Url:</label>
                            </div>
                            <div class="field">
                                <input type="text" name="imageUrl" id="imageUrl" class="form-control"/>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <%--<div class="field-container">--%>
                            <%--<div class="label">--%>
                                <%--<label for="joiningDate">Joining Date:</label>--%>
                            <%--</div>--%>
                            <%--<div class="field">--%>
                                <%--<input type="text" name="joiningDate" id="joiningDate" class="form-control"/>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        <%--</div>--%>
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