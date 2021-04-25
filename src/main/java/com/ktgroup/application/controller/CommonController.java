package com.ktgroup.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ktgroup.application.dto.AppUserForm;
import com.ktgroup.application.services.AppUserServices;
import com.ktgroup.application.services.RoleServices;
import com.ktgroup.application.utils.WebUtils;
import com.ktgroup.application.validator.AppUserValidator;

@Controller
public class CommonController {
    
    @Autowired
    AppUserServices appUserServices;

    @Autowired
    RoleServices roleServices;
    
    @GetMapping(value = { "/", "/login"})
    public String welcomePage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
    
    @GetMapping(value = {"/register"})
    public String registerPage(Model model) {
        model.addAttribute("appUserForm", new AppUserForm());
        return "Register";
    }
}
