package com.flearndriving.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.controller.IndexController;

@ControllerAdvice(assignableTypes = { IndexController.class })
public class IndexControllerAdvice {

    @ModelAttribute("classActiveIndexTab")
    public String cssActivePage() {
        return "active";
    }
}
