package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.DrivingLicenseInfo;
import com.doanfpt.application.entities.Exam;

@Repository
public interface DrivingLicenseInfoResponsitory  extends JpaRepository<DrivingLicenseInfo, Long> {
    
    public Boolean existsByExamAndAccount(Exam exam, Account account);

}
