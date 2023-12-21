package ru.itis.filters;
import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/teacher/*")
public class TeacherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        TeacherRepositoryJdbc studentRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        if (session.getAttribute("role") != null && (session.getAttribute("role").equals("teacher") || session.getAttribute("role").equals("admin"))) {
            Teacher teacher = studentRepository.findPersonByEmail((String) session.getAttribute("email"));
            if (teacher != null) {
                request.setAttribute("teacherId", teacher.getId());
                request.setAttribute("info", teacher.getInfoOfEducation());
            }

            filterChain.doFilter(request, servletResponse);
        }
        else {
            ((HttpServletResponse) servletResponse).sendRedirect("http://localhost:8080/java_semestWork_1");
        }
    }

    @Override
    public void destroy() {

    }
}
