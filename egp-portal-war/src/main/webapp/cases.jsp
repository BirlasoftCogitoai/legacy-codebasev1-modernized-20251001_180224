```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EGP Portal - Cases</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Cases</h1>
    </div>
    <div class="content">
        <c:if test="${not empty cases}">
            <ul>
                <c:forEach var="case" items="${cases}">
                    <li>
                        <a href="caseDetails.jsp?id=${case.id}">${case.title}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty cases}">
            <p>No cases available.</p>
        </c:if>
    </div>
</body>
</html>
```