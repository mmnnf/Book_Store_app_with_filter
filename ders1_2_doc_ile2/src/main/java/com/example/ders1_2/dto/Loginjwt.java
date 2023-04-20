package com.example.ders1_2.dto;

import lombok.Data;


@Data
public class Loginjwt {
    String username;
    String role;
    String jwttoken;
}
