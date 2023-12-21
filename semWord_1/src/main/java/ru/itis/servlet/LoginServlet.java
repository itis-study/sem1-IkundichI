package ru.itis.servlet;

import lombok.SneakyThrows;
import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Student;
import ru.itis.models.Teacher;
import ru.itis.service.HashService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private TeacherRepositoryJdbc teacherRepository;
    private StudentRepositoryJdbc studentRepository;
    HashService hashService = new HashService();


    @Override
    public void init() throws ServletException {
        teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String auth = authenticateUser(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("role", auth);
        session.setAttribute("email", email);
        if (auth.equals("student")) {
            Student student = studentRepository.findStudentByEmailAndPassword(email, hashService.hashPassword(password));
            session.setAttribute("className", student.getClassName());
        }
        if (auth.equals("teacher")) {
            Teacher teacher = teacherRepository.findPersonByEmailAndPassword(email, hashService.hashPassword(password));

        }
        String destinationPage = auth.equals("error") ? "WEB-INF/login.jsp" : "index.jsp";
        request.getRequestDispatcher(destinationPage).forward(request, response);
    }

    private String authenticateUser(String username, String password) throws NoSuchAlgorithmException {

        String hashPassword = hashService.hashPassword(password);
        if (teacherRepository.findPersonByEmailAndPassword(username, hashPassword) != null) {
            Teacher teacher = teacherRepository.findPersonByEmailAndPassword(username, hashPassword);
            if ("admin".equals(teacher.getInfoOfEducation())) {
                return "admin";
            }
            return "teacher";
        }
        if (studentRepository.findStudentByEmailAndPassword(username, hashPassword) != null) {
            return "student";
        }
        return "error";
    }
}
