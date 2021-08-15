package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Exam;
import com.flearndriving.application.entities.ExamProfile;

@Repository
public interface ExamProfileResponsitory extends JpaRepository<ExamProfile, Long> {

	public Boolean existsByExamAndAccount(Exam exam, Account account);

	@Query("SELECT count(d) FROM ExamProfile d WHERE d.status = 3")
	Integer countDrivingLicenseInfo();
}
