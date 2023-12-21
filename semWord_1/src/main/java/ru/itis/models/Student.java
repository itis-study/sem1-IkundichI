package ru.itis.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    Long id;
    String name;
    String surname;
    String mail;
    String password;
    String className;
}
