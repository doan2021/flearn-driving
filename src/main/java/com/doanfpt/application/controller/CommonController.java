package com.doanfpt.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doanfpt.application.dto.AccountForm;

@Controller
public class CommonController {
    
    @GetMapping(value = {"/", "/index"})
    public String init(Model model) {
        return "index";
    }
    
    @GetMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "403";
    }
    
    @GetMapping(value = {"/register"})
    public String registerPage(Model model) {
        model.addAttribute("appUserForm", new AccountForm());
        return "register";
    }

}
