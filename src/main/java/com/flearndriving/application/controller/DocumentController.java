package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flearndriving.application.services.ChapterServices;
import com.flearndriving.application.services.QuestionServices;

@Controller
public class DocumentController {

    @Autowired
    ChapterServices chapterServices;

    @Autowired
    QuestionServices questionServices;

    @GetMapping(value = { "/document" })
    public String viewProfile(Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "document";
    }

    @GetMapping(value = { "/detail-document" })
    public String visitDetailDocumentPage(Long chapterId, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        model.addAttribute("listQuestion", questionServices.getQuestionsInChapter(chapterId));
        return "detail-document";
    }
}