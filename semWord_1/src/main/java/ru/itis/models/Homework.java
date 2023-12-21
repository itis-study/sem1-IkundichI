package ru.itis.models;


import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Homework {
    private int id;
    private int subjectId;
    private Date dateOfHomework;
    private String description;
    private String className;

}
