package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.HistoryAnswer;
import com.flearndriving.application.entities.StatusLearn;

@Repository
public interface HistoryAnswerRespository extends JpaRepository<HistoryAnswer, Long> {
    
    
    @Query(value = "SELECT ha.isCorrect "
            + "FROM HistoryAnswer ha "
            + "WHERE ha.statusLearn = :statusLearn "
            + "ORDER BY ha.dateAnswer DESC ")
    public List<Boolean> checkLastStatusQuestion(StatusLearn statusLearn);
}
