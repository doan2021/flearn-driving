package com.ktgroup.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.services.ChapterServices;

@Controller
public class ChapterController {
    
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/select-chapter" })
    public String selectChapter(Model model) {
        model.addAttribute("listChapter", chapterServices.getAllChapter());
        return "select-chapter";
    }
    
    @GetMapping(value = { "/create-chapter" })
    public String createChapter(Model model) {
        model.addAttribute("chapterForm", new Chapter());
        return "create-chapter";
    }
    
    @PostMapping(value = { "/save-chapter" })
    public String saveChapter(@ModelAttribute("chapterForm") Chapter chapterForm, Model model) {
        chapterServices.saveChapter(chapterForm);
        return "create-chapter";
    }
}
