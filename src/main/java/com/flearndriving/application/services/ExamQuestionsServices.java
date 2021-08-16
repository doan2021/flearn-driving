package com.flearndriving.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.entities.DrivingLicense;
import com.flearndriving.application.entities.ExamQuestions;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.DrivingLicenseRepository;
import com.flearndriving.application.responsitories.ExamQuestionRepository;

@Service
public class ExamQuestionsServices {

    @Autowired
    ExamQuestionRepository examQuestionRepository;
    
    @Autowired
    DrivingLicenseRepository drivingLicenseRespository;

    public List<ExamQuestions> findAllExamQuestion() {
        return examQuestionRepository.findAll();
    }

    public List<ExamQuestions> findExamQuestionByDrivingLicenseId(Long drivingLicenseId) {
        DrivingLicense drivingLicense = drivingLicenseRespository.findByDrivingLicenseId(drivingLicenseId);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        return examQuestionRepository.findByDrivingLicense(drivingLicense);
    }
}
