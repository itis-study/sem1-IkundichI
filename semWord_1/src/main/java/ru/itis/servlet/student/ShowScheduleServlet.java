package ru.itis.servlet.student;

import ru.itis.controls.*;
import ru.itis.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/student/showSchedule")
public class ShowScheduleServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "password";
    private static final String REDIRECT_URL = "http://localhost:8080/java_semestWork_1";
    private static final String[] DAY_OF_WEEK_LABELS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private ScheduleRepositoryJdbc scheduleRepository;
    private SubjectRepositoryJdbc subjectRepository;
    private HomeworkRepositoryJdbc homeworkRepository;
    private GradeRepositoryJdbc gradeRepository;

    @Override
    public void init() throws ServletException {
        scheduleRepository = new ScheduleRepositoryJdbc(DB_URL, USER, PASS);
        subjectRepository = new SubjectRepositoryJdbc(DB_URL, USER, PASS);
        homeworkRepository = new HomeworkRepositoryJdbc(DB_URL, USER, PASS);
        gradeRepository = new GradeRepositoryJdbc(DB_URL, USER, PASS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!userIsAuthenticated(req)) {
            resp.sendRedirect(REDIRECT_URL);
            return;
        }

        if ("student".equals(req.getSession().getAttribute("role"))) {
            String className = (String) req.getSession().getAttribute("className");
            Map<String, List<InfoSchedule>> infoSchedulesMap1 = new LinkedHashMap<>();
            Map<String, List<InfoSchedule>> infoSchedulesMap2 = new LinkedHashMap<>();

            List<String> daysOfWeek = getDates();
            List<String> daysOfWeek1 = daysOfWeek.subList(0, 3);
            List<String> daysOfWeek2 = daysOfWeek.subList(3, 6);

            for (String dayOfWeekName : daysOfWeek1) {
                if (!"Sunday".equals(dayOfWeekName)) {
                    List<Schedule> schedules = scheduleRepository.findByClassAndDay(className, getDayOfWeek(dayOfWeekName));
                    List<InfoSchedule> infoSchedules = buildDailySchedule(schedules, dayOfWeekName, className, req);
                    infoSchedulesMap1.put(dayOfWeekName, infoSchedules);
                }
            }

            for (String dayOfWeekName : daysOfWeek2) {
                if (!"Sunday".equals(dayOfWeekName)) {
                    List<Schedule> schedules = scheduleRepository.findByClassAndDay(className, getDayOfWeek(dayOfWeekName));
                    List<InfoSchedule> infoSchedules = buildDailySchedule(schedules, dayOfWeekName, className, req);
                    infoSchedulesMap2.put(dayOfWeekName, infoSchedules);
                }
            }

            req.setAttribute("infoSchedulesMap1", infoSchedulesMap1);
            req.setAttribute("infoSchedulesMap2", infoSchedulesMap2);
            req.getRequestDispatcher("/WEB-INF/StudentServletsJsp/studentSchedule.jsp").forward(req, resp);
        }
    }

    private boolean userIsAuthenticated(HttpServletRequest req) {
        return req.getSession().getAttribute("role") != null;
    }

    private List<InfoSchedule> buildDailySchedule(List<Schedule> schedules, String dayOfWeekName, String className, HttpServletRequest req) {
        List<InfoSchedule> infoSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            Optional<Subject> subject = subjectRepository.findSubjectById(schedule.getSubjectId());
            if (!subject.isPresent()) continue;

            String subjectName = subject.get().getName();
            Homework homework = homeworkRepository.findBySubjectIdAndDateAndClassName(schedule.getSubjectId(), dayOfWeekName, className);
            Grade grade = gradeRepository.findBySubjectIdAndDateOfGradeAndStudentId(schedule.getSubjectId(), dayOfWeekName, (Long) req.getAttribute("studentId"));

            InfoSchedule infoSchedule = new InfoSchedule();
            infoSchedule.setTime(schedule.getTime());
            infoSchedule.setSubject(subjectName);
            infoSchedule.setHomeWork(homework != null ? homework.getDescription() : "N/A");
            infoSchedule.setGrade(grade != null ? String.valueOf(grade.getGrade()) : " ");
            infoSchedules.add(infoSchedule);
        }
        return infoSchedules;
    }

    private static List<String> getDates() {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dates.add(dateFormat.format(calendar.getTime()));
            }
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return dates;
    }

    private static String getDayOfWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            SimpleDateFormat sdfDayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            return sdfDayOfWeek.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
