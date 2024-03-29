package com.flearndriving.application.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.Common;
import com.flearndriving.application.dto.ResponeData;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Answer;
import com.flearndriving.application.entities.ExamQuestions;
import com.flearndriving.application.entities.HistoryAnswer;
import com.flearndriving.application.entities.TrialExamResult;
import com.flearndriving.application.responsitories.AnswerRespository;
import com.flearndriving.application.responsitories.TrialExamResultResponsitory;

@Service
public class TrialExamResultServices {

    @Autowired
    TrialExamResultResponsitory trialExamResultResponsitory;

    @Autowired
    AnswerRespository answerRespository;

    @Autowired
    ExamQuestionsServices examQuestionsServices;

    @Autowired
    AccountServices accountServices;

    public List<TrialExamResult> findAllTrialExamResult() {
        Account account = accountServices.getAccountLogin();
        return trialExamResultResponsitory.findAllByAccount(account);
    }

    public TrialExamResult getOne(Long trailExamResultId) {
        return trialExamResultResponsitory.getOne(trailExamResultId);
    }

    public ResponeData answerTrialExam(String listSelectAnswers, Long examQuestionsId, Integer timeLeft) {
        ResponeData responeData = new ResponeData();
        Account account = accountServices.getAccountLogin();
        ExamQuestions examQuestions = examQuestionsServices.findOneExamQuestions(examQuestionsId);
        TrialExamResult trialExamResult = new TrialExamResult();
        trialExamResult.setAccount(account);
        trialExamResult.setExamQuestions(examQuestions);
        trialExamResult.setTimeExam(timeLeft);
        List<HistoryAnswer> listHistoryAnswers = new ArrayList<>();
        String[] listAnswerId = listSelectAnswers.split(",");
        int numberCorrect = 0;
        boolean correctParalysis = true;
        boolean isPass = false;
        for (String strAnswerId : listAnswerId) {
            if (StringUtils.isNumeric(strAnswerId)) {
                Long answerId = NumberUtils.toLong(strAnswerId);
                Answer answer = answerRespository.getOne(answerId);
                if (answer.isTrue()) {
                    numberCorrect++;
                } else if (answer.getQuestion().isParalysis()) {
                    correctParalysis = false;
                }
                HistoryAnswer historyAnswer = new HistoryAnswer();
                historyAnswer.setAnswer(answer);
                historyAnswer.setCorrect(answer.isTrue());
                historyAnswer.setDateAnswer(Common.getSystemDate());
                historyAnswer.setNote("Trả lời thi thử");
                historyAnswer.setTrialExamResult(trialExamResult);
                listHistoryAnswers.add(historyAnswer);
            }
        }
        String cause = "Không đủ điểm đậu.";
        if (numberCorrect >= examQuestions.getDrivingLicense().getNumberQuestionCorect()) {
            cause = "Đạt đủ điểm.";
            isPass = true;
        }
        if (!correctParalysis) {
            isPass = false;
            cause = "Sai câu điểm liệt.";
        }
        trialExamResult.setDescription(cause);
        trialExamResult.setListHistoryAnswer(listHistoryAnswers);
        trialExamResult.setNumberCorrect(numberCorrect);
        trialExamResult.setNumberIncorrect(examQuestions.getDrivingLicense().getNumberQuestion() - numberCorrect);
        trialExamResult.setPass(isPass);
        trialExamResult.setCreateAt(Common.getSystemDate());
        trialExamResult.setCreateBy(Common.getUsernameLogin());
        trialExamResult.setUpdateAt(Common.getSystemDate());
        trialExamResult.setUpdateBy(Common.getUsernameLogin());
        trialExamResultResponsitory.save(trialExamResult);
        responeData.putResult("pass", isPass);
        responeData.putResult("cause", cause);
        responeData.putResult("numberCorrect", numberCorrect);
        responeData.putResult("totalQuestion", examQuestions.getDrivingLicense().getNumberQuestion());
        return responeData;
    }

    public int getNumberOfPass() {
        // get account login
        Account account = accountServices.getAccountLogin();
        // get count pass by account login
        return trialExamResultResponsitory.countByAccountAndIsPass(account, true);
    }

    public int countTrialExamResult() {
        Account account = accountServices.getAccountLogin();
        return trialExamResultResponsitory.countByAccount(account);
    }
    
    public Long countAllTrialExamResult() {
        return trialExamResultResponsitory.count();
    }

}
