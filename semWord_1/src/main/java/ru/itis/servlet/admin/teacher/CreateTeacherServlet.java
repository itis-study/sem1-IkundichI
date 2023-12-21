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

@WebServlet("/admin/createTeacher")
public class CreateTeacherServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;
    private final HashService hashService = new HashService();

    public CreateTeacherServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Teacher/createTeacher.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Teacher teacher = Teacher.builder()
                .name(name)
                .surname(surname)
                .mail(mail)
                .infoOfEducation(infoOfEducation)
                .password(password)
                .build();

        teacherRepository.save(teacher);

        response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
    }


}
