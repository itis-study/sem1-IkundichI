package ru.itis.servlet.admin.teacher;

import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Teacher;
import ru.itis.service.HashService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@WebServlet("/admin/updateTeacher")
public class UpdateTeacherServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;
    private HashService hashService = new HashService();

    public UpdateTeacherServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdStr = request.getParameter("id");

        if (teacherIdStr != null) {
            try {
                long teacherId = Long.parseLong(teacherIdStr);
                Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

                if (teacherOptional.isPresent()) {
                    Teacher teacher = teacherOptional.get();
                    request.setAttribute("teacher", teacher);
                    request.getRequestDispatcher("/WEB-INF/Teacher/updateTeacher.jsp").forward(request, response);
                } else {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdStr = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String mail = request.getParameter("mail");
        String infoOfEducation = request.getParameter("infoOfEducation");
        String password;
        try {
            password = hashService.hashPassword(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (teacherIdStr != null) {
            try {
                long teacherId = Long.parseLong(teacherIdStr);
                Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

                if (teacherOptional.isPresent()) {
                    Teacher updatedTeacher = teacherOptional.get();
                    updatedTeacher.setName(name);
                    updatedTeacher.setSurname(surname);
                    updatedTeacher.setMail(mail);
                    updatedTeacher.setInfoOfEducation(infoOfEducation);
                    updatedTeacher.setPassword(password);

                    teacherRepository.updateTeacherData(teacherId, updatedTeacher.getName(), updatedTeacher.getSurname(), updatedTeacher.getMail(), updatedTeacher.getInfoOfEducation(), updatedTeacher.getPassword());

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getTeacher");
        }
    }
}
