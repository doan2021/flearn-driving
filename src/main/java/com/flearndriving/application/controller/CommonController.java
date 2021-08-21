package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flearndriving.application.dto.AccountForm;
import com.flearndriving.application.services.AccountServices;

@Controller
public class CommonController {

    @Autowired
    AccountServices accountServices;

    @GetMapping(value = { "/login" })
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = { "/register" })
    public String registerPage(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "register";
    }

    @PostMapping(value = { "/create-account" })
    public String createUser(@ModelAttribute("accountForm") @Validated AccountForm accountForm, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        accountServices.createAccount(accountForm);
        return "register-successful";
    }
}
