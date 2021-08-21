package com.flearndriving.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.controller.TrialExamController;

@ControllerAdvice(assignableTypes = { TrialExamController.class })
public class TrialExamControllerAdvice {

    @ModelAttribute("classActiveTrialExamTab")
    public String cssActivePage() {
        return "active";
    }
}
