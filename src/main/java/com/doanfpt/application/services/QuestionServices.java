package com.doanfpt.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.common.Constant;
import com.doanfpt.application.entities.Chapter;
import com.doanfpt.application.entities.Question;
import com.doanfpt.application.responsitories.ChapterResponsitory;
import com.doanfpt.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;
    
    @Autowired
    ChapterResponsitory chapterResponsitory;

    public List<Question> getAllQuestions() {
        return questionsRespository.findAll();
    }

	public Object getQuestionsInChapter(Chapter chapter) {
		return questionsRespository.findByChapterAndIsDelete(chapter, Constant.IS_NOT_DELETE);
	}
	
	public Integer countQuestion() {
		return questionsRespository.countQuestion();
	}
}
