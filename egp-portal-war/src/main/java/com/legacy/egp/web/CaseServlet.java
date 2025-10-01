```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Case Management Controller
 * Handles case management related requests.
 */
@RestController
@RequestMapping("/cases")
public class CaseServlet {

    private static final Logger logger = LoggerFactory.getLogger(CaseServlet.class);

    @GetMapping
    public void getCases(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Logic to fetch cases
            response.getWriter().write("Cases Data");
        } else {
            logger.warn("Unauthorized access attempt to /cases");
            response.sendRedirect("/login");
        }
    }

    @PostMapping
    public void createCase(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Logic to create a new case
            response.getWriter().write("Case Created");
        } else {
            logger.warn("Unauthorized access attempt to create a case");
            response.sendRedirect("/login");
        }
    }
}
```