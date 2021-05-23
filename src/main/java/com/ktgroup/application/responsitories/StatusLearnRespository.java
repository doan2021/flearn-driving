package com.ktgroup.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Account;
import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.entities.StatusLearn;

@Repository
public interface StatusLearnRespository extends JpaRepository<StatusLearn, Long> {
    public StatusLearn findByQuestionAndAccount(Question question, Account account);

    public List<StatusLearn> findByQuestionInAndAccount(List<Question> listQuestion, Account account);

    @Query("SELECT st.question.questionId FROM StatusLearn st WHERE st.account = :account AND st.question.chapter = :chapter")
    public List<Long> getListQuestionHadAnswer(@Param("account") Account account,
            @Param("chapter") Chapter chapter);
}
