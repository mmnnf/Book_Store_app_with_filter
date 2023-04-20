package com.example.ders1_2.service;

import com.example.ders1_2.dto.Authordto;
import com.example.ders1_2.model.Author;
import com.example.ders1_2.repository.authorrepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service//bunun vasitesi ile dependency injection bas verir
public class authorimpl implements authorservice {
    private final authorrepository repository;  //???? BU NECE IMPLEMENTASIYASIZ ISLEYIR YENIki @RequiredArgsConstructor normal isleyir imp olmadan
    private final ModelMapper mapper;
    @Override
    public Authordto getauthorbyid(Long id) {
        Author byid=repository.findById(id).get();
        Authordto dto = mapper.map(byid, Authordto.class);
        return dto;
    }
}
