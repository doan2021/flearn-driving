package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flearndriving.application.entities.ExamQuestions;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestions, Long> {

}
