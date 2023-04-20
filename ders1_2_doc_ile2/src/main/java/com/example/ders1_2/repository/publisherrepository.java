package com.example.ders1_2.repository;

import com.example.ders1_2.model.User;
import com.example.ders1_2.model.publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface publisherrepository extends JpaRepository<publisher,Long> {
//    publisher findByName(String userName);
    publisher findByname(String name);

}
