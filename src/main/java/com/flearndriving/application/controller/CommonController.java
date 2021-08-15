package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flearndriving.application.services.AccountServices;
import com.flearndriving.application.services.ChapterServices;
import com.flearndriving.application.services.DrivingLicenseInfoServices;
import com.flearndriving.application.services.QuestionServices;

@Controller
public class CommonController {
    
    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    AccountServices accountServices;
    
    @Autowired
    QuestionServices questionServices;
    
    @Autowired
    DrivingLicenseInfoServices drivingLicenseInfoServices;

    @GetMapping(value = { "/", "/index" })
    public String init(Model model) {
        model.addAttribute("numberOfChapter", chapterServices.countChapter());
        model.addAttribute("numberOfAccount", accountServices.countAccount());
		model.addAttribute("numberOfQuestion", questionServices.countQuestion());
		model.addAttribute("numberOfDrivingLicenseInfo", drivingLicenseInfoServices.countDrivingLicenseInfo());
        return "index";
    }

    @GetMapping(value = { "/login" })
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "403";
    }

}
