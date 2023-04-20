package com.example.ders1_2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper getmodelmapper()
    {
        return new ModelMapper();
    }

//    @Bean
//    @Scope("prototype")
//    public flyable getflayble()
//    {
//        return new bird();
//    }

    //ya yuxaridaki olmalidir ya da ki bird clasinin ustine @service yazilmalidir


    @Bean
    public String getstring()
    {
        return new String();  ////bu olmasa allargsconst gore erorr cixir ortaya
    }

    @Bean
    public Integer getInteger()
    {
        return 1;  ////bu olmasa allargsconst gore erorr cixir ortaya
    }


}
