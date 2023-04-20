package com.example.ders1_2.dto;

import com.example.ders1_2.model.Author;
import com.example.ders1_2.model.book_type;
import com.example.ders1_2.model.publisher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Bookdto {
    private Long id;
    private String name;
    private Integer year;
    private Integer count;
    private book_type type;
    private Author author;
    private   publisher publisher;
}
