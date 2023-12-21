package ru.itis.servlet.admin.teacher;

import ru.itis.controls.TeacherRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;

    public DeleteTeacherServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdStr = request.getParameter("id");

        if (teacherIdStr != null) {
            try {
                long teacherId = Long.parseLong(teacherIdStr);
                boolean deleted = teacherRepository.deleteById(teacherId);

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
