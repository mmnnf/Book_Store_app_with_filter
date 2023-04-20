package com.example.ders1_2;

import com.example.ders1_2.config.jwt.jwtservice;
import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.*;
import com.example.ders1_2.repository.*;
import com.example.ders1_2.service.bookserviceimpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@AllArgsConstructor

@EnableConfigurationProperties
@SpringBootApplication
@Transactional
@Slf4j
public class Ders12Application implements CommandLineRunner {
private final jwtservice jwtservice;
//    private final ModelMapper mapper;
    private  final  bookrepository bookrepo;
    @Autowired
    private com.example.ders1_2.repository.publisherrepository publisherrepository;
    @Autowired
    bookserviceimpl servis;
    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    public publisher getpublisher( String publishername) {

        //publisher publisher = publisherrepository.findByName(publishername);
        publisher publisher = publisherrepository.findByname(publishername);
        return publisher;
    }
    public static void main(String[] args) {
        SpringApplication.run(Ders12Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {



//////////////////////////////////////////////////
//   <----------  KITAB UCUN EMELIYYATLAR   ---------->




        book book1=book.builder()
                .name("harry_potter")
                .year(2000)
                .count(500)
                .build();
        book book2=book.builder()
                .name("cinayet_ve_ceza")
                .year(2000)
                .count(350)
                .build();
        book book3=book.builder()
                .name("kazamarov_qardaslari")
                .year(1885)
                .count(250)
                .build();


        Author author1= Author.builder()
                .birthdate(1970)
                .book_count(12)
                .name("lllll")
                .surname("kkkkk")
                .books(List.of(book1,book2,book3))
                .build();


//

//
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author1);
//        publisher publisher1=publisher.builder()
//                .name("sina_yayincilik")
//                .books(List.of(book1,book2))
//                .build();
//        publisher publisher2=publisher.builder()
//                .name("serq qerb nesriyyat")
//                .books(List.of(book3))
//                .build();

        publisher publisherx1= getpublisher("serq qerb nesriyyat");
        System.out.println(publisherx1+"))))");
        //publisherx1.setBooks(List.of(book1,book2));

        publisher publisherx2= getpublisher("sina_yayincilik");
        System.out.println(publisherx2+"))))");
//        publisherrepository.save(publisherx2);

        book1.setPublisher(publisherx1);
        book2.setPublisher(publisherx1);
        book3.setPublisher(publisherx2);
        publisherrepository.save(publisherx1);
        publisherrepository.save(publisherx2);

        bookrepo.save(book1);
        bookrepo.save(book2);
        bookrepo.save(book3);

//        /////////////////////sorgu
////        // KICIK BIR SORGU: id-si 1 olan marketin adini getirir.
////        branch byId = branchrepo.getById(1l);
////        String name=byId.getMarkett().getName();
////        System.out.println(name);
//
//
////        book byId = bookrepo.getById(1L);
////        System.out.println( byId.getName());
////        System.out.println(byId.getAuthor().getName());
        Bookdto response = servis.getbookbyid(1l);

        ////||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||


         Role rol=new Role();
         rol.setRole_name("ADMIN");
         User taleh=User.builder()
                 .name("taleh")
                 .password(encoder().encode("123"))
                 .roles(List.of(rol))
                 .publisher(null)
                 .build();
         userRepository.save(taleh);
         String jwt=jwtservice.issuetoken(taleh, Duration.ofHours(1));
         log.info(jwt);
//         log.info(jwtservice.parseToken(jwt).toString());
        log.info("parse jwt {}",jwtservice.parseToken(jwt));


        ///////////////////////////////////////////
        ///IKINCI USER JWT ILE
        Role rol2=new Role();
        rol2.setRole_name("ADMIN2");
        User nergiz=User.builder()
                .name("naargiz")
                .password(encoder().encode("456"))
                .roles(List.of(rol2))
                .publisher(null)
                .build();
        userRepository.save(nergiz);
        String jwt2=jwtservice.issuetoken(nergiz, Duration.ofHours(1));
        log.info(jwt2);
//         log.info(jwtservice.parseToken(jwt).toString());
        log.info("parse jwt {}",jwtservice.parseToken(jwt2));

        /////#####################################3
//        book book=mapper.map(dto,book.class);
//        book save=repository.save(book);
//        return mapper.map(save,bookdto.class);
    }
}
