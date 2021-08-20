package com.flearndriving.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.TrialExamResult;
import com.flearndriving.application.responsitories.TrialExamResultResponsitory;

@Service
public class TrialExamResultServices {
	@Autowired
	TrialExamResultResponsitory trialExamResultResponsitory;
	
	@Autowired
	AccountServices accountServices;

	public List<TrialExamResult> findAllTrialExamResult() {
		Account account = accountServices.getAccountLogin();
		return trialExamResultResponsitory.findAllByAccount(account);
	}

	public TrialExamResult getOne(Long trailExamResultId) {
		return trialExamResultResponsitory.getOne(trailExamResultId);
	}

	public int getPercentPass() {
		// get account login
		Account account = accountServices.getAccountLogin();
		// gt count total by account login
		// get count pass by account login
		return trialExamResultResponsitory.countByAccountAndIsPass(account, true);
	}
	
	

	public int countTrialExamResult() {
		// TODO Auto-generated method stub
		Account account = accountServices.getAccountLogin();
		int total = trialExamResultResponsitory.countByAccount(account);
		return total;
	}
	
}
