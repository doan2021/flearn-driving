package com.ktgroup.application.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktgroup.application.common.Constant;
import com.ktgroup.application.utils.WebUtils;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping(value = {"/", "/index"})
    public String userInfo(Model model, Principal principal) {
        // Sau khi user login thanh cong se co principal
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute(Constant.LABEL_NAME_SCREEN, "dashboard");
        return "dashboard";
    }
}
