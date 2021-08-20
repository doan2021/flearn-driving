package com.flearndriving.application.responsitories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.TrialExamResult;

@Repository
public interface TrialExamResultResponsitory extends JpaRepository<TrialExamResult, Long> {

	
	int countByAccount(Account account);

	int countByAccountAndIsPass(Account account, boolean isPass);

	int countByAccountAndTimeExam(Account account , int timeExam);

	List<TrialExamResult> findAllByAccount(Account account);

}
