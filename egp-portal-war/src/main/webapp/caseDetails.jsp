```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EGP Portal - Case Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Case Details</h1>
    </div>
    <div class="content">
        <c:if test="${not empty case}">
            <h2>Case ID: ${case.id}</h2>
            <p><strong>Title:</strong> ${case.title}</p>
            <p><strong>Description:</strong> ${case.description}</p>
            <p><strong>Status:</strong> ${case.status}</p>
        </c:if>
        <c:if test="${empty case}">
            <p>No case details available.</p>
        </c:if>
    </div>
</body>
</html>
```