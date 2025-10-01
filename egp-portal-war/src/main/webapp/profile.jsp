<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - Profile</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header class="header">
        <h1>Enterprise Government Portal - Profile</h1>
    </header>
    <main>
        <section class="profile">
            <c:choose>
                <c:when test="${empty profile}">
                    <p>No profile information available</p>
                </c:when>
                <c:otherwise>
                    <div>
                        <h2>${profile.name}</h2>
                        <p>${profile.email}</p>
                        <p>${profile.phone}</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </main>
    <footer class="footer">
        <p>&copy; 2023 Enterprise Government Portal</p>
    </footer>
</body>
</html>