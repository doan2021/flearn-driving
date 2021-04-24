package com.ktgroup.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ktgroup.application.entities.AppUser;
import com.ktgroup.application.responsitories.AppUserRespository;

@Service
public class CommonServices {
    
    @Autowired
    AppUserRespository appUserRespository;
    
    public AppUser getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return appUserRespository.findByUserName(authentication.getName());
    }
}
