package ru.itis.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InfoSchedule {
    private String time;
    private String subject;
    private String homeWork;
    private String grade;
}
