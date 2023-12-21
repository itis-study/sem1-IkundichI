package ru.itis.servlet.admin.student;

import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.models.Student;
import ru.itis.service.HashService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/admin/createStudent")
public class CreateStudentServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;
    private final HashService hashService = new HashService();

    public CreateStudentServlet() {
        this.studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Student/createStudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String mail = request.getParameter("mail");
            String password = hashService.hashPassword(request.getParameter("password"));
            String className = request.getParameter("className");
            Student student = Student.builder()
                    .name(name)
                    .surname(surname)
                    .mail(mail)
                    .password(password)
                    .className(className)
                    .build();

            studentRepository.save(student);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
    }

}
