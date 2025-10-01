```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - Audit Log</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Governance Portal - Audit Log</h1>
    </div>
    <div class="content">
        <c:choose>
            <c:when test="${not empty auditLogs}">
                <table>
                    <thead>
                        <tr>
                            <th>Timestamp</th>
                            <th>User</th>
                            <th>Action</th>
                            <th>Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${auditLogs}">
                            <tr>
                                <td><c:out value="${log.timestamp}"/></td>
                                <td><c:out value="${log.user}"/></td>
                                <td><c:out value="${log.action}"/></td>
                                <td><c:out value="${log.details}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No audit logs available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
```