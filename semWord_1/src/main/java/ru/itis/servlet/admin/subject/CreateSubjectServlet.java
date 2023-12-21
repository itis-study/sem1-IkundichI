package ru.itis.servlet.admin.subject;

import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/createSubject")
public class CreateSubjectServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;

    public CreateSubjectServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherRepository.getAll();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/Subject/createSubject.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectName = request.getParameter("subjectName");
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        SubjectRepositoryJdbc subjectRepositoryJdbc = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

        subjectRepositoryJdbc.addSubjectToTeacher(subjectName, teacherId);

        response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
    }
}
