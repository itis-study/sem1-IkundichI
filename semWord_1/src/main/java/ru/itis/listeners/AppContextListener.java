package ru.itis.listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebListener
public class AppContextListener implements ServletContextListener {

    private String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private String USER = "postgres";
    private String PASSWORD = "password";


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Произведен запуск приложения");

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("scheme.sql");
            if (is == null) {
                throw new IllegalArgumentException("Файл scheme.sql не найден");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                StringBuilder sqlQuery = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sqlQuery.append(line);
                }
                statement.executeUpdate(sqlQuery.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Произведено завершение приложения");
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String[] dropTableStatements = {
                    "DROP TABLE IF EXISTS schedule",
                    "DROP TABLE IF EXISTS homework",
                    "DROP TABLE IF EXISTS grade",
                    "DROP TABLE IF EXISTS subject",
                    "DROP TABLE IF EXISTS student",
                    "DROP TABLE IF EXISTS teacher",
            };

            for (String sql : dropTableStatements) {
                statement.executeUpdate(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
