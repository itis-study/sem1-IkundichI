package ru.itis.servlet.admin.homework;

import ru.itis.controls.*;
import ru.itis.models.Homework;
import ru.itis.models.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@WebServlet("/teacher/getHomework")
public class HomeworkInfoServlet extends HttpServlet {

    private final SubjectRepositoryJdbc subjectRepository;
    private final StudentRepositoryJdbc studentRepository;
    private final HomeworkRepositoryJdbc homeworkRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public HomeworkInfoServlet() {
        this.homeworkRepository = new HomeworkRepositoryJdbc(HOST, USER, PASS);
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS);
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<String> classNames = studentRepository.getAllUniqueClassNames();
        List<Subject> subjects;
        if (request.getAttribute("info") != null && request.getAttribute("info").equals("admin")) {
            subjects = subjectRepository.getAllSubjects();
        } else {
            subjects = subjectRepository.getSubjectsByTeacher(Long.parseLong(String.valueOf(request.getAttribute("teacherId"))));
        }
        request.setAttribute("subjectList", subjects);
        request.setAttribute("classNames", classNames.stream().toList());

        request.getRequestDispatcher("/WEB-INF/Homework/getHomework.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedClass = request.getParameter("className");
        String selectedSubject = request.getParameter("subject");
        List<Homework> homeworkForClassAndSubject = homeworkRepository.findBySubjectIdAndDateAndClassName(Integer.parseInt(selectedSubject), selectedClass);
        homeworkForClassAndSubject.sort(Comparator.comparing(Homework::getDateOfHomework));

        request.setAttribute("homeworkInfoList", homeworkForClassAndSubject);
        Set<String> classNames = studentRepository.getAllUniqueClassNames();
        List<Subject> subjects;
        if (request.getAttribute("info") != null && request.getAttribute("info").equals("admin")) {
            subjects = subjectRepository.getAllSubjects();
        } else {
            subjects = subjectRepository.getSubjectsByTeacher(Long.parseLong(String.valueOf(request.getAttribute("teacherId"))));
        }
        request.setAttribute("subjectList", subjects);
        request.setAttribute("classNames", classNames.stream().toList());

        request.getRequestDispatcher("/WEB-INF/Homework/getHomework.jsp").forward(request, response);
    }
}
