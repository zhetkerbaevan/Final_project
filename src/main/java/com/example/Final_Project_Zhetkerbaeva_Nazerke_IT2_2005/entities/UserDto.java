package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String about_me;
    private Long gender_id;

    // геттеры и сеттеры
}