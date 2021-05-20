package com.ktgroup.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktgroup.application.dto.AccountForm;
import com.ktgroup.application.entities.Account;
import com.ktgroup.application.services.AccountsServices;
import com.ktgroup.application.services.RoleServices;
import com.ktgroup.application.utils.WebUtils;

@Controller
public class CommonController {
    
    @Autowired
    AccountsServices accountsServices;

    @Autowired
    RoleServices roleServices;
    
    @GetMapping(value = {"/", "/index"})
    public String init(Principal principal, Model model) {
        Account accountLogin = new Account();
        if (principal != null) {
           accountLogin = accountsServices.getAccountByUserName(principal.getName());
        }
        model.addAttribute(accountLogin);
        return "index";
    }
    
    @GetMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403";
    }
    
    @GetMapping(value = {"/register"})
    public String registerPage(Model model) {
        model.addAttribute("appUserForm", new AccountForm());
        return "register";
    }

}
