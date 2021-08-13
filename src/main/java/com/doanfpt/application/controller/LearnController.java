package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doanfpt.application.dto.ResponeData;
import com.doanfpt.application.services.ChapterServices;
import com.doanfpt.application.services.LearnServices;

@Controller
public class LearnController {

    @Autowired
    LearnServices learnServices;

    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/learn/{chapterId}" })
    public String learn(@PathVariable Long chapterId, Model model) {
        model.addAttribute(chapterId);
        return "learn";
    }

    @PostMapping(value = { "/load-page-learn" })
    @ResponseBody
    public ResponeData initPageLearn(Long chapterId) {
        return learnServices.getQuestionInChapter(chapterId);
    }

    @PostMapping(value = { "/submit-answer" })
    @ResponseBody
    public ResponeData submitAnswer(Long answerId) {
        return learnServices.checkResultAnswer(answerId);
    }

    @GetMapping(value = { "/select-chapter" })
    public String selectChapter(Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "select-chapter";
    }
}
