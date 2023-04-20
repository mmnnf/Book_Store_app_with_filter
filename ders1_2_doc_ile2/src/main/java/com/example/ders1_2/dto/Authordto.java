package com.example.ders1_2.dto;

import com.example.ders1_2.model.book;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Data
public class Authordto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer birthdate;
    private Integer book_count;
  //  private List<book> books;
}
