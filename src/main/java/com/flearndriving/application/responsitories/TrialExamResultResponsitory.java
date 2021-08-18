package com.flearndriving.application.responsitories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flearndriving.application.entities.TrialExamResult;


@Repository
public interface TrialExamResultResponsitory  extends JpaRepository<TrialExamResult, Long> {

	public List<TrialExamResult> findByTrialExamResult(TrialExamResult trialExamResult);
    
}
