package ru.itis.servlet.admin.student;


import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/admin/getStudent")
public class GetStudentServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;

    public GetStudentServlet() {
        this.studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentRepository.getAll();
        students.sort(Comparator.comparing(Student::getName));
        request.setAttribute("studentList", students);
        request.getRequestDispatcher("/WEB-INF/Student/getStudent.jsp").forward(request, response);
    }
}
