package ru.itis.servlet.admin.schedule;


import ru.itis.controls.ScheduleRepositoryJdbc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteSchedule")
public class DeleteScheduleServlet extends HttpServlet {
    private final ScheduleRepositoryJdbc scheduleRepositoryJdbc;

    public DeleteScheduleServlet() {
        this.scheduleRepositoryJdbc = new ScheduleRepositoryJdbc("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scheduleIdStr = request.getParameter("id");

        if (scheduleIdStr != null) {
            try {
                long studentId = Long.parseLong(scheduleIdStr);
                boolean deleted = scheduleRepositoryJdbc.delete(studentId);

                if (deleted) {

                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
                } else {
                    response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
                }
            } catch (NumberFormatException | IOException e) {

                response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
            }
        } else {

            response.sendRedirect("http://localhost:8080/java_semestWork_1/admin");
        }
    }
}
