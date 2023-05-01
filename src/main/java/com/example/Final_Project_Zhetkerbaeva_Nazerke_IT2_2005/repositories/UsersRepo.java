package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Users;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
