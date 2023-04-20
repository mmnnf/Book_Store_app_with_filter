package com.example.ders1_2.controller;

import com.example.ders1_2.dto.Logindto;
import com.example.ders1_2.dto.Loginjwt;
import com.example.ders1_2.service.securityservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final securityservice securityservice;
    @PostMapping("/login")
    public Loginjwt login(@RequestBody Logindto request )
    {
        Loginjwt loginjwt=securityservice.login(request);
        if (loginjwt!=null)
        {
            return loginjwt;
        }
           // throw  new RuntimeException("user tapilmadi");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"user tapilmadi");
    }
//    @PostMapping("/loginn")
//    public loginjwt loginn( @RequestBody loginjwt request )
//    {
//        return request;
//    }
//    @PostMapping("/loginw")
//    public loginjwt loginw( @RequestBody loginjwt request )
//    {
//        return request;
//    }
//    @GetMapping("/login")
//    public String loging(   )
//    {
//        return "request";
//    }
//    @PostMapping("/login2")
//    public String login2(  )
//    {
//        log.info("wwwwwwww");
//       return "heyyy";
//
//
//    }
//    @PostMapping("/login3")
//    public String login3(@RequestBody logindto request  )
//    {
//        log.info("wwwwwwww");
//        return "heyyy";
//
//
//    }

}
