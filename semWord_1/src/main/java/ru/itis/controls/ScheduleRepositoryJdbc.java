package ru.itis.controls;

import ru.itis.models.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepositoryJdbc {


    private final String HOST;
    private final String USER;
    private final String PASS;

    public ScheduleRepositoryJdbc(String HOST, String USER, String PASS) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void save(Schedule schedule) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Schedule (subjectId, className, dayOfWeek, time) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setLong(1, schedule.getSubjectId());
            preparedStatement.setString(2, schedule.getClassName());
            preparedStatement.setString(3, schedule.getDayOfWeek());
            preparedStatement.setString(4, schedule.getTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, Schedule updatedSchedule) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Schedule SET subjectId = ?, className = ?, dayOfWeek = ?, time = ? WHERE id = ?")) {
            preparedStatement.setLong(1, updatedSchedule.getSubjectId());
            preparedStatement.setString(2, updatedSchedule.getClassName());
            preparedStatement.setString(3, updatedSchedule.getDayOfWeek());
            preparedStatement.setString(4, updatedSchedule.getTime());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Schedule WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Schedule mapRow(ResultSet resultSet) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(resultSet.getLong("id"));
        schedule.setSubjectId(resultSet.getLong("subjectId"));
        schedule.setClassName(resultSet.getString("className"));
        schedule.setDayOfWeek(resultSet.getString("dayOfWeek"));
        schedule.setTime(resultSet.getString("time"));
        return schedule;
    }

    public List<Schedule> findByClassAndDay(String className, String dayOfWeek) {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Schedule WHERE className = ? AND dayOfWeek = ?")) {
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, dayOfWeek);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = mapRow(resultSet);
                    schedules.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }



    public Boolean isBeingSubjectAndDay(Integer subjecId, String dayOfWeek) {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Schedule WHERE subjectid = ? AND dayOfWeek = ?")) {
            preparedStatement.setInt(1, subjecId);
            preparedStatement.setString(2, dayOfWeek);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = mapRow(resultSet);
                    schedules.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules.size() > 0;
    }

}
