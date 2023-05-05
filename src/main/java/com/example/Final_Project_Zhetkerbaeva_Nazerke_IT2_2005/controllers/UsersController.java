package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.controllers;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.*;
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


import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    String error;
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(secUser.getUsername());
            return currentUser;
        }
        return null;
    }

    @PostMapping("save_profile")
    public String save_profile(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "email_field") String email,
                               @RequestParam(name = "password_field") String password,
                               @RequestParam(name = "name_field") String name,
                               @RequestParam(name = "surname_field") String surname,
                               @RequestParam(name = "about_field") String about,
                               @RequestParam(name = "gender_field") Long gender_id) {

        Users user = userService.getUser(id);

        Gender gender = userService.getGender(gender_id);

        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setGender(gender);
        user.setAbout_me(about);
        userService.saveUser(user);
        return "redirect:/profile";
    }

    @GetMapping(value="/edit_profile/{idshka}")
    public String profile_details(Model model, @PathVariable(name="idshka") Long user_id){

        List<Gender> gender_list = userService.getAllGender();
        model.addAttribute("gender_list", gender_list);

        Users user = userService.getUser(user_id);
        model.addAttribute("currentUser",user);
        return "edit_profile";
    }

    @GetMapping(value="register")
    public String register(Model model){
        List<Gender> gender_list = userService.getAllGender();
        model.addAttribute("gender_list", gender_list);
        model.addAttribute("error", error);
        error="";
        return "register";
    }


    @PostMapping(value = "/register")
    public String toRegister(@RequestParam(name = "username_field") String username,
                             @RequestParam(name = "email_field") String email,
                             @RequestParam(name = "password_field") String password,
                             @RequestParam(name = "re_password_field") String rePassword,
                             @RequestParam(name = "name_field") String name,
                             @RequestParam(name = "surname_field") String surname,
                             @RequestParam(name = "about_field") String about,
                             @RequestParam(name = "gender_field") Long gender_id){


        //System.out.println("Role_id " + role_id);
        Gender gender = userService.getGender(gender_id);
        //System.out.println("gender: " + gender);
            if (password.equals(rePassword)) {
                Users newUser = new Users();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setEmail(email);
                newUser.setGender(gender);
                newUser.setAbout_me(about);
                newUser.setTicket(false);

                System.out.println(newUser);
                if (userService.createUser(newUser) != null) {
                    return "redirect:/login";
                } else {
                    error = "Username exists ";
                }
            }
            else {
                error = "password doesn't match ";
            }


        return "redirect:/register";
    }

    @GetMapping("/users")
    public String users(Model model){

        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List<Gender> gender = userService.getAllGender();
        model.addAttribute("gender", gender);

        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("currentUser", getUserData());
        return "users";
    }

    @GetMapping(value="/edit_user/{idshka}")
    public String details(Model model, @PathVariable(name="idshka") Long user_id){
        Users user = userService.getUser(user_id);
        model.addAttribute("user", user);

        List<Gender> genders = userService.getAllGender();
        model.addAttribute("genders", genders);

        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("currentUser", getUserData());
        return "edit_user";
    }

    //user by admin
    @PostMapping("save_user")
    public String save_user(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "email_field") String email,
                               @RequestParam(name = "password_field") String password,
                               @RequestParam(name = "name_field") String name,
                               @RequestParam(name = "surname_field") String surname,
                               @RequestParam(name = "about_field") String about,
                               @RequestParam(name = "gender_field") Long gender_id) {

        Users user = userService.getUser(id);
        Gender gender = userService.getGender(gender_id);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setGender(gender);
        user.setAbout_me(about);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/assignroles")
    public String assignRoles(@RequestParam(name = "user_id") Long user_id,
                                 @RequestParam(name = "role_id") Long role_id) {

        Roles role = userService.getRole(role_id);
        if (role != null) {
            Users user = userService.getUser(user_id);
            if (user != null) {
                List<Roles> roles = user.getRoles();
                if (roles == null) {
                    roles = new ArrayList<>();
                }
                else if (!(roles.contains(role))){
                    roles.add(role);
                }
                userService.saveUser(user);
                return "redirect:/edit_user/" + user_id;
            }
        }

        return "redirect:/users";
    }

    @GetMapping("/add_user_url")
    public String add_user_url(Model model){
        model.addAttribute("error", error);
        error="";

        List<Gender> gender_list = userService.getAllGender();
        model.addAttribute("gender_list", gender_list);
        model.addAttribute("currentUser", getUserData());
        return "add_user";
    }

    @PostMapping(value = "/add_user")
    public String add_user(@RequestParam(name = "username_field") String username,
                             @RequestParam(name = "email_field") String email,
                             @RequestParam(name = "password_field") String password,
                             @RequestParam(name = "re_password_field") String rePassword,
                             @RequestParam(name = "name_field") String name,
                             @RequestParam(name = "surname_field") String surname,
                             @RequestParam(name = "about_field") String about,
                             @RequestParam(name = "gender_field") Long gender_id){


        //System.out.println("Role_id " + role_id);
        Gender gender = userService.getGender(gender_id);
        //System.out.println("gender: " + gender);
        if (password.equals(rePassword)) {
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setEmail(email);
            newUser.setGender(gender);
            newUser.setAbout_me(about);
            newUser.setTicket(false);

            System.out.println(newUser);
            if (userService.createUser(newUser) != null) {
                return "redirect:/users";
            } else {
                error = "Username exists ";
            }
        }
        else {
            error = "password doesn't match ";
        }


        return "redirect:/add_user";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfile(@RequestParam(name="id") Long id){
        Users user = userService.getUser(id);
        if(user != null){
            userService.deleteUser(user);
        }
        return "redirect:/logout";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(name="id") Long id){
        Users user = userService.getUser(id);
        if(user != null){
            userService.deleteUser(user);
        }
        return "redirect:/users";
    }
}
