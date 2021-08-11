package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doanfpt.application.entities.DrivingLicense;
import com.doanfpt.application.services.DrivingLicenseServices;
import com.doanfpt.application.services.ExamQuestionsServices;

public class TrialExamController {
	
	@Autowired
	DrivingLicenseServices drivingLicenseServices;
	
	@Autowired
	ExamQuestionsServices examQuestionServices;
	
	@GetMapping(value = { "/trial-exam" })
	public String trialTest(Model model) {
		return "trial-exam";
	}
	
	@GetMapping(value = { "/select-trial-exam" })
    public String selectTrialExam(Model model, Long drivingLicenseId) {
    	DrivingLicense drivingLicense = drivingLicenseServices.getOne(drivingLicenseId);
    	model.addAttribute("drivingLicense", drivingLicense);
        return "select-trial-exam";
    }
}
