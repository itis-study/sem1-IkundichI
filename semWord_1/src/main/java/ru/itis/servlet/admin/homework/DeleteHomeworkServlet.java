package ru.itis.servlet.admin.homework;

import ru.itis.controls.HomeworkRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/deleteHomework")
public class DeleteHomeworkServlet extends HttpServlet {
    private final HomeworkRepositoryJdbc homeworkRepositoryJdbc;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public DeleteHomeworkServlet() {
        this.homeworkRepositoryJdbc = new HomeworkRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String homeworkIdStr = request.getParameter("id");

        if (homeworkIdStr != null && !homeworkIdStr.isEmpty()) {
            try {
                int homeworkId = Integer.parseInt(homeworkIdStr);
                boolean deleted = homeworkRepositoryJdbc.delete(homeworkId);

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
            redirectUrl = "http://localhost:8080/java_semestWork_1/teacher/homeworkPage";
        }
        response.sendRedirect(redirectUrl);
    }
}
