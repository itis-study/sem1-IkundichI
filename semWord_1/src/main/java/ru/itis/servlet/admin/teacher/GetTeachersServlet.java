package ru.itis.servlet.admin.teacher;

import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/admin/getTeacher")
public class GetTeachersServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;

    public GetTeachersServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teacherList = teacherRepository.getAll();
        teacherList.sort(Comparator.comparing(Teacher::getName));

        request.setAttribute("teacherList", teacherList);
        request.getRequestDispatcher("/WEB-INF/Teacher/getTeacher.jsp").forward(request, response);
    }
}
