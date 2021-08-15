package com.flearndriving.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.responsitories.ExamQuestionRepository;

@Service
public class ExamQuestionsServices {

	@Autowired
	ExamQuestionRepository examQuestionRepository;
}
