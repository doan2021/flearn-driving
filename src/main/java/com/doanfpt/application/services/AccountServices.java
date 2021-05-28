package com.doanfpt.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doanfpt.application.dto.AccountForm;
import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.AuthenticationProvider;
import com.doanfpt.application.entities.Role;
import com.doanfpt.application.model.AccountPrincipal;
import com.doanfpt.application.responsitories.AccountsRespository;
import com.doanfpt.application.responsitories.RoleRespository;
import com.doanfpt.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

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
    
    public Account findByEmail(String email) {
        return accountsRespository.findByEmail(email);
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
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return accountsRespository.findByEmail(loginedUser.getEmail());
    }

    @Transactional
    public void createAccountAfterOAuthLoginSuccess(String email, String firstName, String lastName, AuthenticationProvider authenticationProvider) {
        Account account = new Account();
        account.setUserName(email);
        account.setEmail(email);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDelete(false);
        account.setAuthProvider(authenticationProvider.toString());
        account.setEncrytedPassword("");
        Role role = roleRespository.getOne(new Long(2));
        account.setRole(role);
        accountsRespository.save(account);
    }

    @Transactional
    public void updateAccountAfterOAuthLoginSuccess(Account account, AuthenticationProvider authenticationProvider) {
        account.setAuthProvider(authenticationProvider.toString());
        accountsRespository.save(account);
    }

}
