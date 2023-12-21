package ru.itis.servlet.admin.schedule;

import ru.itis.controls.ScheduleRepositoryJdbc;
import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.models.Schedule;
import ru.itis.models.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/createSchedule")
public class CreateScheduleServlet extends HttpServlet {
    private final ScheduleRepositoryJdbc scheduleRepository;
    private final SubjectRepositoryJdbc subjectRepository;
    private final StudentRepositoryJdbc studentRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public CreateScheduleServlet() {
        this.scheduleRepository = new ScheduleRepositoryJdbc(HOST, USER, PASS);
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS);
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subject> subjects = subjectRepository.getAllSubjects();
        Set<String> students = studentRepository.getAllUniqueClassNames();

        request.setAttribute("studentsClassName", students.stream().toList());
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("/WEB-INF/Schedule/createSchedule.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long subjectId = Long.parseLong(request.getParameter("subjectId"));
            String className = request.getParameter("className");
            String dayOfWeek = request.getParameter("dayOfWeek");
            String time = request.getParameter("time");

            Schedule schedule = Schedule.builder().subjectId(subjectId).className(className).dayOfWeek(dayOfWeek).time(time).build();
            scheduleRepository.save(schedule);

            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/createSchedule");

        } catch (NumberFormatException e) {

            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin/createSchedule");
        }
    }
}
