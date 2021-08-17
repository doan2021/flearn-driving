package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flearndriving.application.entities.DrivingLicense;
import com.flearndriving.application.entities.ExamQuestions;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestions, Long> {
    public List<ExamQuestions> findByDrivingLicense(DrivingLicense drivingLicense);

    public ExamQuestions findByExamQuestionsId(Long examQuestionsId);

}
