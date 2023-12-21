package ru.itis.servlet.admin.grade;

import ru.itis.controls.GradeRepositoryJdbc;
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
import java.util.Comparator;
import java.util.List;

@WebServlet("/teacher/getStudentGrades")
public class StudentGradesServlet extends HttpServlet {
    private final StudentRepositoryJdbc studentRepository;
    private final SubjectRepositoryJdbc subjectRepository;
    private final GradeRepositoryJdbc gradeRepository;
    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASS = "password";

    public StudentGradesServlet() {
        this.studentRepository = new StudentRepositoryJdbc(HOST, USER, PASS);
        this.subjectRepository = new SubjectRepositoryJdbc(HOST, USER, PASS);
        this.gradeRepository = new GradeRepositoryJdbc(HOST, USER, PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentRepository.getAll();
        List<Subject> subjects;
        if (request.getAttribute("info") != null && request.getAttribute("info").equals("admin")) {
            subjects = subjectRepository.getAllSubjects();

        } else {
            subjects = subjectRepository.getSubjectsByTeacher(Long.parseLong(String.valueOf(request.getAttribute("teacherId"))));
        }
        request.setAttribute("studentList", students);
        request.setAttribute("subjectList", subjects);
        request.getRequestDispatcher("/WEB-INF/Grade/getStudentsGrades.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("student");
        String subjectIdStr = request.getParameter("subject");

        if (studentIdStr != null && !studentIdStr.isEmpty() &&
                subjectIdStr != null && !subjectIdStr.isEmpty()) {
            try {
                Long studentId = Long.parseLong(studentIdStr);
                Long subjectId = Long.parseLong(subjectIdStr);

                Student selectedStudent = studentRepository.findById(studentId).orElse(null);
                Subject selectedSubject = subjectRepository.findSubjectById(subjectId).orElse(null);

                if (selectedStudent != null && selectedSubject != null) {
                    List<Grade> studentGrades = gradeRepository.getGradesByStudentAndSubject(studentId, subjectId);
                    studentGrades.sort(Comparator.comparing(Grade::getDateOfGrade));

                    request.setAttribute("selectedStudent", selectedStudent);
                    request.setAttribute("selectedSubject", selectedSubject);
                    request.setAttribute("studentGrades", studentGrades);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        List<Student> students = studentRepository.getAll();
        List<Subject> subjects = subjectRepository.getAllSubjects();
        request.setAttribute("studentList", students);
        request.setAttribute("subjectList", subjects);
        request.getRequestDispatcher("/WEB-INF/Grade/getStudentsGrades.jsp").forward(request, response);
    }
}
