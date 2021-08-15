package com.flearndriving.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrialExamController {

	@GetMapping(value = { "/trial-exam" })
	public String trialTest(Model model) {
		return "trial-exam";
	}

	@GetMapping(value = { "/select-trial-exam" })
	public String selectTrialExam(Model model) {
		return "select-trial-exam";
	}
}
