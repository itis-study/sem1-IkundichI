package ru.itis.servlet.admin.grade;

import ru.itis.controls.GradeRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/deleteGrade")
public class DeleteGradeServlet extends HttpServlet {
    private final GradeRepositoryJdbc gradeRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public DeleteGradeServlet() {
        this.gradeRepository = new GradeRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradeIdStr = request.getParameter("id");

        if (gradeIdStr != null && !gradeIdStr.isEmpty()) {
            try {
                int gradeId = Integer.parseInt(gradeIdStr);
                boolean deleted = gradeRepository.delete(gradeId);

                if (deleted) {
                    redirectToPage(response, request.getAttribute("info"));
                } else {
                    redirectToPage(response, request.getAttribute("info"));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                redirectToPage(response, request.getAttribute("info"));
            }
        } else {
            redirectToPage(response, request.getAttribute("info"));
        }
    }

    private void redirectToPage(HttpServletResponse response, Object infoAttribute) throws IOException {
        String redirectUrl;
        if (infoAttribute != null && infoAttribute.equals("admin")) {
            redirectUrl = "http://localhost:8080/java_semestWork_1/admin";
        } else {
            redirectUrl = "http://localhost:8080/java_semestWork_1/teacher/gradePage";
        }
        response.sendRedirect(redirectUrl);
    }
}
