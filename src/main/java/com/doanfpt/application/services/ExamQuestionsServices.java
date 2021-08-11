package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.responsitories.ExamQuestionRepository;

@Service
public class ExamQuestionsServices {

	@Autowired
	ExamQuestionRepository examQuestionRepository;
}
