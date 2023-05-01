package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "classification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long classification_id;

    @Column(name="name")
    private String classification_name;
}
