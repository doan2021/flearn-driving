package com.doanfpt.application.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TrialExamController {
	@GetMapping(value = { "/trial-exam" })
	public String trialTest(Model model) {
		return "trial-exam";
	}
}
