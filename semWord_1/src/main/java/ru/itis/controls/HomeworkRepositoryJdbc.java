package ru.itis.controls;

import ru.itis.models.Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeworkRepositoryJdbc {

    private final String HOST;
    private final String USER;
    private final String PASS;

    public HomeworkRepositoryJdbc(String HOST, String USER, String PASS) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void save(Homework homework) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO homework (subjectId, dateOfHomework, description, className) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, homework.getSubjectId());
            preparedStatement.setDate(2, new java.sql.Date(homework.getDateOfHomework().getTime()));
            preparedStatement.setString(3, homework.getDescription());
            preparedStatement.setString(4, homework.getClassName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Homework updatedHomework) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE homework SET subjectId = ?, dateOfHomework = ?, description = ?, className = ? WHERE id = ?")) {
            preparedStatement.setInt(1, updatedHomework.getSubjectId());
            preparedStatement.setDate(2, new java.sql.Date(updatedHomework.getDateOfHomework().getTime()));
            preparedStatement.setString(3, updatedHomework.getDescription());
            preparedStatement.setString(4, updatedHomework.getClassName());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM homework WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Homework mapRow(ResultSet resultSet) throws SQLException {
        Homework homework = new Homework();
        homework.setId(resultSet.getInt("id"));
        homework.setSubjectId(resultSet.getInt("subjectId"));
        homework.setDateOfHomework(resultSet.getDate("dateOfHomework"));
        homework.setDescription(resultSet.getString("description"));
        homework.setClassName(resultSet.getString("className"));
        return homework;
    }
    public Homework findBySubjectIdAndDateAndClassName(Long subjectId, String dateOfHomework, String className) {
        List<Homework> homeworkList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homework WHERE subjectId = ? AND dateOfHomework = ? AND className = ?")) {
            preparedStatement.setLong(1, subjectId);
            preparedStatement.setDate(2, Date.valueOf(dateOfHomework));
            preparedStatement.setString(3, className);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Homework homework = mapRow(resultSet);
                    homeworkList.add(homework);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworkList.size() > 0 ? homeworkList.get(0) : null;
    }

    public List<Homework> findBySubjectIdAndDateAndClassName(int subjectId, String className) {
        List<Homework> homeworkList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homework WHERE subjectId = ? AND className = ?")) {
            preparedStatement.setInt(1, subjectId);
            preparedStatement.setString(2, className);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Homework homework = mapRow(resultSet);
                    homeworkList.add(homework);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworkList;
    }


}
