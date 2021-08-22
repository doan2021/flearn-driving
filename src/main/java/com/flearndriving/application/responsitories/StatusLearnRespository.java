package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Chapter;
import com.flearndriving.application.entities.Question;
import com.flearndriving.application.entities.StatusLearn;

@Repository
public interface StatusLearnRespository extends JpaRepository<StatusLearn, Long> {

    public StatusLearn findByQuestionAndAccount(Question question, Account account);

    @Query("   SELECT sl.question "
            + "FROM StatusLearn sl "
            + "WHERE sl.question.chapter = :chapter "
            + "AND sl.question.isDelete = false "
            + "AND sl.account = :account "
            + "AND sl.statusQuestion = :statusQuestion")
    public List<Question> getListQuestionWithStatus(Chapter chapter, Account account, Integer statusQuestion);
    
    @Query("   SELECT count(sl.question)"
            + "FROM StatusLearn sl "
            + "WHERE sl.question.chapter = :chapter "
            + "AND sl.question.isDelete = false "
            + "AND sl.account = :account "
            + "AND sl.statusQuestion = :statusQuestion")
    public int countQuestionWithStatus(Chapter chapter, Account account, Integer statusQuestion);

    @Query("    SELECT count(*)" 
            + " FROM Question q " 
            + " WHERE q.chapter = :chapter " 
            + " AND q.isDelete = false "
            + " AND q NOT IN (SELECT sl.question "
            + " FROM StatusLearn sl " 
            + " WHERE sl.account = :account "
            + " AND (sl.statusQuestion = 2 or sl.statusQuestion = 3))")
    public int countQuestionRest(Chapter chapter, Account account);

    
}
