<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - Case Notes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header class="header">
        <h1>Enterprise Government Portal - Case Notes</h1>
    </header>
    <main>
        <section class="notes">
            <c:choose>
                <c:when test="${empty notes}">
                    <p>No notes available</p>
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="note" items="${notes}">
                            <li>${note}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </section>
    </main>
    <footer class="footer">
        <p>&copy; 2023 Enterprise Government Portal</p>
    </footer>
</body>
</html>