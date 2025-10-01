```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Profile Management Servlet handling user profiles.
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Log user accessing profile
        logger.info("User {} is accessing profile page", session.getAttribute("user"));

        // Forward to profile JSP
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle profile updates
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        // Insert validation and business logic here

        // Log profile update
        logger.info("Profile updated for user: {}", username);

        // Redirect to profile page with success message
        response.sendRedirect("profile?success=true");
    }
}
```