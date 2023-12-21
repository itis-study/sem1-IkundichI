package ru.itis.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Schedule {
    private Long id;
    private Long subjectId;
    private String className;
    private String dayOfWeek;
    private String time;
}
