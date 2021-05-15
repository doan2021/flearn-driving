package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktgroup.application.dto.AccountForm;
import com.ktgroup.application.entities.Accounts;
import com.ktgroup.application.entities.Role;
import com.ktgroup.application.responsitories.AccountsRespository;
import com.ktgroup.application.responsitories.RoleRespository;
import com.ktgroup.application.utils.EncrytedPasswordUtils;

@Service
public class AccountsServices {

    @Autowired
    private AccountsRespository appUserRespository;
    
    @Autowired
    private RoleRespository roleRespository;

    public List<Accounts> findAllAccount() {
        List<Accounts> listUser = appUserRespository.findAll();
        if (listUser != null) {
            return listUser;
        }
        return new ArrayList<Accounts>();
    }
    
    @Transactional
    public void createAccount(AccountForm appUserForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
        Accounts account = new Accounts();
        account.setUserName(appUserForm.getUserName());
        account.setFirstName(appUserForm.getFirstName());
        account.setLastName(appUserForm.getLastName());
        account.setEmail(appUserForm.getEmail());
        account.setGender(appUserForm.getGender());
        account.setEncrytedPassword(encrytedPassword);
        Role role = roleRespository.getOne(new Long(2));
        account.setRole(role);
        appUserRespository.save(account);
    }
}
