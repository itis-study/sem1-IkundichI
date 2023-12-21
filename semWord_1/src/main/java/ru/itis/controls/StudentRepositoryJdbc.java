package ru.itis.controls;

import ru.itis.models.Student;

import java.sql.*;
import java.util.*;

public class StudentRepositoryJdbc {
    public final static String SQL_SAVE = "INSERT INTO student (name, surname, mail, password, classname) VALUES (?, ?, ?, ?, ?)";
    public final static String SQL_GET_BY_ID = "SELECT * FROM student WHERE id = ?";
    private final static String SQL_GET_ALL = "SELECT * FROM student";
    private final static String SQL_GET_ALL_UNIQUE_CLASS_NAME = "SELECT DISTINCT classname FROM student";
    private final static String SQL_DELETE_BY_NAME = "DELETE FROM student WHERE id = ?";
    private final static String SQL_FOR_AUTH = "SELECT * FROM student WHERE mail = ? AND password = ?";
    private final static String SQL_FOR_EMAIL = "SELECT * FROM student WHERE mail = ?";


    private final String HOST;
    private final String USER;
    private final String PASS;

    public StudentRepositoryJdbc(String HOST, String USER, String PASS) {
        this.HOST = HOST;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void save(Student entity) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getMail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getClassName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setMail(resultSet.getString("mail"));
                student.setPassword(resultSet.getString("password"));
                student.setClassName(resultSet.getString("classname"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Set<String> getAllUniqueClassNames() {
        Set<String> classNames = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_UNIQUE_CLASS_NAME);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String className = resultSet.getString("classname");
                classNames.add(className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classNames;
    }


    public Optional<Student> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setMail(resultSet.getString("mail"));
                    student.setPassword(resultSet.getString("password"));
                    student.setClassName(resultSet.getString("classname"));
                    return Optional.of(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Boolean deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME)) {
            preparedStatement.setLong(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student findStudentByEmailAndPassword(String email, String password) {
        try {
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_AUTH);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setMail(resultSet.getString("mail"));
                student.setPassword(resultSet.getString("password"));
                student.setClassName(resultSet.getString("classname"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStudentData(Long id, String name, String surname, String className, String mail, String password) {
        String sql = "UPDATE student SET name = ?, surname = ?, classname = ?, mail = ?, password = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, className);
            preparedStatement.setString(4, mail);
            preparedStatement.setString(5, password);
            preparedStatement.setLong(6, id);

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Student findStudentByEmail(String email) {
        try {
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_EMAIL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setMail(resultSet.getString("mail"));
                student.setPassword(resultSet.getString("password"));
                student.setClassName(resultSet.getString("classname"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
