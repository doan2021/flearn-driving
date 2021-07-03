package com.doanfpt.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {
    
    @GetMapping(value = { "/regist-exam" })
    public String visitRegistExamPage(Model model) {
        return "regist-exam";
    }
}
