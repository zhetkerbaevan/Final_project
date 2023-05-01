package com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.repositories;

import com.example.Final_Project_Zhetkerbaeva_Nazerke_IT2_2005.entities.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HabitatRepo extends JpaRepository<Habitat, Long> {
}
