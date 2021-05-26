package com.ktgroup.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktgroup.application.dto.ExamForm;
import com.ktgroup.application.dto.QuestionForm;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.respone.ResponeData;
import com.ktgroup.application.services.ChapterServices;
import com.ktgroup.application.services.LearnServices;
import com.ktgroup.application.services.QuestionServices;

@Controller
public class LearnController {
    
    @Autowired
    private QuestionServices questionServices;
    
    @Autowired
    LearnServices learnServices;
    
    @Autowired
    private ChapterServices chapterServices;
    
    @GetMapping(value = {"/create-question"})
    public String createQuestion(Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute("listChapter", chapterServices.getAllChapter());
        model.addAttribute(questionForm);
        return "create-question";
    }
    
    @PostMapping(value = {"/save-question"})
    public String createNewQuestion(@ModelAttribute QuestionForm form, Model model) {
        questionServices.createNewQuestion(form);
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute("listChapter", chapterServices.getAllChapter());
        model.addAttribute(questionForm);
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

}
