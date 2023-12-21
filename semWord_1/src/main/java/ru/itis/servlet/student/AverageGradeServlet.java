package ru.itis.servlet.student;


import ru.itis.controls.GradeRepositoryJdbc;
import ru.itis.controls.SubjectRepositoryJdbc;
import ru.itis.models.Grade;
import ru.itis.models.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@WebServlet("/student/averageGrade")
public class AverageGradeServlet extends HttpServlet {

    private GradeRepositoryJdbc gradeRepository;
    private SubjectRepositoryJdbc subjectRepository;

    @Override
    public void init() throws ServletException {
        this.gradeRepository = new GradeRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        this.subjectRepository = new SubjectRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = (Long) req.getAttribute("studentId");

        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        List<Subject> subjects = subjectRepository.getAllSubjects();

        Map<Subject, List<Grade>> gradesBySubject = new HashMap<>();

        for (Grade grade : grades) {
            Subject subject = subjects.stream()
                    .filter(s -> s.getId() == grade.getSubjectId())
                    .findFirst()
                    .orElseThrow(() -> new ServletException("Subject not found"));
            gradesBySubject.computeIfAbsent(subject, k -> new java.util.ArrayList<>()).add(grade);
        }

        Map<Subject, Double> averageGradesBySubject = new HashMap<>();
        for (Map.Entry<Subject, List<Grade>> entry : gradesBySubject.entrySet()) {
            OptionalDouble average = entry.getValue().stream()
                    .mapToInt(Grade::getGrade)
                    .average();
            average.ifPresent(a -> averageGradesBySubject.put(entry.getKey(), a));
        }

        double overallAverage = grades.stream()
                .mapToInt(Grade::getGrade)
                .average()
                .orElse(0.0);

        req.setAttribute("gradesBySubject", gradesBySubject);
        req.setAttribute("averageGradesBySubject", averageGradesBySubject);
        req.setAttribute("overallAverage", overallAverage);

        req.getRequestDispatcher("/WEB-INF/StudentServletsJsp/averageGrade.jsp").forward(req, resp);
    }

}
