package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RolesRepo extends JpaRepository<Roles, Long> {
    Roles findByRole(String role);
}
