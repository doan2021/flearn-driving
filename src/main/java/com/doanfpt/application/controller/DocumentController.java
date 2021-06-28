package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doanfpt.application.services.ChapterServices;

@Controller
public class DocumentController {
	
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/document" })
    public String viewProfile(Model model) {
    	model.addAttribute("listChapter", chapterServices.getAllChapter());
        return "document";
    }
}
