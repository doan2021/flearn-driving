package com.flearndriving.application.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.ResponeData;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Answer;
import com.flearndriving.application.entities.Chapter;
import com.flearndriving.application.entities.HistoryAnswer;
import com.flearndriving.application.entities.Question;
import com.flearndriving.application.entities.StatusLearn;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.AccountsRespository;
import com.flearndriving.application.responsitories.AnswerRespository;
import com.flearndriving.application.responsitories.ChapterResponsitory;
import com.flearndriving.application.responsitories.HistoryAnswerRespository;
import com.flearndriving.application.responsitories.QuestionsRespository;
import com.flearndriving.application.responsitories.StatusLearnRespository;

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
    AccountServices accountsServices;

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
        Random rand = new Random();
        Question questionRandom = null;
        int rest = 0;
        int familiar = 0;
        int knowledge = 0;
        List<Question> listQuestionRest = questionsRespository.getListQuestionRest(chapter, account);
        List<Question> listQuestionFamiliar = statusLearnRespository.getListQuestionWithStatus(chapter, account, 2);
        List<Question> listQuestionKnowledge = statusLearnRespository.getListQuestionWithStatus(chapter, account, 3);
        if (listQuestionRest != null && listQuestionRest.size() != 0) {
            questionRandom = listQuestionRest.get(rand.nextInt(listQuestionRest.size()));
        } else if (listQuestionFamiliar != null && listQuestionFamiliar.size() != 0) {
            questionRandom = listQuestionFamiliar.get(rand.nextInt(listQuestionFamiliar.size()));
        } else {
            questionRandom = listQuestionKnowledge.get(rand.nextInt(listQuestionKnowledge.size()));
        }

        if (listQuestionRest != null && listQuestionRest.size() != 0) {
            rest = listQuestionRest.size();
        }

        if (listQuestionFamiliar != null && listQuestionFamiliar.size() != 0) {
            familiar = listQuestionFamiliar.size();
        }

        if (listQuestionKnowledge != null && listQuestionKnowledge.size() != 0) {
            knowledge = listQuestionKnowledge.size();
        }
        List<Answer> listAnswer = answerRespository.findByQuestion(questionRandom);
        // Get list status learn
        responeData.putResult("rest", rest);
        responeData.putResult("familiar", familiar);
        responeData.putResult("knowledge", knowledge);
        responeData.putResult("questionRandom", questionRandom);
        responeData.putResult("listAnswer", listAnswer);
        responeData.putResult("chapter", chapter);
        return responeData;
    }

    @Transactional
    public ResponeData checkResultAnswer(Long answerId) {
        ResponeData responeData = new ResponeData();
        Answer answer = answerRespository.findByAnswerId(answerId);
        if (answer == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Câu trả lời không tồn tại!");
        }
        if (answer.isTrue()) {
            responeData.putResult("answerTrue", true);
        } else {
            responeData.putResult("answerTrue", false);
        }
        setHistoryAnswer(answer);
        responeData.putResult("answerId", answerId);
        return responeData;
    }

    public void setHistoryAnswer(Answer answer) {
        Account account = accountsServices.getAccountLogin();
        // Set status learn
        StatusLearn statusLearn = statusLearnRespository.findByQuestionAndAccount(answer.getQuestion(), account);
        if (statusLearn == null) {
            statusLearn = new StatusLearn();
            statusLearn.setQuestion(answer.getQuestion());
            statusLearn.setAccount(account);
            if (answer.isTrue()) {
                statusLearn.setCorrectNumberOfTimes(1);
                statusLearn.setIncorrectNumberOfTimes(0);
                statusLearn.setStatusQuestion(2);
            } else {
                statusLearn.setCorrectNumberOfTimes(0);
                statusLearn.setIncorrectNumberOfTimes(1);
                statusLearn.setStatusQuestion(1);
            }
        } else {
            List<Boolean> statusLastQuestion = historyAnswerRespository.checkLastStatusQuestion(answer.getQuestion(), account);
            if (answer.isTrue()) {
                if (statusLastQuestion.get(0)) {
                    statusLearn.setStatusQuestion(3);
                }
                statusLearn.setCorrectNumberOfTimes(statusLearn.getCorrectNumberOfTimes() + 1);
            } else {
                if (statusLearn.getCorrectNumberOfTimes() > 0) {
                    statusLearn.setStatusQuestion(2);
                } else {
                    statusLearn.setStatusQuestion(1);
                }
                statusLearn.setIncorrectNumberOfTimes(statusLearn.getIncorrectNumberOfTimes() + 1);
            }
        }
        statusLearn.setUpdateAt(new Date());
        statusLearnRespository.save(statusLearn);
        HistoryAnswer historyAnswer = new HistoryAnswer();
        historyAnswer.setCorrect(answer.isTrue());
        historyAnswer.setDateAnswer(new Date());
        historyAnswer.setNote("Answer when learn");
        historyAnswer.setAnswer(answer);
        historyAnswer.setAccount(account);
        historyAnswerRespository.save(historyAnswer);
    }
}
