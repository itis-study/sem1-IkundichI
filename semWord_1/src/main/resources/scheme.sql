CREATE TABLE Teacher (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         surname VARCHAR(50) NOT NULL,
                         mail VARCHAR(100) NOT NULL,
                         infoOfEducation VARCHAR(200) NOT NULL,
                         password VARCHAR(100) NOT NULL
);

CREATE TABLE Student (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         surname VARCHAR(50) NOT NULL,
                         mail VARCHAR(100) NOT NULL,
                         password VARCHAR(100) NOT NULL,
                         className VARCHAR(10) NOT NULL
);

CREATE TABLE Subject (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         teacherId INT NOT NULL,
                         FOREIGN KEY (teacherId) REFERENCES Teacher(id)
);

CREATE TABLE Grade (
                       id SERIAL PRIMARY KEY,
                       subjectId INT NOT NULL,
                       studentId INT NOT NULL,
                       dateOfGrade DATE NOT NULL,
                       grade INT NOT NULL,
                       FOREIGN KEY (subjectId) REFERENCES Subject(id),
                       FOREIGN KEY (studentId) REFERENCES Student(id)
);

CREATE TABLE Schedule (
                          id SERIAL PRIMARY KEY,
                          subjectId INT NOT NULL,
                          className VARCHAR(10) NOT NULL,
                          dayOfWeek VARCHAR(10) NOT NULL,
                          time VARCHAR(20) NOT NULL,
                          FOREIGN KEY (subjectId) REFERENCES Subject(id)
);

CREATE TABLE Homework (
                          id SERIAL PRIMARY KEY,
                          subjectId INT NOT NULL,
                          dateOfHomework DATE NOT NULL,
                          description VARCHAR(200) NOT NULL,
                          className VARCHAR(10) NOT NULL,
                          FOREIGN KEY (subjectId) REFERENCES Subject(id)
);


INSERT INTO Teacher (name, surname, mail, infoOfEducation, password) VALUES ('Admin', 'Adminov', 'admin@mail.ru', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO student (name, surname, mail, password, className) VALUES ('Vasiliy', 'Pupkin', 'vasiliy@mail.ru', 'daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5', '5A');
INSERT INTO subject (name, teacherId) VALUES
                                          ('bio', 1),
                                          ('math', 1),
                                          ('izo', 1),
                                          ('ru', 1),
                                          ('franch', 1),
                                          ('pe', 1);
INSERT INTO grade (subjectId, studentId, dateOfGrade, grade) VALUES
                                                                 (3, 1, '2023-11-06', 2),
                                                                 (4, 1, '2023-11-09', 5),
                                                                 (5, 1, '2023-11-07', 4);
INSERT INTO schedule (subjectId, className, dayOfWeek, time) VALUES
                                                                 (2, '5A', 'Monday',    '08:30-10:00'),
                                                                 (3, '5A', 'Monday',    '10:10-11:40'),
                                                                 (1, '5A', 'Monday',    '12:10-13:40'),
                                                                 (4, '5A', 'Tuesday',   '08:30-10:00'),
                                                                 (5, '5A', 'Tuesday',   '10:10-11:40'),
                                                                 (2, '5A', 'Tuesday',   '12:10-13:40'),
                                                                 (6, '5A', 'Wednesday', '08:30-10:00'),
                                                                 (2, '5A', 'Wednesday', '10:10-11:40'),
                                                                 (3, '5A', 'Wednesday', '12:10-13:40'),
                                                                 (6, '5A', 'Thursday',  '08:30-10:00'),
                                                                 (4, '5A', 'Thursday',  '10:10-11:40'),
                                                                 (5, '5A', 'Thursday',  '12:10-13:40'),
                                                                 (2, '5A', 'Friday',    '08:30-10:00'),
                                                                 (1, '5A', 'Friday',    '10:10-11:40'),
                                                                 (4, '5A', 'Friday',    '12:10-13:40'),
                                                                 (4, '5A', 'Saturday',  '08:30-10:00'),
                                                                 (2, '5A', 'Saturday',  '10:10-11:40'),
                                                                 (6, '5A', 'Saturday',  '12:10-13:40');
INSERT into homework (subjectId, dateOfHomework, description, className) VALUES

                                                                             (5, '2023-11-07', 'write hello world from France', '5A'),
                                                                             (1, '2023-11-10', 'draw something', '5A'),
                                                                             (6, '2023-11-11', 'vzati formy na ylitzu', '5A');




