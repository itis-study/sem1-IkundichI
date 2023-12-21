package ru.itis.servlet.admin.grade;

import ru.itis.controls.GradeRepositoryJdbc;
import ru.itis.models.Grade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@WebServlet("/teacher/updateGrade")
public class UpdateGradeServlet extends HttpServlet {
    private final GradeRepositoryJdbc gradeRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public UpdateGradeServlet() {
        this.gradeRepository = new GradeRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradeIdStr = request.getParameter("id");

        if (gradeIdStr != null && !gradeIdStr.isEmpty()) {
            try {
                int gradeId = Integer.parseInt(gradeIdStr);
                Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);

                if (optionalGrade.isPresent()) {
                    Grade grade = optionalGrade.get();
                    request.setAttribute("grade", grade);
                    request.getRequestDispatcher("/WEB-INF/Grade/updateGrade.jsp").forward(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradeIdStr = request.getParameter("id");
        String newGradeStr = request.getParameter("newGrade");
        String newDateStr = request.getParameter("newDate");

        if (gradeIdStr != null && !gradeIdStr.isEmpty() &&
                newGradeStr != null && !newGradeStr.isEmpty()) {
            try {
                int gradeId = Integer.parseInt(gradeIdStr);
                int newGrade = Integer.parseInt(newGradeStr);
                Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDateStr);

                Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);

                if (optionalGrade.isPresent()) {
                    Grade grade = optionalGrade.get();
                    grade.setGrade(newGrade);
                    grade.setDateOfGrade(newDate);
                    gradeRepository.update(gradeId, grade);

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/teacher/getStudentGrades");
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/teacher/getStudentGrades");                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("http://localhost:8080/java_semestWork_1/teacher/getStudentGrades");            } catch (
                    ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("http://localhost:8080/java_semestWork_1/teacher/getStudentGrades");        }
    }
}
