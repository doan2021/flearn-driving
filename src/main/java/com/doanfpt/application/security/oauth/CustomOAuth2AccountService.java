package com.doanfpt.application.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.Account;
import com.doanfpt.application.model.AccountPrincipal;
import com.doanfpt.application.services.AccountServices;

@Service
public class CustomOAuth2AccountService extends DefaultOAuth2UserService {

    @Autowired
    AccountServices accountServices;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User =  super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        AccountPrincipal accountPrincipal = null;
        Account account = accountServices.findByEmail(email);
        if (account == null) {
            accountPrincipal = AccountPrincipal.create(oauth2User.getAttributes());
        } else {
            accountPrincipal = AccountPrincipal.create(account, oauth2User.getAttributes());
        }
        return accountPrincipal;
    }
}
