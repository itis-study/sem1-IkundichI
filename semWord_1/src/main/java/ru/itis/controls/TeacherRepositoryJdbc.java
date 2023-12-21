package ru.itis.controls;

import ru.itis.models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TeacherRepositoryJdbc {
    //language=SQL
    public final static String SQL_SAVE = "INSERT INTO teacher (name, surname, mail, infoofeducation, password) VALUES (?, ?, ?, ?, ?)";
    public final static String SQL_GET_BY_ID = "SELECT * FROM teacher WHERE id = ?";
    private final static String SQL_GET_ALL = "SELECT * FROM teacher";
    private final static String SQL_DELETE_BY_ID = "DELETE FROM teacher WHERE id = ?";
    private final static String SQL_FOR_AUTH = "SELECT * FROM teacher WHERE mail = ? AND password = ?";
    private final static String SQL_FOR_EMAIL = "SELECT * FROM teacher WHERE mail = ?";


    private final String HOST;
    private final String USER;
    private final String PASS;

    public TeacherRepositoryJdbc(String HOST, String USER, String PASS) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void save(Teacher entity) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getMail());
            preparedStatement.setString(4, entity.getInfoOfEducation());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setMail(resultSet.getString("mail"));
                teacher.setInfoOfEducation(resultSet.getString("infoofeducation"));
                teacher.setPassword(resultSet.getString("password"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public Optional<Teacher> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet.getLong("id"));
                    teacher.setName(resultSet.getString("name"));
                    teacher.setSurname(resultSet.getString("surname"));
                    teacher.setMail(resultSet.getString("mail"));
                    teacher.setInfoOfEducation(resultSet.getString("infoofeducation"));
                    teacher.setPassword(resultSet.getString("password"));
                    return Optional.of(teacher);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Boolean deleteById(Long teacherId) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, teacherId);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Teacher findPersonByEmailAndPassword(String email, String surname) {
        try {
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_AUTH);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, surname);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setInfoOfEducation(resultSet.getString("infoofeducation"));
                teacher.setMail(resultSet.getString("mail"));
                teacher.setPassword(resultSet.getString("password"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean updateTeacherData(Long id, String name, String surname, String mail, String infoOfEducation, String password) {
        String sql = "UPDATE teacher SET name = ?, surname = ?, mail = ?, infoofeducation = ?, password = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, infoOfEducation);
            preparedStatement.setString(5, password);
            preparedStatement.setLong(6, id);

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Teacher findPersonByEmail(String email) {
        try {
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setInfoOfEducation(resultSet.getString("infoofeducation"));
                teacher.setMail(resultSet.getString("mail"));
                teacher.setPassword(resultSet.getString("password"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

