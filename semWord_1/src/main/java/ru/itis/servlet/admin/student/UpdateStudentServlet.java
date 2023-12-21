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
import java.util.Optional;

@WebServlet("/admin/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;
    private final HashService hashService = new HashService();

    public UpdateStudentServlet() {
        this.studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("id");

        if (studentIdStr != null) {
            try {
                long studentId = Long.parseLong(studentIdStr);
                Optional<Student> studentOptional = studentRepository.findById(studentId);

                if (studentOptional.isPresent()) {
                    Student student = studentOptional.get();
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("/WEB-INF/Student/updateStudent.jsp").forward(request, response);
                } else {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
                }
            } catch (NumberFormatException e) {

                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String className = request.getParameter("className");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");


        if (studentIdStr != null) {
            try {
                long studentId = Long.parseLong(studentIdStr);
                Optional<Student> studentOptional = studentRepository.findById(studentId);

                if (studentOptional.isPresent()) {
                    Student updatedStudent = studentOptional.get();
                    updatedStudent.setName(name);
                    updatedStudent.setSurname(surname);
                    updatedStudent.setClassName(className);
                    updatedStudent.setMail(mail);
                    updatedStudent.setPassword(hashService.hashPassword(password));

                    studentRepository.updateStudentData(studentId, updatedStudent.getName(), updatedStudent.getSurname(), updatedStudent.getClassName(), updatedStudent.getMail(), updatedStudent.getPassword());

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
                } else {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
                }
            } catch (NumberFormatException | NoSuchAlgorithmException e) {

                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
            }
        } else {

            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/getStudent");
        }
    }
}
