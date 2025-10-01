```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Authentication Filter
 * Implements session-based authentication for the EGP portal.
 * 
 * @version 2.0
 * @since 2023
 */
@Component
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static final List<String> EXCLUDED_URLS = List.of("/login", "/register");

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("AuthFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String requestURI = httpRequest.getRequestURI();
        boolean isExcluded = EXCLUDED_URLS.stream().anyMatch(requestURI::contains);

        if (isExcluded || (session != null && session.getAttribute("user") != null)) {
            chain.doFilter(request, response);
        } else {
            logger.warn("Unauthorized access attempt to {}", requestURI);
            httpResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        logger.info("AuthFilter destroyed");
    }
}
```