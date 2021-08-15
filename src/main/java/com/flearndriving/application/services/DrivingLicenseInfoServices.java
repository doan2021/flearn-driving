package com.flearndriving.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Exam;
import com.flearndriving.application.responsitories.ExamProfileResponsitory;

@Service
public class DrivingLicenseInfoServices {
    
    @Autowired
    ExamProfileResponsitory examProfileResponsitory;

    public Boolean checkExistsDrivingLicenseInfo(Exam exam, Account account) {
        return examProfileResponsitory.existsByExamAndAccount(exam, account);
    }
    
    public Integer countDrivingLicenseInfo() {
		return examProfileResponsitory.countDrivingLicenseInfo();
	}
}
