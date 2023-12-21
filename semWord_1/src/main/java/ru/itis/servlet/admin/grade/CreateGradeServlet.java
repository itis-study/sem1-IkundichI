package ru.itis.servlet.admin.grade;

import ru.itis.controls.GradeRepositoryJdbc;
import ru.itis.controls.ScheduleRepositoryJdbc;
import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.models.Grade;
import ru.itis.models.Student;
import ru.itis.models.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/teacher/createGrade")

public class CreateGradeServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;
    private final SubjectRepositoryJdbc subjectRepository;
    private final GradeRepositoryJdbc gradeRepository;
    private final ScheduleRepositoryJdbc scheduleRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public CreateGradeServlet() {
        this.scheduleRepository = new ScheduleRepositoryJdbc(HOST, USER, PASS);
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS);
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS);
        this.gradeRepository = new GradeRepositoryJdbc(HOST, USER, PASS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subject> subjects;
        if (request.getAttribute("info") != null && request.getAttribute("info").equals("admin")) {
            subjects = subjectRepository.getAllSubjects();

        } else {
            subjects = subjectRepository.getSubjectsByTeacher(Long.parseLong(String.valueOf(request.getAttribute("teacherId"))));
        }

        List<Student> students = studentRepository.getAll();

        request.setAttribute("subjectList", subjects);
        request.setAttribute("studentList", students);

        request.getRequestDispatcher("/WEB-INF/Grade/createGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentParam = request.getParameter("student");
            String subjectParam = request.getParameter("subject");
            String dateParam = request.getParameter("date");
            String gradeParam = request.getParameter("grade");

            if (studentParam != null && !studentParam.isEmpty() &&
                    subjectParam != null && !subjectParam.isEmpty() &&
                    dateParam != null && !dateParam.isEmpty() &&
                    gradeParam != null && !gradeParam.isEmpty()) {

                int studentId = Integer.parseInt(studentParam);
                int subjectId = Integer.parseInt(subjectParam);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateParam);
                int grade = Integer.parseInt(gradeParam);

                Grade gradeForSave = Grade.builder().dateOfGrade(date).subjectId(subjectId).studentId(studentId).grade(grade).build();
                if (scheduleRepository.isBeingSubjectAndDay(subjectId, getDayOfWeek(date))) {
                    gradeRepository.save(gradeForSave);
                    System.out.println("Grade saved");
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/teacher/createGrade");
                } else {
                    System.out.println("Grade doesn't saved");
                    request.getRequestDispatcher("/WEB-INF/Grade/errorGrade.jsp").forward(request, response);
                }


            } else {
                request.getRequestDispatcher("/WEB-INF/Grade/errorGrade.jsp").forward(request, response);
            }

        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/Grade/errorGrade.jsp").forward(request, response);
        }
    }

    private String getDayOfWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }

}
