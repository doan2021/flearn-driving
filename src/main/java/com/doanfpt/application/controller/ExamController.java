package com.doanfpt.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {

    @GetMapping(value = { "/update-info-driving-licence" })
    public String visitPageRegistExam(Model model) {
        return "update-info-driving-licence";
    }
}
