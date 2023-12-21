
package ru.itis.servlet.admin.homework;

import ru.itis.controls.HomeworkRepositoryJdbc;
import ru.itis.controls.ScheduleRepositoryJdbc;
import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.models.Homework;
import ru.itis.models.Student;
import ru.itis.models.Subject;
import ru.itis.service.EmailService;

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
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@WebServlet("/teacher/createHomework")
public class CreateHomeworkServlet extends HttpServlet {

    private final SubjectRepositoryJdbc subjectRepositoryJdbc;
    private final StudentRepositoryJdbc studentRepositoryJdbc;
    private final HomeworkRepositoryJdbc homeworkRepository;
    private final ScheduleRepositoryJdbc scheduleRepositoryJdbc;
    private final EmailService emailService = new EmailService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);


    public CreateHomeworkServlet() {
        scheduleRepositoryJdbc = new ScheduleRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        subjectRepositoryJdbc = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        studentRepositoryJdbc = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        homeworkRepository = new HomeworkRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects;
        if (req.getAttribute("info") != null && req.getAttribute("info").equals("admin")) {
            subjects = subjectRepositoryJdbc.getAllSubjects();

        } else {
            subjects = subjectRepositoryJdbc.getSubjectsByTeacher(Long.parseLong(String.valueOf(req.getAttribute("teacherId"))));
        }
        Set<String> classNames = studentRepositoryJdbc.getAllUniqueClassNames();
        req.setAttribute("subjectList", subjects);
        req.setAttribute("classNames", classNames.stream().toList());
        req.getRequestDispatcher("/WEB-INF/Homework/createHomework.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectId = req.getParameter("subject");
        String className = req.getParameter("className");
        String date = req.getParameter("date");
        String description = req.getParameter("description");
        if (subjectId != null && className != null && date != null && description != null) {
            try {
                Date dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                Homework homework = Homework.builder()
                        .dateOfHomework(dateFormat)
                        .subjectId((int) Long.parseLong(subjectId))
                        .className(className)
                        .description(description)
                        .build();
                if (scheduleRepositoryJdbc.isBeingSubjectAndDay((int) Long.parseLong(subjectId), getDayOfWeek(dateFormat))) {
                    homeworkRepository.save(homework);
                    executorService.submit(() -> {
                        sendMessage("Homework created", "Add homework for " + homework.getSubjectId() + " "  + homework.getClassName() +  " " + homework.getDateOfHomework() + " " + homework.getDescription());
                    });
                    resp.sendRedirect(req.getContextPath() + "/teacher/createHomework");
                } else {
                    req.getRequestDispatcher("/WEB-INF/Homework/errorHomework.jsp").forward(req, resp);
                }

            } catch (ParseException e) {
                throw new ServletException("Error parsing date", e);
            }
        }

    }
    @Override
    public void destroy() {
        executorService.shutdown();
    }


    private void sendMessage(String subject, String message) {
        List<Student> toEmails = studentRepositoryJdbc.getAll();
        for (Student student : toEmails) {
            String toEmail = student.getMail();
            emailService.sendEmail(toEmail, subject, message);
        }
        String toEmail = "artemvaleev428@gmail.com"; //Дл теста, потому-что остальные почты не сущестувуют
        emailService.sendEmail(toEmail, subject, message);

    }


    private String getDayOfWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }
}
