package ru.itis.servlet.admin.subject;

import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Subject;
import ru.itis.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@WebServlet("/admin/teacherSubjects")
public class TeacherSubjectsServlet extends HttpServlet {
    private final TeacherRepositoryJdbc teacherRepository;
    private final SubjectRepositoryJdbc subjectRepository;

    public TeacherSubjectsServlet() {
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        this.subjectRepository = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherRepository.getAll();
        teachers.sort(Comparator.comparing(Teacher::getName));
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/Subject/teacherSubjects.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));

        List<Subject> teacherSubjects = subjectRepository.getSubjectsByTeacher(teacherId);
        request.setAttribute("teacherSubjects", teacherSubjects);

        List<Teacher> teachers = teacherRepository.getAll();
        request.setAttribute("teachers", teachers);

        request.getRequestDispatcher("/WEB-INF/Subject/teacherSubjects.jsp").forward(request, response);
    }
}

