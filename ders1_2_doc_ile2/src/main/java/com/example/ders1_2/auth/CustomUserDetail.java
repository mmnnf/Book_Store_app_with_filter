package com.example.ders1_2.auth;

import com.example.ders1_2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
public class CustomUserDetail implements UserDetails {

    private String name;
    private String password;
    private List<GrantedAuthority> roles;


    public CustomUserDetail(User user){

        this.name=user.getName();
        this.password=user.getPassword();
        this.roles=user.getRoles().stream().map((role -> new SimpleGrantedAuthority(role.getRole_name()))).collect(Collectors.toList());
        log.info("CustomUserDetail a girdi");
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

