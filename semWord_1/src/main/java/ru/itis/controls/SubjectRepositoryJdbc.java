package ru.itis.controls;

import ru.itis.models.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectRepositoryJdbc {

    private final String HOST;
    private final String USER;
    private final String PASS;

    private static final String SQL_INSERT_SUBJECT = "INSERT INTO Subject (name, teacherId) VALUES (?, ?)";
    private static final String SQL_DELETE_SUBJECT = "DELETE FROM Subject WHERE id = ?";
    private static final String SQL_UPDATE_SUBJECT = "UPDATE Subject SET name = ?, teacherId = ? WHERE id = ?";
    private static final String SQL_SELECT_ALL_SUBJECTS = "SELECT * FROM Subject";
    private static final String SQL_SELECT_SUBJECTS_BY_TEACHER = "SELECT * FROM Subject WHERE teacherId = ?";
    private static final String SQL_FIND_SUBJECT_BY_ID = "SELECT * FROM Subject WHERE id = ?";

    public SubjectRepositoryJdbc(String host, String user, String pass) {
        this.HOST = host;
        this.USER = user;
        this.PASS = pass;
    }

    public boolean addSubjectToTeacher(String subjectName, Long teacherId) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_SUBJECT)) {
            preparedStatement.setString(1, subjectName);
            preparedStatement.setLong(2, teacherId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSubject(Long subjectId) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SUBJECT)) {
            preparedStatement.setLong(1, subjectId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSubject(Long subjectId, String newName, Long newTeacherId) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SUBJECT)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, newTeacherId);
            preparedStatement.setLong(3, subjectId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_SUBJECTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                subjects.add(mapRowToSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        List<Subject> subjects = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_SUBJECTS_BY_TEACHER)) {
            preparedStatement.setLong(1, teacherId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    subjects.add(mapRowToSubject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public Optional<Subject> findSubjectById(Long subjectId) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_SUBJECT_BY_ID)) {
            preparedStatement.setLong(1, subjectId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToSubject(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Subject mapRowToSubject(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Long teacherId = resultSet.getLong("teacherId");
        return new Subject(id, name, teacherId);
    }

}
