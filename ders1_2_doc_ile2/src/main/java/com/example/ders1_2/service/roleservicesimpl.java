package com.example.ders1_2.service;

import com.example.ders1_2.model.Role;
import com.example.ders1_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class roleservicesimpl implements roleservices {
    private final UserRepository userRepository;
    @Override
    public List<Role> getrolesbyname( Principal principal) {
       return userRepository.findByName(principal.getName()).getRoles();
    }


}
