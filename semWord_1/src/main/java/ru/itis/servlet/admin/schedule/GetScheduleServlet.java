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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@WebServlet("/admin/getSchedule")
public class GetScheduleServlet extends HttpServlet {
    private final ScheduleRepositoryJdbc scheduleRepository;
    private final SubjectRepositoryJdbc subjectRepository;
    private final StudentRepositoryJdbc studentRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public GetScheduleServlet() {
        this.scheduleRepository = new ScheduleRepositoryJdbc(HOST, USER, PASS);
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS);
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selectedClass = request.getParameter("className");
        String selectedDay = request.getParameter("dayOfWeek");

        List<Schedule> scheduleForClassAndDay = scheduleRepository.findByClassAndDay(selectedClass, selectedDay);
        scheduleForClassAndDay.sort(Comparator.comparing(Schedule::getTime));
        for (Schedule schedule : scheduleForClassAndDay) {
            Long subjectId = schedule.getSubjectId();
            Optional<Subject> subject = subjectRepository.findSubjectById(subjectId);
            String subjectName = subject.get().getName();
            request.setAttribute("subjectName_" + schedule.getSubjectId(), subjectName);
        }
        Set<String> students = studentRepository.getAllUniqueClassNames();


        request.setAttribute("studentsClassName", students.stream().toList());
        request.setAttribute("students", studentRepository.getAll());
        request.setAttribute("scheduleForClassAndDay", scheduleForClassAndDay);
        request.getRequestDispatcher("/WEB-INF/Schedule/getSchedule.jsp").forward(request, response);


    }
}
