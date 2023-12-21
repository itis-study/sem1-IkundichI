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

@WebServlet("/admin/updateSchedule")
public class UpdateScheduleServlet extends HttpServlet {
    private final ScheduleRepositoryJdbc scheduleRepository;
    private final SubjectRepositoryJdbc subjectRepository;
    private final StudentRepositoryJdbc studentRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public UpdateScheduleServlet() {
        this.scheduleRepository = new ScheduleRepositoryJdbc(HOST, USER, PASS); // Ваш репозиторий для Schedule
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS); // Ваш репозиторий для Subject
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS); // Ваш репозиторий для Student
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subject> subjects = subjectRepository.getAllSubjects();
        Set<String> students = studentRepository.getAllUniqueClassNames();

        request.setAttribute("subjects", subjects);
        request.setAttribute("studentsClassName", students.stream().toList());
        request.getRequestDispatcher("/WEB-INF/Schedule/updateSchedule.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        String className = request.getParameter("className");
        String dayOfWeek = request.getParameter("dayOfWeek");
        String time = request.getParameter("time");
        Long scheduleId = Long.parseLong(request.getParameter("id"));

        Schedule schedule = Schedule.builder().subjectId(subjectId).className(className).dayOfWeek(dayOfWeek).time(time).build();
        scheduleRepository.update(scheduleId, schedule);

        response.sendRedirect(request.getContextPath() + "/admin/getSchedule");
    }
}
