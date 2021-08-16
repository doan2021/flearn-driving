package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flearndriving.application.dto.ResponeData;
import com.flearndriving.application.services.DrivingLicenseServices;
import com.flearndriving.application.services.ExamQuestionsServices;

@Controller
public class TrialExamController {
    
    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @Autowired
    ExamQuestionsServices examQuestionsServices;
    
    @GetMapping(value = { "/trial-exam" })
    public String trialTest(Model model) {
        return "trial-exam";
    }

    @GetMapping(value = { "/select-exam-question" })
    public String selectTrialExam(Model model) {
        return "select-exam-question";
    }
    
    @GetMapping(value = { "/init-select-exam-question" })
    @ResponseBody
    public ResponeData initSelectExamQuestion() {
        ResponeData responeData = new ResponeData();
        responeData.putResult("listDrivingLicense", drivingLicenseServices.findAllDrivingLicense());
        return responeData;
    }
    
    @GetMapping(value = { "/select-driving-license" })
    @ResponseBody
    public ResponeData selectDrivingLicense(Long drivingLicenseId) {
        ResponeData responeData = new ResponeData();
        responeData.putResult("listExamQuestions", examQuestionsServices.findExamQuestionByDrivingLicenseId(drivingLicenseId));
        return responeData;
    }
}
