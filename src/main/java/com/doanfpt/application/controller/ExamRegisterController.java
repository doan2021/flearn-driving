package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ExamRegisterController {
   

    @GetMapping(value = { "/exam-register" })
    public String examRegister(Model model) {
        
        return "exam-register";
    }
}
