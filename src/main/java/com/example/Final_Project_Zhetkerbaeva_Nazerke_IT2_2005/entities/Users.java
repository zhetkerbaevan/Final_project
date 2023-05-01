package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long user_id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="email", length = 70)
    private String email;

    @Column(name="about_me")
    private String about_me;

    @Column(name="ticket")
    private boolean ticket;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gender gender;
}
