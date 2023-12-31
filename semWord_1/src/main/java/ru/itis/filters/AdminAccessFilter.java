package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String userRole = (String) request.getSession().getAttribute("role");

        if (userRole != null && userRole.equals("admin")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1");

        }
    }

    @Override
    public void destroy() {
    }
}
