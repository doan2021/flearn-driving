package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktgroup.application.dto.AppUserForm;
import com.ktgroup.application.entities.AppUser;
import com.ktgroup.application.entities.Role;
import com.ktgroup.application.responsitories.AppUserRespository;
import com.ktgroup.application.responsitories.RoleRespository;
import com.ktgroup.application.utils.EncrytedPasswordUtils;

@Service
public class AppUserServices {

    @Autowired
    private AppUserRespository appUserRespository;

    @Autowired
    private CommonServices commonServices;
    
    @Autowired
    private RoleRespository roleRespository;

    public List<AppUser> findAllUser() {
        List<AppUser> listUser = appUserRespository.findAll();
        if (listUser != null) {
            return listUser;
        }
        return new ArrayList<AppUser>();
    }
    
    @Transactional
    public void createAppUser(AppUserForm appUserForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
        AppUser loginUser = commonServices.getUserLogin();
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserForm.getUserName());
        appUser.setFirstName(appUserForm.getFirstName());
        appUser.setLastName(appUserForm.getLastName());
        appUser.setEmail(appUserForm.getEmail());
        appUser.setGender(appUserForm.getGender());
        appUser.setEncrytedPassword(encrytedPassword);
        appUser.setCreateAt(loginUser.getUserName());
        appUser.setUpdateAt(loginUser.getUserName());
        appUser.setCreateDate(new Date());
        appUser.setUpdateDate(new Date());
        Role role = roleRespository.getOne(appUserForm.getRole());
        appUser.setRole(role);
        appUserRespository.save(appUser);
    }
}
