package com.ktgroup.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktgroup.application.dto.ExamForm;
import com.ktgroup.application.dto.QuestionForm;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.services.QuestionServices;

@Controller
public class LearnController {
    
    @Autowired
    private QuestionServices questionServices;
    
    @GetMapping(value = {"/create-question"})
    public String createQuestion(Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute(questionForm);
        return "create-question";
    }
    
    @PostMapping(value = {"/save-question"})
    public String createNewQuestion(@ModelAttribute QuestionForm form, Model model) {
        questionServices.createNewQuestion(form);
        return "create-question";
    }
    
    @GetMapping(value = {"/create-exam"})
    public String createNewExam(Model model) {
        ExamForm examForm = new ExamForm();
        List<Question> listQuestions = questionServices.getAllQuestions();
        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("examForm", examForm);
        return "create-exam";
    }
    
    @PostMapping(value = {"/save-exam"})
    public String createNewExam(@ModelAttribute ExamForm form, Model model) {
        return "redirect:/create-exam";
    }
    
    @GetMapping(value = {"/learn"})
    public String index(Model model) {
        return "learn";
    }
}
