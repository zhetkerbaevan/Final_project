package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Animals;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Classification;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Habitat;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Meal;

import java.util.List;

public interface AnimalService {
    List<Animals> getAllAnimals();
    Animals getAnimal(Long id);
    List<Classification> getAllClassification();
    List<Meal> getAllMeal();
    List<Habitat> getAllHabitat();

    Animals saveAnimal(Animals animal);
    Animals addAnimal(Animals animal);

    void deleteAnimal(Animals animal);

    Classification getClassification(Long id);
    Meal getMeal(Long id);
    Habitat getHabitat(Long id);

    Classification addCl(Classification classification);

}
