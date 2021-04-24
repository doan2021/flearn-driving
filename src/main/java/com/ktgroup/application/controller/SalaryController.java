package com.ktgroup.application.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salary")
public class SalaryController {
    
    @GetMapping(value = {"/", "/index"})
    public String index(Model model, Principal principal) {
        return "salary";
    }
}
