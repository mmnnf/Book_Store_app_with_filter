package com.example.ders1_2.config.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class jwtfilter extends OncePerRequestFilter {
    private final jwtservice jwtservice;
    private final static String autorization_header="Authorization";
    private final static String bearer_header="Bearer";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String autorization =request.getHeader(autorization_header);
        if (autorization!=null)
        {
            String tokenfromheader=gettokenfromheader(autorization);
            log.info("auth header {}",autorization);
            log.info("bearer token {}",tokenfromheader);
            Claims claims = jwtservice.parseToken(tokenfromheader);
            Authentication autentificationBearer = getAutentificationBearer(claims);
            SecurityContextHolder.getContext().setAuthentication(autentificationBearer);
            //YUXARIDAKI SETRDEN SONRA http://localhost:8080/hello LINKINDE GET EDENDE JWT TOKEN ILE LINK ISLEYIR VE SALAM SOZU CIXIR

            log.info("claims {}",claims);
        }

        filterChain.doFilter(request,response);
    }
    private String gettokenfromheader(String autorization)
    {
        if (  autorization.startsWith(bearer_header))
        {
           return autorization.substring(bearer_header.length());
        }
        else
        {
            return autorization;
        }
       //throw new RuntimeException("bearer token not found");
    }

    private Authentication getAutentificationBearer (Claims claims)
    {
        List<?> roles=claims.get("role",List.class);
        List<GrantedAuthority> authorityList=roles
                .stream()
                .map(a-> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return  new UsernamePasswordAuthenticationToken(claims.getSubject(),"",authorityList);


    }

}
