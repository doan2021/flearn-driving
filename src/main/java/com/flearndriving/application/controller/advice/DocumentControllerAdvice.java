package com.flearndriving.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.application.controller.DocumentController;

@ControllerAdvice(assignableTypes = { DocumentController.class })
public class DocumentControllerAdvice {

    @ModelAttribute("classActiveDocumentTab")
    public String cssActivePage() {
        return "active";
    }
}
