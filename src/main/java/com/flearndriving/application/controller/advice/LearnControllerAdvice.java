package com.flearndriving.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.controller.LearnController;

@ControllerAdvice(assignableTypes = { LearnController.class })
public class LearnControllerAdvice {

    @ModelAttribute("classActiveLearnTab")
    public String cssActivePage() {
        return "active";
    }
}
