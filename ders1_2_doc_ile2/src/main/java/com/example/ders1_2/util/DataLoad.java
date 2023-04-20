package com.example.ders1_2.util;

import com.example.ders1_2.model.Role;
import com.example.ders1_2.model.User;
import com.example.ders1_2.model.publisher;
import com.example.ders1_2.repository.RoleRepository;
import com.example.ders1_2.repository.UserRepository;
import com.example.ders1_2.repository.publisherrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataLoad implements ApplicationRunner {

    public publisher getpublisher( String publishername) {

        publisher publisher = publisherrepository.findByname(publishername);
        return publisher;
    }
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private publisherrepository publisherrepository;

    @Autowired
    private RoleRepository roleRepository;

    public PasswordEncoder encoder2() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Admin save

        Role roleAdmin=Role.builder().id(1L).role_name("ROLE_ADMIN").build();
        Role roleUser=Role.builder().id(2L).role_name("ROLE_USER").build();
        Role rolePublisher=Role.builder().id(3L).role_name("ROLE_Publisher").build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        roleRepository.save(rolePublisher);


        List<Role> adminRoleList=new ArrayList<>();
        adminRoleList.add(roleAdmin);

        User admin=User.builder().id(3L).name("Burak").password(encoder2().encode("123")).roles(adminRoleList).build();
        userRepository.save(admin);

        //User save

        List<Role> userRoleList=new ArrayList<>();
        userRoleList.add(roleUser);

        User user=User.builder().id(4L).name("Ali").password(encoder2().encode("123")).roles(userRoleList).build();
        userRepository.save(user);

        List<Role> publisherRoleList=new ArrayList<>();
        publisherRoleList.add(rolePublisher);


        publisher publisher1=publisher.builder()
                .name("sina_yayincilik")
                .build();
        publisher publisher2=publisher.builder()
                .name("serq qerb nesriyyat")
                .build();

        publisherrepository.save(publisher1);
        publisherrepository.save(publisher2);

        System.out.println(getpublisher("sina_yayincilik") );
        User publ1=User.builder().id(7L).name("sina_yayincilik").password(encoder2().encode("123")).roles(publisherRoleList).publisher(publisher1).build();
        User publ2=User.builder().id(8L).name("serq qerb nesriyyat").password(encoder2().encode("123")).roles(publisherRoleList).publisher(publisher2).build();


        userRepository.save(publ1);
        userRepository.save(publ2);
        System.out.println(publ1.getPublisher().getName()+"checkkkk");
        System.out.println(publ1.getPublisher().getId()+"  checkkkk  id");

        //////////////////////////////////////////////////////
//        initializing values

        User user1=User.builder().id(5L).name("nergiz").password(encoder().encode("123")).roles(userRoleList).build();


        userRepository.save(user1);

        User user2=User.builder().id(6L).name("arife").password(encoder().encode("123")).roles(userRoleList).build();
        userRepository.save(user2);


        System.out.println("-- Roles --");
        roleRepository.findAll().forEach(System.out::println);
        System.out.println("-- User --");
        userRepository.findAll().forEach(System.out::println);
    }
}
