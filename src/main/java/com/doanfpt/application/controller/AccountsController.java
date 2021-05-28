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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == AccountForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @PostMapping(value = { "/create-account" })
    public String createUser(@ModelAttribute("appUserForm") @Validated AccountForm appUserForm, BindingResult result,
            final RedirectAttributes redirectAttributes, Model model) {
        // Validate result
        if (result.hasErrors()) {
            return "register";
        }
        accountsServices.createAccount(appUserForm);
        return "login";
    }
    
    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        return "view-profile";
    }
}
