package ru.itis.filters;

import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/student/*")
public class StudentFilter implements Filter {
    StudentRepositoryJdbc studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute("role") != null && session.getAttribute("role").equals("student")) {
            Student student = studentRepository.findStudentByEmail((String) session.getAttribute("email"));
            if (student != null) {
                request.setAttribute("className", student.getClassName());
                request.setAttribute("studentId", student.getId());
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/java_semestWork_1");
            }
        } else {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/java_semestWork_1");
        }
    }

    @Override
    public void destroy() {

    }
}
