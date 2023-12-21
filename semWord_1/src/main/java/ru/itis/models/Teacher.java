package ru.itis.models;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Teacher {
    Long id;
    String name;
    String surname;
    String mail;
    String infoOfEducation;
    String password;
}
