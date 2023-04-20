package com.example.ders1_2.service;

import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface bookservice {
    Bookdto getbookbyid(Long id);
    Bookdto createbook(Bookdto dto);
    void deletebook(Long id);
    Bookdto updatebook(Bookdto dto);
    List<Bookdto> allbooks();
    List<List<String>> allbooks2();
    Bookdto detailsbook(Long id);
//    bookdto createbooksecure(bookdto dto);
    List<Bookdto> findByPub_Name(String name);
    List<book> findByname(String name);
//    List<book> findBynamewp(String name,Integer year ,Integer count,String signyear,String signcount);
    List<book> findnameandyear(String name);

    List<book> allbooksW();
    List<book> myjavafiltr(Integer year,Integer count);

    List<book> myjavafiltwithnvl(String name,Integer year,Integer count);
// List <book> findwithfilter(String f1);
//  List<book> findBookByName(String name);

}
