package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doanfpt.application.respone.ResponeData;
import com.doanfpt.application.services.LearnServices;

@Controller
public class LearnController {
    
    @Autowired
    LearnServices learnServices;
    
    @GetMapping(value = {"/learn/{chapterId}"})
    public String learn(@PathVariable Long chapterId, Model model) {
        model.addAttribute(chapterId);
        return "learn";
    }
    
    @PostMapping(value = {"/load-page-learn"})
    @ResponseBody
    public ResponeData index(Long chapterId) {
        return learnServices.getQuestionInChapter(chapterId);
    }
    
    @PostMapping(value = {"/submit-answer"})
    @ResponseBody
    public ResponeData submitAnswer(Long questionId, Long answerId) {
        return learnServices.checkResultAnswer(questionId, answerId);
    }

    @PostMapping(value = {"/view-document"})
    public String viewDocument(Model model) {
        return "";
    }
}
