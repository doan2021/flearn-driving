package com.flearndriving.application.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flearndriving.application.entities.TrialExamResult;
import com.flearndriving.application.responsitories.TrialExamResultResponsitory;

@Service
public class TrialExamResultServices {
@Autowired
TrialExamResultResponsitory trialExamResultResponsitory;

	public List<TrialExamResult> findAllTrialExamResult() {
		return trialExamResultResponsitory.findAll();
	}

	public TrialExamResult getOne(Long trailExamResultId) {
		return trialExamResultResponsitory.getOne(trailExamResultId);
	}
//
//	public Object getQuestionsInTrialExamResult(TrialExamResult trialExamResult) {
//		// TODO Auto-generated method stub
//		return trialExamResultResponsitory.findByTrialExamResult(trialExamResult);
//	}
}
