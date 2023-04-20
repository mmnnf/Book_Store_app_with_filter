package com.example.ders1_2.service;

import com.example.ders1_2.dto.Logindto;
import com.example.ders1_2.dto.Loginjwt;

public interface securityservice {
    public Loginjwt login(Logindto request);
}
