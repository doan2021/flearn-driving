package com.flearndriving.application.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.flearndriving.application.common.Common;
import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.AccountPrincipal;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.services.AccountServices;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AccountServices accountServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        AccountPrincipal accountDetail = (AccountPrincipal) authentication.getPrincipal();
        String email = accountDetail.getEmail();
        String fullName = accountDetail.getFullName();
        Account account = accountServices.findByEmail(email);
        if (account == null) {
            accountServices.createAccountAfterOAuthLoginSuccess(email, Common.getFirstName(fullName),
                    Common.getLastName(fullName), Constant.GOOGLE_PROVIDER);
        } else {
            accountServices.updateAccountAfterOAuthLoginSuccess(account, Constant.GOOGLE_PROVIDER);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
