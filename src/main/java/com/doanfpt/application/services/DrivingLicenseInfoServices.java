package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.Exam;
import com.doanfpt.application.responsitories.ExamProfileResponsitory;

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
