package ru.itis.servlet.admin.student;


import ru.itis.controls.StudentRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;

    public DeleteStudentServlet() {
        this.studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("id");

        if (studentIdStr != null) {
            try {
                long studentId = Long.parseLong(studentIdStr);
                boolean deleted = studentRepository.deleteById(studentId);

                if (deleted) {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");

                } else {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");

                }
            } catch (NumberFormatException | IOException e) {

                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
        }
    }
}
