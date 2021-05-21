package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.HistoryAnswer;

@Repository
public interface HistoryAnswerRespository extends JpaRepository<HistoryAnswer, Long> {

}
