package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.Exam;
import com.doanfpt.application.responsitories.DrivingLicenseInfoResponsitory;

@Service
public class DrivingLicenseInfoServices {
    
    @Autowired
    DrivingLicenseInfoResponsitory drivingLicenseInfoResponsitory;

    public Boolean checkExistsDrivingLicenseInfo(Exam exam, Account account) {
        return drivingLicenseInfoResponsitory.existsByExamAndAccount(exam, account);
    }
    
    public Integer countDrivingLicenseInfo() {
		return drivingLicenseInfoResponsitory.countDrivingLicenseInfo();
	}
}
