package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doanfpt.application.services.ChapterServices;

@Controller
public class ChapterController {
    
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/select-chapter" })
    public String selectChapter(Model model) {
        model.addAttribute("listChapter", chapterServices.getAllChapter());
        return "select-chapter";
    }
}
