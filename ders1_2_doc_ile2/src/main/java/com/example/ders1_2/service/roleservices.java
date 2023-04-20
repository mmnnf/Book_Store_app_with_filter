package com.example.ders1_2.service;

import com.example.ders1_2.model.Role;
import com.example.ders1_2.model.User;
import com.example.ders1_2.model.book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface roleservices {
     List<Role> getrolesbyname( Principal principal);
}
