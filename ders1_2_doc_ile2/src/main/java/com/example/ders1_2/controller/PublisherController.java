package com.example.ders1_2.controller;

import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.User;
import com.example.ders1_2.model.book;
import com.example.ders1_2.model.publisher;
import com.example.ders1_2.repository.UserRepository;
import com.example.ders1_2.repository.publisherrepository;
import com.example.ders1_2.service.bookservice;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/publisher")
//@RequiredArgsConstructor
@AllArgsConstructor
public class PublisherController {
    UserRepository userRepository;
    publisherrepository  publisherrepository;
    public final bookservice service;


    @GetMapping("/user2")
    @ResponseBody
    public String getuser(Principal principal) {

        String username= principal.getName();

        User user = userRepository.findByName(username);
        return user.getName();
    }

    @GetMapping("/findpublisher/{publishername}")
    @ResponseBody
    public publisher getpublisher(@PathVariable String publishername) {

        publisher publisher = publisherrepository.findByname(publishername);

        //publisher publisher = publisherrepository.findByName("sina_yayincilik");
        return publisher;
    }


    @GetMapping("/role")
    @ResponseBody
    public String getrole(Principal principal) {

        String username= principal.getName();

        User user = userRepository.findByName(username);
        // System.out.println(user.getRoles()+"rolu");
        return user.getRoles().get(0).getRole_name();
    }

    @GetMapping("/getmybooks")
    @ResponseBody
    public List<book> getmybooks(Principal principal) {
        String username= principal.getName();
        User user = userRepository.findByName(username);
        System.out.println(user.getName());
        System.out.println(user.getPublisher().getId());
        return user.getPublisher().getBooks();
    }

    @GetMapping("/getpublisher")
    @ResponseBody
    public publisher getpublisher(Principal principal) {

        String username= principal.getName();

        User user = userRepository.findByName(username);
        System.out.println(user.getName());
        System.out.println(user.getPublisher().getId());
        return user.getPublisher();

    }

    @GetMapping("/all")
    public List<Bookdto> getall() {
        return service.allbooks();
    }


    @GetMapping("/paging")
    public Page<publisher> pagination (Pageable pageable) {
        return publisherrepository.findAll(pageable);
    }

}
