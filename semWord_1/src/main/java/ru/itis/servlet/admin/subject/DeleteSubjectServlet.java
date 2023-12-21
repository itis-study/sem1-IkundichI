package ru.itis.servlet.admin.subject;


import ru.itis.controls.SubjectRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteSubject")
public class DeleteSubjectServlet extends HttpServlet {
    private final SubjectRepositoryJdbc subjectRepository;

    public DeleteSubjectServlet() {
        this.subjectRepository = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectIdStr = request.getParameter("id");

        if (subjectIdStr != null) {
            try {
                long subjectId = Long.parseLong(subjectIdStr);
                boolean deleted = subjectRepository.deleteSubject(subjectId);

                if (deleted) {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
                }
            } catch (NumberFormatException | IOException e) {
                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
        }
    }
}

