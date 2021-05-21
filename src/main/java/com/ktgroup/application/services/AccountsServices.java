package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ktgroup.application.dto.AccountForm;
import com.ktgroup.application.entities.Account;
import com.ktgroup.application.entities.Role;
import com.ktgroup.application.responsitories.AccountsRespository;
import com.ktgroup.application.responsitories.RoleRespository;
import com.ktgroup.application.utils.EncrytedPasswordUtils;

@Service
public class AccountsServices {

    @Autowired
    private AccountsRespository accountsRespository;
    
    @Autowired
    private RoleRespository roleRespository;
    
    public Account getAccountByUserName(String userName) {
        return accountsRespository.findByUserName(userName);
    }
    

    public List<Account> findAllAccount() {
        List<Account> listUser = accountsRespository.findAll();
        if (listUser != null) {
            return listUser;
        }
        return new ArrayList<Account>();
    }
    
    @Transactional
    public void createAccount(AccountForm appUserForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
        Account account = new Account();
        account.setUserName(appUserForm.getUserName());
        account.setFirstName(appUserForm.getFirstName());
        account.setLastName(appUserForm.getLastName());
        account.setEmail(appUserForm.getEmail());
        account.setGender(appUserForm.getGender());
        account.setEncrytedPassword(encrytedPassword);
        Role role = roleRespository.getOne(new Long(2));
        account.setRole(role);
        accountsRespository.save(account);
    }
    
    public Account getAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginedUser = (User) auth.getPrincipal();
        String userNameLogin = loginedUser.getUsername();
        return accountsRespository.findByUserName(userNameLogin);
    }
}
