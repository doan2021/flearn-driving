package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flearndriving.application.dto.TrialExamDto;
import com.flearndriving.application.entities.DrivingLicense;
import com.flearndriving.application.entities.ExamQuestions;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestions, Long> {
    public List<ExamQuestions> findByDrivingLicense(DrivingLicense drivingLicense);

    public ExamQuestions findByExamQuestionsId(Long examQuestionsId);
    
    @Query(value="SELECT new com.flearndriving.application.dto.TrialExamDto(eq.examQuestionsId AS examQuestionsId, "
            + " eq.name,"
            + " eq.drivingLicense.examMinutes)"
            + " FROM ExamQuestions eq "
            + " WHERE eq.examQuestionsId = :examQuestionsId "
            + " AND eq.isDelete = false")
    public TrialExamDto getDataTrialExam(Long examQuestionsId);
    
}
