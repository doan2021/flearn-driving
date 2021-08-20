package com.flearndriving.application.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.dto.AccountLogin;
import com.flearndriving.application.services.AccountServices;

@ControllerAdvice(annotations = Controller.class)
public class CommonControllerAdvice {
    
    @Autowired
    AccountServices accountServices;

    @ModelAttribute("accountLogin")
    public AccountLogin loadAccountLogin() {
        return accountServices.getBasicInfoAccountLogin();
    }
}
