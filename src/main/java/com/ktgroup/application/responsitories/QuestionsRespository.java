package com.ktgroup.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.entities.Question;

@Repository
public interface QuestionsRespository  extends JpaRepository<Question, Long> {

    public Question findByNumber(int number);
    
    public List<Question> findByChapter(Chapter chapter);
    
    public List<Question> findByQuestionIdNotIn(List<Long> listIds);
    
    public List<Question> findByQuestionIdIn(List<Long> listIds);
}
