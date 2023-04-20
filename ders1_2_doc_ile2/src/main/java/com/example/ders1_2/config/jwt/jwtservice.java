package com.example.ders1_2.config.jwt;

import com.example.ders1_2.model.Role;
import com.example.ders1_2.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class jwtservice {

    private Key key;

    @PostConstruct
    public void init()
    {
        byte [] keybytes;
        keybytes= Decoders.BASE64.decode("dGhpcyBpcyBteSBzZWNyZXQga2V5IHRoaXMgaXMgbXkgc2VjcmV0IGtleSB0aGlzIGlzIG15IHNlY3JldCBrZXkg");
        key= Keys.hmacShaKeyFor(keybytes);

    }

public Claims parseToken(String token)
{
    return  Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
}

    public String issuetoken(User user, Duration duration)
    {
        log.trace("Issue Jwt token to {} for {}",user,duration);
//        log.info("rollar -----{}",user.getRoles());
        List<Role> rollar = user.getRoles();
        List<String> stringrollar=new ArrayList<>();
        for (Role a:rollar)
        {
            stringrollar.add(a.getRole_name().toString().toLowerCase());
        }


        final JwtBuilder jwtbuilder= Jwts.builder()
                .setSubject(user.getName())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(duration)))
                .setHeader(Map.of("type","JWT"))
                .signWith(key, SignatureAlgorithm.HS256)
//                .addClaims(Map.of("role", List.of("user","admin")));
                .addClaims(Map.of("role", stringrollar));

        return jwtbuilder.compact();
    }

}
