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
public class publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToOne(cascade = CascadeType.PERSIST)
//    private User user;
    @OneToMany(mappedBy="publisher")
    @ToString.Exclude
  //  @JsonIgnore
    private List<book> books;

    @Override
    public String toString() {
        return name;
    }
}
