package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.impl;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Animals;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Classification;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Habitat;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Meal;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.AnimalsRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.ClassificationRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.HabitatRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.MealRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalsRepo animalsRepo;

    @Autowired
    MealRepo mealRepo;
    @Autowired
    HabitatRepo habitatRepo;
    @Override
    public List<Animals> getAllAnimals() {
        return animalsRepo.findAll();
    }
    @Autowired
    ClassificationRepo classificationRepo;
    @Override
    public List<Classification> getAllClassification() {
        return classificationRepo.findAll();
    }

    @Override
    public List<Meal> getAllMeal() {
        return mealRepo.findAll();
    }

    @Override
    public Animals addAnimal(Animals animal) {
        return animalsRepo.save(animal);
    }

    @Override
    public Classification getClassification(Long id) {
        return classificationRepo.getOne(id);
    }

    @Override
    public Meal getMeal(Long id) {
        return mealRepo.getOne(id);
    }

    @Override
    public Animals getAnimal(Long id) {
        return animalsRepo.getOne(id);
    }

    @Override
    public Animals saveAnimal(Animals animal) {
        return animalsRepo.save(animal);
    }

    @Override
    public void deleteAnimal(Animals animal) {
        animalsRepo.delete(animal);
    }

    @Override
    public List<Habitat> getAllHabitat() {
        return habitatRepo.findAll();
    }

    @Override
    public Classification addCl(Classification classification) {
        return classificationRepo.save(classification);
    }

    @Override
    public Habitat getHabitat(Long id) {
        return habitatRepo.getOne(id);
    }
}
