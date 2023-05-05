package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Gender;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Roles;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users getUserByUsername(String username);
    List<Users> getAllUsers();
    Users createUser(Users user);
    Users saveUser(Users user);
    Users getUser(Long id);

    List<Gender> getAllGender();
    Gender getGender(Long id);

    List<Roles> getAllRoles();

    Roles getRole(Long id);

    void deleteUser(Users user);
}
