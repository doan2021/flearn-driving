package com.ktgroup.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Account;
import com.ktgroup.application.entities.HistoryAnswer;
import com.ktgroup.application.entities.Question;

@Repository
public interface HistoryAnswerRespository extends JpaRepository<HistoryAnswer, Long> {

    public List<HistoryAnswer> findByQuestionInAndAccount(List<Question> listQuestion, Account account);
}
