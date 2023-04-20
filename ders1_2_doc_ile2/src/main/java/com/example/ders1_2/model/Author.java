package com.example.ders1_2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer birthdate;
    private Integer book_count;
    @ToString.Exclude
    @OneToMany(mappedBy="author")
  //  @JsonIgnore
    private List<book> books;
    public String toString() {
        return name+" "+surname;
    }
}
