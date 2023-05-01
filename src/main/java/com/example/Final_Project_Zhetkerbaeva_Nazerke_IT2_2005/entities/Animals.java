package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long animal_id;

    @Column(name="type")
    private String type;

    @Column(name="amount", length = 3)
    private int amount;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Meal meal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classification classification;

    @ManyToOne(fetch = FetchType.EAGER)
    private Habitat habitat;

}
