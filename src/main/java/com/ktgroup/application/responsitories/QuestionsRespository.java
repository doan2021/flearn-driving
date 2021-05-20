package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Question;

@Repository
public interface QuestionsRespository  extends JpaRepository<Question, Long> {

    public Question findByNumber(int number);
}
