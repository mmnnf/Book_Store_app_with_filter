package com.example.ders1_2.controller;

import com.example.ders1_2.model.book;
import com.example.ders1_2.repository.Custom_filtr;
import com.example.ders1_2.repository.booksearchfilter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/filtr")
@AllArgsConstructor
public class BookFilterController {

    private Custom_filtr customfiltr;
    private  booksearchfilter booksearchfilter;


    //DYNAMIC FILTR TAM VERSIYA menim metodumla(ancaq sql ile):
    @GetMapping("/1/{filtr}")
    public List<book> myfiltrnameyearcountfullmap(@PathVariable String filtr) {

        Map<String, String> myMap = new HashMap<String, String>();
        String s = filtr;
        String[] pairs = s.split("&");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            myMap.put(keyValue[0],  keyValue[1]);
        }
        String name,year,count;
        name=year=count="null";
        if (myMap.get("name")!=null)
        {
            name=myMap.get("name");

        }
        if (myMap.get("year")!=null)
        {
            year=myMap.get("year");

        }
        if (myMap.get("count")!=null)
        {
            count=myMap.get("count");

        }
        String query = customfiltr.findAllquery();
        boolean firstparam = false;
        if (!name.equals("null")) {
            if (firstparam == true)
                query = query + " and ";
            query = customfiltr.addnamefiltr(query, name);
            firstparam = true;
        }

        if (!year.equals("null")) {
            if (firstparam == true)
                query = query + " and ";
            query = customfiltr.addyearfiltr(query, year);
            firstparam = true;
        }

        if (!count.equals("null")) {
            if (firstparam == true)
                query = query + " and ";
            query = customfiltr.addcountfiltr(query, count);
            firstparam = true;
        }
        return customfiltr.findbyQueryString(query);
    }


    //DYNAMIC FILTR TAM VERSIYA hazir funksiya ile:
    @GetMapping("/2/{filtr}")
    public List<book> hazirmodulilefiltrrealurl10(@PathVariable String filtr) {
        Map<String, String> myMap = new HashMap<String, String>();
        String s = filtr;
        String[] pairs = s.split("&");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            myMap.put(keyValue[0],  keyValue[1]);
        }
        String name;
        Integer year,count;
        name=null;
        year=count=null;

        if (myMap.get("name")!=null)
        {
            String tempname=myMap.get("name");
            name=tempname;

        }
        if (myMap.get("year")!=null)
        {
            String tempyear=myMap.get("year");
            year=Integer.parseInt(tempyear);

        }
        if (myMap.get("count")!=null)
        {
            String tempcount=myMap.get("count");
            count=Integer.parseInt(tempcount);

        }
        return booksearchfilter.findallbyquery3(name,year,count);
    }

}
