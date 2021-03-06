package com.flearndriving.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.Question;
import com.flearndriving.application.responsitories.ChapterResponsitory;
import com.flearndriving.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    @Autowired
    AccountServices accountsServices;

    public List<Question> getAllQuestions() {
        return questionsRespository.findAll();
    }

	public List<Question> getQuestionsInChapter(Long chapterId) {
		return questionsRespository.findQuestionByChapterId(chapterId);
	}
	
	public Integer countQuestion() {
		return questionsRespository.countQuestion();
	}

}
