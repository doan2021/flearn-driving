package com.flearndriving.application.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.CustomOAuth2User;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.services.AccountServices;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AccountServices accountServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = customOAuth2User.getEmail();
        String fullName = customOAuth2User.getName();
        Account account = accountServices.findByEmail(email);
        if (account == null) {
            accountServices.createAccountAfterOAuthLoginSuccess(email, fullName, Constant.GOOGLE_PROVIDER);
        } else {
            accountServices.updateAccountAfterOAuthLoginSuccess(account, Constant.GOOGLE_PROVIDER);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
