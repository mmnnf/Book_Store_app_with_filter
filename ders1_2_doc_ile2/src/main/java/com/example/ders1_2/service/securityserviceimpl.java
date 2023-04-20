package com.example.ders1_2.service;

import com.example.ders1_2.config.jwt.jwtservice;
import com.example.ders1_2.dto.Logindto;
import com.example.ders1_2.dto.Loginjwt;
import com.example.ders1_2.model.Role;
import com.example.ders1_2.model.User;
import com.example.ders1_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class securityserviceimpl implements securityservice{
    private final UserRepository userRepository;
    private final jwtservice jwtservice;


    public List<Role> getrolesbyname( String username) {
        return userRepository.findByName(username).getRoles();
    }

    @Bean
    public PasswordEncoder encoder2() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public Loginjwt login(Logindto request) {
        User user=userRepository.findByName(request.getUsername());
        log.info("###{}",user.getPassword());
        log.info("###{}",encoder2().encode(request.getPassword()));
        log.info("###{}",encoder2().encode("123"));
        log.info("###{}", request.getPassword() );
        if(encoder2().matches(request.getPassword(),user.getPassword()) )
       {
           Loginjwt response=new Loginjwt();
           response.setUsername(user.getName());
           Role rol=getrolesbyname(user.getName()).get(0);
           response.setRole(rol.getRole_name());

           String jwt=jwtservice.issuetoken(user, Duration.ofHours(1));

           response.setJwttoken(jwt);
           return response;
       }
       else {
           log.info("_____________SIFRE DUZ DEYIL_______");
           return null;
       }
    }
}
