package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktgroup.application.common.Constant;
import com.ktgroup.application.entities.Answer;
import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.entities.HistoryAnswer;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.respone.ResponeData;
import com.ktgroup.application.responsitories.AccountsRespository;
import com.ktgroup.application.responsitories.AnswerRespository;
import com.ktgroup.application.responsitories.ChapterResponsitory;
import com.ktgroup.application.responsitories.HistoryAnswerRespository;
import com.ktgroup.application.responsitories.QuestionsRespository;

@Service
public class LearnServices {
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    @Autowired
    QuestionsRespository questionsRespository;
    
    @Autowired
    AnswerRespository answerRespository;
    
    @Autowired
    AccountsRespository accountRespository;
    
    @Autowired
    HistoryAnswerRespository historyAnswerRespository;
    
    @Autowired
    AccountsServices accountsServices;

    public ResponeData getQuestionInChapter(Long chapterId) {
        ResponeData responeData = new ResponeData();
        Chapter chapter = chapterResponsitory.getOne(chapterId);
        Random rand = new Random();
        List<Question> listQuestion = new ArrayList<>();
        Question questionRandom = new Question();
        listQuestion = questionsRespository.findByChapter(chapter);
        questionRandom = listQuestion.get(rand.nextInt(listQuestion.size()));
        responeData.putResult("questionRandom", questionRandom);
        responeData.putResult("chapter", chapter);
        return responeData;
    }
    
    @Transactional
    public ResponeData checkResultAnswer(Long questionId, Long answerId) {
        ResponeData responeData = new ResponeData();
        Question question = new Question();
        question = questionsRespository.getOne(questionId);
        Answer answer = new Answer();
        answer = answerRespository.getOne(answerId);
        if (answer.isTrue()) {
            responeData.setStatus(Constant.STR_TRUE);
            responeData.setMessage("Correct !!");
        } else {
            responeData.setStatus(Constant.STR_FALSE);
            responeData.setMessage("Incorrect !!");
        }
        HistoryAnswer historyAnswer = new HistoryAnswer();
        historyAnswer.setCorrect(answer.isTrue());
        historyAnswer.setDateAnswer(new Date());
        historyAnswer.setQuestion(question);
        historyAnswer.setNote("Answer when learn");
        historyAnswer.setAnswer(answer);
        historyAnswer.setAccount(accountsServices.getAccountLogin());
        historyAnswerRespository.save(historyAnswer);
        return responeData;
    }
    
    public ResponeData getStatusLearn(Long chapterId) {
        ResponeData responeData = new ResponeData();
        Chapter chapter = chapterResponsitory.getOne(chapterId);
        List<Question> listQuestion = new ArrayList<>();
        listQuestion = chapter.getListQuestion();
        return responeData;
    }
}
