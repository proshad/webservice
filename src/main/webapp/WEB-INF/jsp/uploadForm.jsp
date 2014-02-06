<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${fn:length(errorMsgs) > 0}">
    <ul>
        <c:forEach items="${errorMsgs}" var="error">
            <li><c:out value="${error}" /></li>
        </c:forEach>
    </ul>
</c:if>

<form:form enctype="multipart/form-data" modelAttribute="uploadItem" method="post" action="../image/upload">
    <form:label for="fileData" path="fileData">File:</form:label>
    <form:input path="fileData" type="file" />
    <input type="submit" value="Upload" />
</form:form>