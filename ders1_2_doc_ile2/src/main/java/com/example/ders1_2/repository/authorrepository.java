package com.example.ders1_2.repository;

import com.example.ders1_2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface authorrepository extends JpaRepository<Author,Long> {
}
