package com.flearndriving.application.controller.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String handleUnwantedException(Exception e, Model model) {
        model.addAttribute("error", e);
        return "system-error";
    }
}
