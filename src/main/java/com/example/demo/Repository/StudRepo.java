package com.example.demo.Repository;

import com.example.demo.Model.stud_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface StudRepo extends JpaRepository<stud_table, Integer> {

    stud_table findByUsername(String username);
}
