package ru.itis.servlet;

import ru.itis.controls.StudentRepositoryJdbc;
import ru.itis.controls.TeacherRepositoryJdbc;
import ru.itis.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/myCabinet")
public class MyCabinetServlet extends HttpServlet {


    TeacherRepositoryJdbc teacherRepository = new TeacherRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    StudentRepositoryJdbc studentRepository = new StudentRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (teacherRepository.findPersonByEmail((String) request.getAttribute("email")) != null) {
            Teacher teacher = teacherRepository.findPersonByEmail((String) request.getAttribute("email"));
            request.setAttribute("teacher1", teacher);

        } else if (studentRepository.findStudentByEmail((String) request.getAttribute("email")) != null) {
            request.setAttribute("student1", studentRepository.findStudentByEmail((String) request.getAttribute("email")));
        }
        request.getRequestDispatcher("/WEB-INF/myCabinet.jsp").forward(request, response);


    }



}
