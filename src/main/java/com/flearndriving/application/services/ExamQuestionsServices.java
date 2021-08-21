package com.flearndriving.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.QuestionDto;
import com.flearndriving.application.dto.TrialExamDto;
import com.flearndriving.application.entities.DrivingLicense;
import com.flearndriving.application.entities.ExamQuestions;
import com.flearndriving.application.entities.Question;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.DocumentRespository;
import com.flearndriving.application.responsitories.DrivingLicenseRepository;
import com.flearndriving.application.responsitories.ExamQuestionRepository;
import com.flearndriving.application.responsitories.QuestionsRespository;

@Service
public class ExamQuestionsServices {

    @Autowired
    ExamQuestionRepository examQuestionRepository;

    @Autowired
    DrivingLicenseRepository drivingLicenseRespository;

    @Autowired
    QuestionsRespository questionsRespository;

    @Autowired
    DocumentRespository documentRespository;

    public List<ExamQuestions> findAllExamQuestion() {
        return examQuestionRepository.findAll();
    }

    public TrialExamDto initTrialExam(Long examQuestionsId) {
        TrialExamDto trialExamData = examQuestionRepository.getDataTrialExam(examQuestionsId);
        if (trialExamData.getExamQuestionsId() == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Đề thi không tồn tại!");
        }
        List<QuestionDto> listQuestions = questionsRespository.getListQuestionDtoByExamQuestionsId(examQuestionsId);
        List<QuestionDto> listQues = new ArrayList<>();
        for (QuestionDto questionDto : listQuestions) {
            if (questionDto != null) {
                questionDto.setListAnswers(questionsRespository.getListAnswerDtoByQuestionId(questionDto.getQuestionId()));
                questionDto.setListUrlImage(documentRespository.getListUrlImageByQuestionId(questionDto.getQuestionId()));
                listQues.add(questionDto);
            }
        }
        trialExamData.setListQuestions(listQues);
        return trialExamData;
    }

    public ExamQuestions findOneExamQuestions(Long examQuestionsId) {
        ExamQuestions examQuestions = examQuestionRepository.findByExamQuestionsId(examQuestionsId);
        if (examQuestions == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Đề thi không tồn tại!");
        }
        return examQuestions;
    }

    public List<ExamQuestions> findExamQuestionByDrivingLicenseId(Long drivingLicenseId) {
        DrivingLicense drivingLicense = drivingLicenseRespository.findByDrivingLicenseId(drivingLicenseId);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Hạng bằng không tồn tại!");
        }
        return examQuestionRepository.findByDrivingLicense(drivingLicense);
    }

    public List<Question> findListQuestionByExamQuestionsId(Long examQuestionsId) {
        return questionsRespository.getListQuestionByExamQuestionsId(examQuestionsId);
    }
}
