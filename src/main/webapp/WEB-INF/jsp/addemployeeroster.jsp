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
    <title>Assign Employee to Roster</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/grid.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.css'/>"/>

    <script type="text/javascript" src="<c:url value='/resources/js/jquery-latest.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            // populate rosters
            var rosters = '${rosters}';
            var arrRosters = jQuery.parseJSON(rosters);
            for (var i = 0; i < arrRosters.length; i++) {
                var roster = arrRosters[i];
                var option = $('<option/>');
                option.attr({ 'value': roster.ID }).text(roster.name);
                $('#roster').append(option);
            }

            // populate employee
            var employees = '${employees}';
            var arrEmployees = jQuery.parseJSON(employees);
            for (var i = 0; i < arrEmployees.length; i++) {
                var employee = arrEmployees[i];
                var option = $('<option/>');
                option.attr({ 'value': employee.ID }).text(employee.name);
                $('#employee').append(option);
            }


            $('input#btnSubmit').click(function (event) {
                event.preventDefault();
                var rosterID = $('#roster').val();
                var employeeID = $('#employee').val();
                if(rosterID=="-1" || employeeID=="-1"){
                    alert("please select valid data");
                    return;
                }
                $('#rosterID').val(rosterID);
                $('#employeeID').val(employeeID);
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
        <h3>Assign employee to roster</h3>

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
                <span class="label">Select Roster</span>
            </p>
            <select class="catDropDown form-control" name="roster" id="roster">
                <option value="-1" selected="selected">Select</option>
            </select>
        </div>

        <div class="form-container">
            <form id="frmEmployeeRoster" action="../employee/assignRoster" method="POST">
                <div class="field-group collection-field-group" id="divService">
                    <div class="collection-field-group-container">
                        <div class="field-container">
                            <div class="label">
                                <label></label>
                                <input type="hidden" id="rosterID" name="rosterID"/>
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