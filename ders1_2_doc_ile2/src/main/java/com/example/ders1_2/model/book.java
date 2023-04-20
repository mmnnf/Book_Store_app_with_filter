package com.example.ders1_2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer year;
    private Integer count;
    @JsonIgnore
    @ManyToOne (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)/// cox kitab 1 tene type qarsiliq gelir
   // @ToString.Exclude
    private book_type type;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    //@ToString.Exclude
    private Author author;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    @ManyToOne
   // @ToString.Exclude
    private publisher publisher;


}
