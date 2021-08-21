package com.flearndriving.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.controller.ExamController;

@ControllerAdvice(assignableTypes = { ExamController.class })
public class ExamControllerAdvice {

    @ModelAttribute("classActiveExamTab")
    public String cssActivePage() {
        return "active";
    }
}
