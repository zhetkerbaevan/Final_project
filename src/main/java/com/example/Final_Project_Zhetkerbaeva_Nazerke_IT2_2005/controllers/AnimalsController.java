package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.controllers;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.*;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.AnimalService;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AnimalService animalService;
    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(secUser.getUsername());
            return currentUser;
        }
        return null;
    }
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String main(Model model) {
        model.addAttribute("currentUser", getUserData());
        List<Animals> animals = animalService.getAllAnimals();
        for (Animals animal : animals) {
            char[] chars = animal.getDescription().toString().toCharArray();
            if(chars.length > 100){
                animal.setDescription(animal.getDescription().toString().substring(0, 100) + "...");
            } else {
                animal.setDescription(animal.getDescription() + "...");
            }
        }
        model.addAttribute("animals", animals);
        return "main";
    }


    @GetMapping("/animals")
    @PreAuthorize("isAuthenticated()")
    public String animals(Model model) {
        List<Animals> animals = animalService.getAllAnimals();
        model.addAttribute("animals", animals);

        List<Classification> classifications = animalService.getAllClassification();
        model.addAttribute("classifications", classifications);

        List<Meal> meals = animalService.getAllMeal();
        model.addAttribute("meals", meals);

        List<Habitat> habitats = animalService.getAllHabitat();
        model.addAttribute("habitats", habitats);

        model.addAttribute("currentUser", getUserData());

        return "animals";
    }

    @PostMapping("add_animal")
    @PreAuthorize("isAuthenticated()")
    public String add_animal(@RequestParam(name="type_field") String type,
                             @RequestParam(name="amount_field") int amount,
                             @RequestParam(name="desc_field") String description,
                             @RequestParam(name="photo_field") String photo_url,
                             @RequestParam(name="classification_id") Long classification_id,
                             @RequestParam(name="meal_id") Long meal_id,
                             @RequestParam(name="habitat_id") Long habitat_id) {

        Classification classification = animalService.getClassification(classification_id);
        Meal meal = animalService.getMeal(meal_id);
        Habitat habitat = animalService.getHabitat(habitat_id);

        Animals animal = new Animals(null, type, amount, description, photo_url, meal, classification, habitat);
        animalService.addAnimal(animal);
        return "redirect:/animals";
    }

    @GetMapping(value="/edit_animal/{idshka}")
    public String details(Model model, @PathVariable(name="idshka") Long animal_id){
        Animals animal = animalService.getAnimal(animal_id);
        model.addAttribute("animal", animal);

        List<Classification> classifications = animalService.getAllClassification();
        model.addAttribute("classifications", classifications);

        List<Meal> meals = animalService.getAllMeal();
        model.addAttribute("meals", meals);

        List<Habitat> habitats = animalService.getAllHabitat();
        model.addAttribute("habitats", habitats);

        model.addAttribute("currentUser", getUserData());
        return "edit_animal";
    }
    @PostMapping("save_animal")
    public String save_animal(@RequestParam(name="id") Long id,
                              @RequestParam(name="type_field") String type,
                              @RequestParam(name="amount_field") int amount,
                              @RequestParam(name="desc_field") String description,
                              @RequestParam(name="photo_field") String photo_url,
                              @RequestParam(name="classification_id") Long classification_id,
                              @RequestParam(name="meal_id") Long meal_id,
                              @RequestParam(name="habitat_id") Long habitat_id) {

        Animals animal = animalService.getAnimal(id);

        Classification classification = animalService.getClassification(classification_id);
        Meal meal = animalService.getMeal(meal_id);
        Habitat habitat = animalService.getHabitat(habitat_id);

        animal.setType(type);
        animal.setAmount(amount);
        animal.setDescription(description);
        animal.setPhoto(photo_url);
        animal.setClassification(classification);
        animal.setMeal(meal);
        animal.setHabitat(habitat);

        animalService.saveAnimal(animal);
        return "redirect:/animals";
    }
    @GetMapping("/add_animal_url")
    public String add_animal_url(Model model){
        List<Animals> animals = animalService.getAllAnimals();
        model.addAttribute("animals", animals);

        List<Classification> classifications = animalService.getAllClassification();
        model.addAttribute("classifications", classifications);

        List<Meal> meals = animalService.getAllMeal();
        model.addAttribute("meals", meals);

        List<Habitat> habitats = animalService.getAllHabitat();
        model.addAttribute("habitats", habitats);

        model.addAttribute("currentUser", getUserData());

        return "add_animal";
    }

    @PostMapping("/deleteAnimal")
    public String deleteAnimal(@RequestParam(name="id") Long id){
        Animals animal = animalService.getAnimal(id);
        if(animal != null){
            animalService.deleteAnimal(animal);
        }
        return "redirect:/animals";
    }
}
