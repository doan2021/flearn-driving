package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doanfpt.application.dto.AccountPrincipal;
import com.doanfpt.application.entities.Account;
import com.doanfpt.application.responsitories.AccountsRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountsRespository accountsRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountsRespository.findByUserName(userName);
        AccountPrincipal userDetails = AccountPrincipal.create(account);
        return userDetails;
    }
}
