package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flearndriving.application.services.AccountServices;
import com.flearndriving.application.services.ChapterServices;
import com.flearndriving.application.services.QuestionServices;
import com.flearndriving.application.services.TrialExamResultServices;

@Controller
public class IndexController {
    @Autowired
    ChapterServices chapterServices;

    @Autowired
    AccountServices accountServices;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    TrialExamResultServices trialExamResultServices;

    @GetMapping(value = { "/", "/index" })
    public String init(Model model) {
        model.addAttribute("numberOfChapter", chapterServices.countChapter());
        model.addAttribute("numberOfAccount", accountServices.countAccount());
        model.addAttribute("numberOfQuestion", questionServices.countQuestion());
        model.addAttribute("numberOfTrialExam", trialExamResultServices.countAllTrialExamResult());
        return "index";
    }
}
