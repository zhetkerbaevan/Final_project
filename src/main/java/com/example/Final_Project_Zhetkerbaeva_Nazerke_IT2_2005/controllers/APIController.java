package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.controllers;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Animals;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Classification;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class APIController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animals")
    public List<Animals> getAllAnimals(){
        return animalService.getAllAnimals();
    }

    @PostMapping("/addAnimal")
    public Animals addNewAnimal(@RequestBody Animals animal){
        return animalService.addAnimal(animal);
    }

    @GetMapping("/classification")
    public List<Classification> getALlCl(){
        return animalService.getAllClassification();
    }

    @PostMapping("/classification")
    public Classification addNewClassification(@RequestBody Classification classification){
        return animalService.addCl(classification);
    }

}
