package com.example.ders1_2.controller;

import com.example.ders1_2.dto.Authordto;
import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.methods.CheckingRole;
import com.example.ders1_2.model.User;
import com.example.ders1_2.model.book;
import com.example.ders1_2.repository.Custom_filtr;
import com.example.ders1_2.repository.UserRepository;
import com.example.ders1_2.repository.booksearchfilter;
import com.example.ders1_2.service.authorservice;
import com.example.ders1_2.service.bookservice;
import com.example.ders1_2.service.roleservices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {
    public final bookservice service;
    public final authorservice authorservice;
    UserRepository userRepository;
    private CheckingRole checkingRole;


    @GetMapping("/{id}")
    public Bookdto getbookbyid(@PathVariable Long id) {
        System.out.println(service.getbookbyid(id).getName());
        return service.getbookbyid(id);
    }


    //butun kitablar get edilir
    @GetMapping("/all")
    public List<Bookdto> getall() {
        return service.allbooks();
    }

    //butun kitablar get edilir
    @GetMapping("/allw")
    public List<book> getalwl() {
        return service.allbooksW();
    }


    //ASAGIDAKI SADECE ADLARINI GETIRIR BUTUN KITABLARIN:
    @GetMapping("/all2")
    public List<List<String>> getall2() {
        return service.allbooks2();
    }


    //authorlari id ile get edilir:
    @GetMapping("author/{id}")
    public Authordto getauthorbyid(@PathVariable Long id) {
        System.out.println(authorservice.getauthorbyid(id).getName());
        return authorservice.getauthorbyid(id);
    }

    @PostMapping("/createbook")
    public Bookdto createbook(@RequestBody Bookdto dto, Principal principal) {
        if (checkingRole.checkRole(principal, "Role_ADMIN") || checkingRole.checkRole(principal, "ROLE_Publisher")) {

            return service.createbook(dto);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }


    @PutMapping("/update")
    public Bookdto updatebook(@RequestBody Bookdto dto) {
        return service.updatebook(dto);
    }


    @DeleteMapping("/{id}")
    public void deletebook(@PathVariable Long id) {
        service.deletebook(id);
    }

    // ASAGIDAKI PUBLISHERLERIN KITABLARININ SIYAHISINI CIXARIR:
    @GetMapping("/pub_name/{name}")
    public List<Bookdto> findByPub_Name_withalgo(@PathVariable String name) {
        List<Bookdto> all = service.allbooks();
        List<Bookdto> findedbooks = new ArrayList<>();
        for (Bookdto x : all) {
            if (x.getPublisher().getName().equals(name)) {
                findedbooks.add(x);
            }
        }
        return findedbooks;
    }


    //ASAGIDAKI KITABLARI ADI ILE PAGE VASITESI ILE GETIRIR
    @GetMapping("/all/{name}")
    public List<book> findall(@PathVariable String name) {
        // Pageable paging = PageRequest.of(0, 2);
        return service.findByname(name);
    }

    @GetMapping("/w/{name}")
    public List<book> getnameandyearandcount(@PathVariable String name) {
//        return service.myjavafiltwithnvl(name,year,count);
        return service.findnameandyear(name);


    }


    //ANCAQ PUBLISHERIN OZUNE AID KITABLARI UPDATE EDE BILIR
    @PutMapping("/updatemy")
    public Bookdto updatebookbypublishers(@RequestBody Bookdto dto, Principal principal) {
        String username = principal.getName();

        User user = userRepository.findByName(username);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        String user_pub_name = user.getPublisher().getName();
        String wantedchange_pub_name = dto.getPublisher().getName();
        log.info("user pub name {}", user_pub_name);
        log.info("wanted change pub name {}", wantedchange_pub_name);
        if (user_pub_name.equals(wantedchange_pub_name)) {
            return service.updatebook(dto);
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN);


    }





}
