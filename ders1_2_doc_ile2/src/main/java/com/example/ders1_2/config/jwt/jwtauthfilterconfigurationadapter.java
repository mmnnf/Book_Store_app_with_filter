package com.example.ders1_2.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class jwtauthfilterconfigurationadapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>

{
    private final jwtfilter jwtfilter;
    @Override
    public void configure (HttpSecurity htttp)
    {
        log.info("adding jwt filter");
        htttp.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

    }

}
