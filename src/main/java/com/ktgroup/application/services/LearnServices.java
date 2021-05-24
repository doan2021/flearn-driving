package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktgroup.application.common.Common;
import com.ktgroup.application.common.Constant;
import com.ktgroup.application.entities.Account;
import com.ktgroup.application.entities.Answer;
import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.entities.HistoryAnswer;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.entities.StatusLearn;
import com.ktgroup.application.respone.ResponeData;
import com.ktgroup.application.responsitories.AccountsRespository;
import com.ktgroup.application.responsitories.AnswerRespository;
import com.ktgroup.application.responsitories.ChapterResponsitory;
import com.ktgroup.application.responsitories.HistoryAnswerRespository;
import com.ktgroup.application.responsitories.QuestionsRespository;
import com.ktgroup.application.responsitories.StatusLearnRespository;

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
    
    @Autowired
    StatusLearnRespository statusLearnRespository;

    public ResponeData getQuestionInChapter(Long chapterId) {
        // Init respone data
        ResponeData responeData = new ResponeData();
        // Get user login
        Account account = accountsServices.getAccountLogin();
        // Get chapter
        Chapter chapter = chapterResponsitory.getOne(chapterId);
        // Get list question in chapter
        List<Long> listQuestionIdHadAnswer = new ArrayList<>();
        listQuestionIdHadAnswer = statusLearnRespository.getListQuestionHadAnswer(account, chapter);
        List<Question> listQuestion = new ArrayList<>();
        listQuestion = questionsRespository.findByQuestionIdNotIn(listQuestionIdHadAnswer);
        // Get ramdom question
        Random rand = new Random();
        Question questionRandom = null;
        if (listQuestion == null || listQuestion.size() == 0) {
            listQuestion = questionsRespository.findByQuestionIdIn(listQuestionIdHadAnswer);
        }
        
        if (listQuestion != null && listQuestion.size() != 0) {
            questionRandom = listQuestion.get(rand.nextInt(listQuestion.size()));
        }
        // Get list status learn
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
        setHistoryAnswer(question, answer);
        return responeData;
    }
    
    public void setHistoryAnswer(Question question, Answer answer) {
        Account account = accountsServices.getAccountLogin();
        HistoryAnswer historyAnswer = new HistoryAnswer();
        historyAnswer.setCorrect(answer.isTrue());
        historyAnswer.setDateAnswer(new Date());
        historyAnswer.setQuestion(question);
        historyAnswer.setNote("Answer when learn");
        historyAnswer.setAnswer(answer);
        historyAnswer.setAccount(account);
        historyAnswerRespository.save(historyAnswer);
        StatusLearn statusLearn = statusLearnRespository.findByQuestionAndAccount(question, account);
        if (statusLearn == null) {
            statusLearn = new StatusLearn();
            statusLearn.setQuestion(question);
            statusLearn.setAccount(account);
            if (answer.isTrue()) {
                statusLearn.setCorrectNumberOfTimes(1);
                statusLearn.setIncorrectNumberOfTimes(0);
            } else {
                statusLearn.setCorrectNumberOfTimes(0);
                statusLearn.setIncorrectNumberOfTimes(1);
            }
        } else {
            if (answer.isTrue()) {
                statusLearn.setCorrectNumberOfTimes(statusLearn.getCorrectNumberOfTimes() + 1);
            } else {
                statusLearn.setIncorrectNumberOfTimes(statusLearn.getIncorrectNumberOfTimes() + 1);
            }
        }
        statusLearn.setUpdateAt(new Date());
        statusLearnRespository.save(statusLearn);
    }
    
    public ResponeData getStatusLearn(Long chapterId) {
        ResponeData responeData = new ResponeData();
        Chapter chapter = chapterResponsitory.getOne(chapterId);
        List<Question> listQuestion = new ArrayList<>();
        listQuestion = chapter.getListQuestion();
        Account account = accountsServices.getAccountLogin();
        List<StatusLearn> listStatusLearn = statusLearnRespository.findByQuestionInAndAccount(listQuestion, account);
        int rest = listQuestion.size() - listStatusLearn.size();
        responeData.putResult("rest", rest);
        int knowledge = 0;
        int familiar = 0;
        for (StatusLearn statusLearn :listStatusLearn) {
            if (Common.percentQuestion(statusLearn.getCorrectNumberOfTimes(), statusLearn.getIncorrectNumberOfTimes()) >= 80 || statusLearn.getCorrectNumberOfTimes() > statusLearn.getIncorrectNumberOfTimes() + 5) {
                knowledge+= 1;
            } else {
                familiar+=1;
            }
        }
        responeData.putResult("knowledge", knowledge);
        responeData.putResult("familiar", familiar);
        return responeData;
    }
}
