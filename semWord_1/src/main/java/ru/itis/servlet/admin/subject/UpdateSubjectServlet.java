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
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/updateSubject")
public class UpdateSubjectServlet extends HttpServlet {
    private final SubjectRepositoryJdbc subjectRepository;
    private final TeacherRepositoryJdbc teacherRepository;

    public UpdateSubjectServlet() {
        this.subjectRepository = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        this.teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectIdStr = request.getParameter("id");

        if (subjectIdStr != null) {
            try {
                long subjectId = Long.parseLong(subjectIdStr);
                Optional<Subject> subjectOptional = subjectRepository.findSubjectById(subjectId);

                if (subjectOptional.isPresent()) {
                    Subject subject = subjectOptional.get();
                    List<Teacher> teachers = teacherRepository.getAll();
                    request.setAttribute("subject", subject);
                    request.setAttribute("teachers", teachers);
                    request.getRequestDispatcher("/WEB-INF/Subject/updateSubject.jsp").forward(request, response);
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectIdStr = request.getParameter("id");
        String name = request.getParameter("name");
        String teacherIdStr = request.getParameter("teacherId");

        if (subjectIdStr != null) {
            try {
                long subjectId = Long.parseLong(subjectIdStr);
                Optional<Subject> subjectOptional = subjectRepository.findSubjectById(subjectId);

                if (subjectOptional.isPresent()) {
                    Subject updatedSubject = subjectOptional.get();
                    updatedSubject.setName(name);
                    if (teacherIdStr != null) {
                        long teacherId = Long.parseLong(teacherIdStr);
                        updatedSubject.setTeacherId(teacherId);
                    }

                    subjectRepository.updateSubject(subjectId, updatedSubject.getName(), updatedSubject.getTeacherId());

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/teacherSubjects");
        }
    }
}
