package com.doanfpt.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping(value = { "/", "/index" })
    public String init(Model model) {
        return "index";
    }

    @GetMapping(value = { "/login" })
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "403";
    }

}
