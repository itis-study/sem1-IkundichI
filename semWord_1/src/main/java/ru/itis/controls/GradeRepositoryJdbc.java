package ru.itis.controls;

import ru.itis.models.Grade;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GradeRepositoryJdbc {

    private final String HOST;
    private final String USER;
    private final String PASS;

    public GradeRepositoryJdbc(String HOST, String USER, String PASS) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
    }

    public Optional<Grade> findById(int id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM grade WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(Grade grade) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO grade (subjectId, studentId, dateOfGrade, grade) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, grade.getSubjectId());
            preparedStatement.setInt(2, grade.getStudentId());
            preparedStatement.setDate(3, new java.sql.Date(grade.getDateOfGrade().getTime()));
            preparedStatement.setInt(4, grade.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Grade updatedGrade) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE grade SET subjectId = ?, studentId = ?, dateOfGrade = ?, grade = ? WHERE id = ?")) {
            preparedStatement.setInt(1, updatedGrade.getSubjectId());
            preparedStatement.setInt(2, updatedGrade.getStudentId());
            preparedStatement.setDate(3, new java.sql.Date(updatedGrade.getDateOfGrade().getTime()));
            preparedStatement.setInt(4, updatedGrade.getGrade());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM grade WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Grade mapRow(ResultSet resultSet) throws SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getInt("id"));
        grade.setSubjectId(resultSet.getInt("subjectId"));
        grade.setStudentId(resultSet.getInt("studentId"));
        grade.setDateOfGrade(resultSet.getDate("dateOfGrade"));
        grade.setGrade(resultSet.getInt("grade"));
        return grade;
    }

    public List<Grade> findByStudentId(Long studentId) {
        List<Grade> grades = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM grade WHERE studentId = ?")) {
            preparedStatement.setLong(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = mapRow(resultSet);
                    grades.add(grade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public List<Grade> getGradesByStudentAndSubject(Long studentId, Long subjectId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT * FROM grade WHERE studentId = ? AND subjectId = ?";

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, subjectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = mapRow(resultSet);
                    grades.add(grade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public Grade findBySubjectIdAndDateOfGradeAndStudentId(Long subjectId, String dateOfGrade, Long studentId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT * FROM grade WHERE subjectId = ? AND dateOfGrade = ? AND studentId = ?";

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, subjectId);
            preparedStatement.setDate(2, Date.valueOf(dateOfGrade));
            preparedStatement.setLong(3, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = mapRow(resultSet);
                    grades.add(grade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades.size() > 0 ? grades.get(0) : null;
    }




}
