package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doanfpt.application.services.ChapterServices;

@Controller
public class CommonController {
    
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/", "/index" })
    public String init(Model model) {
        model.addAttribute("numberOfChapter", chapterServices.countChapter());
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
