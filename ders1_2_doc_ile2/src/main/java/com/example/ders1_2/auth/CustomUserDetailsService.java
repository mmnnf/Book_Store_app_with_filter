package com.example.ders1_2.auth;

import com.example.ders1_2.model.User;
import com.example.ders1_2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       log.info("ILKINNNNNN NULLLL {}",username==null);
        User user = userRepository.findByName(username);
//        User user = userRepository.findByName("taleh");
        if (user==null)
            log.info("NNNNUUULLLL");
        return new CustomUserDetail(user);
    }
}
