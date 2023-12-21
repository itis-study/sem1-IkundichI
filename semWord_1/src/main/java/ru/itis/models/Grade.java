package ru.itis.models;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Grade {
    private int id;
    private int subjectId;
    private int studentId;
    private Date dateOfGrade;
    private int grade;

}
