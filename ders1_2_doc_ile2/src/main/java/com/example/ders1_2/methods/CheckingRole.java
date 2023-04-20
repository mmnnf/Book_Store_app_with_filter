package com.example.ders1_2.methods;

import com.example.ders1_2.model.Role;
import com.example.ders1_2.service.roleservices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class CheckingRole {
    private final roleservices roleservices;
    public Boolean checkRole(Principal principal, String role)
    {
        log.info("girish");
        List<Role> rol= roleservices.getrolesbyname(principal);
        log.info("buracan geldi");
        for (Role a:rol)
        {
            if (a.getRole_name().equalsIgnoreCase(role))
            {
                return true;
            }
        }
        return false;
    }
}
