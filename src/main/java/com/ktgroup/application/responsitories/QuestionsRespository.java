package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Questions;

@Repository
public interface QuestionsRespository  extends JpaRepository<Questions, Long> {

    public Questions findByNumber(int number);
}
