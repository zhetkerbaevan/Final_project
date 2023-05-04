package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.controllers;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.*;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.AnimalService;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class APIController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private UserService userService;

    @GetMapping("/animals")
    public List<Animals> getAllAnimals(){
        return animalService.getAllAnimals();
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        System.out.println(userService.getAllUsers().get(1));
        return userService.getAllUsers();
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

   @PostMapping("addUser")
    public Users addNewUser(@RequestBody UserDto userDto){
       System.out.println(userDto);
       Users user = new Users();
       Gender gender = userService.getGender(userDto.getGender_id());
       System.out.println(gender);
       user.setUsername(userDto.getUsername());
       user.setPassword(userDto.getPassword());
       user.setName(userDto.getName());
       user.setSurname(userDto.getSurname());
       user.setEmail(userDto.getEmail());

       user.setGender(gender);
       user.setAbout_me(userDto.getAbout_me());
       user.setTicket(false);
       return userService.createUser(user);
   }

}
