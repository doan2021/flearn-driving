package com.doanfpt.application.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.doanfpt.application.common.Common;
import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.AuthenticationProvider;
import com.doanfpt.application.model.AccountPrincipal;
import com.doanfpt.application.services.AccountServices;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Autowired
    private AccountServices accountServices;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        AccountPrincipal accountDetail = (AccountPrincipal)authentication.getPrincipal();
        String email = accountDetail.getEmail();
        String fullName = accountDetail.getFullName();
        Account account = accountServices.findByEmail(email);
        if (account == null) {
            accountServices.createAccountAfterOAuthLoginSuccess(email, Common.getFirstName(fullName), Common.getLastName(fullName), AuthenticationProvider.GOOGLE);
        } else {
            accountServices.updateAccountAfterOAuthLoginSuccess(account, AuthenticationProvider.GOOGLE);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
