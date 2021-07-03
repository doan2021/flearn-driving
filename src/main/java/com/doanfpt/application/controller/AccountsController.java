package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.doanfpt.application.dto.AccountForm;
import com.doanfpt.application.services.AccountServices;
import com.doanfpt.application.services.RoleServices;
import com.doanfpt.application.validator.AppUserValidator;

@Controller
public class AccountsController {

    @Autowired
    AccountServices accountsServices;

    @Autowired
    RoleServices roleServices;

    @Autowired
    private AppUserValidator appUserValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == AccountForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @GetMapping(value = { "/register" })
    public String registerPage(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "register";
    }

    @PostMapping(value = { "/create-account" })
    public String createUser(@ModelAttribute("accountForm") @Validated AccountForm accountForm, BindingResult result, Model model) {
        // Validate result
        if (result.hasErrors()) {
            return "register";
        }
        accountsServices.createAccount(accountForm);
        return "login";
    }

    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
<<<<<<< HEAD
<<<<<<< HEAD
        return "register-exam";
=======
//        return "view-profile";
        return "regist-exam";
>>>>>>> b548c49a4e6eead583e4f758b60a2f355cfff583
=======
        return "view-profile";
>>>>>>> 3fe9eccd5bbff675d7c28462f88760e70e915be7
    }
}
