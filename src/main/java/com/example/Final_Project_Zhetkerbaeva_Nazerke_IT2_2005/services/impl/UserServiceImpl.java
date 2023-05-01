package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.impl;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Gender;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Roles;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Users;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.GenderRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.RolesRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories.UsersRepo;
import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    GenderRepo genderRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser = usersRepo.findByUsername(s);
        if(myUser != null){
            User secUser = new User(myUser.getUsername(), myUser.getPassword(), myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Users getUserByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    @Override
    public Users createUser(Users user) {
        Users checkUser = usersRepo.findByUsername(user.getUsername());
        if(checkUser == null){
            Roles role = rolesRepo.findByRole("USER");
            if(role!=null)
            {
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return usersRepo.save(user);
            }
        }
        return null;
    }

    @Override
    public Users getUser(Long id) {
        return usersRepo.getOne(id);
    }

    @Override
    public Users saveUser(Users users) {
        return usersRepo.save(users);
    }

    @Override
    public List<Gender> getAllGender() {
        return genderRepo.findAll();
    }

    @Override
    public Gender getGender(Long id) {
        return genderRepo.getOne(id);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepo.findAll();
    }

    @Override
    public Roles getRole(Long id) {
        return rolesRepo.getOne(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }
}