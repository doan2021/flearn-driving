package com.doanfpt.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Answer;
import com.doanfpt.application.entities.Question;

@Repository
public interface AnswerRespository extends JpaRepository<Answer, Long> {

    public Answer findByAnswerId(Long answerId);
    
    public List<Answer> findByQuestion(Question question);
}
