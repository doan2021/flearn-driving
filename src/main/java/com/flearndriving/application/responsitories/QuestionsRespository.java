package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.dto.AnswerDto;
import com.flearndriving.application.dto.QuestionDto;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Chapter;
import com.flearndriving.application.entities.Question;

@Repository
public interface QuestionsRespository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.chapter.chapterId = :chapterId")
    List<Question> findQuestionByChapterId(Long chapterId);
    
    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false AND q.chapter.chapterId = :chapterId")
    Long countQuestionByChapterId(Long chapterId);

    @Query("SELECT q " 
            + "FROM Question q " 
            + "WHERE q.chapter = :chapter " 
            + "AND q.isDelete = false "
            + "AND q NOT IN (SELECT sl.question "
            + "FROM StatusLearn sl " 
            + "WHERE sl.account = :account "
            + "AND (sl.statusQuestion = 2 or sl.statusQuestion = 3))")
    public List<Question> getListQuestionRest(Chapter chapter, Account account);

    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false")
    Integer countQuestion();

    @Query("SELECT q " 
            + "FROM Question q " 
            + "WHERE q.chapter = :chapter " 
            + "AND q.isDelete = false "
            + "AND q NOT IN (SELECT sl.question "
            + "FROM StatusLearn sl " 
            + "WHERE sl.account = :account "
            + "AND (sl.statusQuestion = 1 or sl.statusQuestion = 2))")
    public List<Question> getListQuestionKnowledge(Chapter chapter, Account account);

    // Trial exam
    @Query("SELECT eqd.question FROM ExamQuestionsDetail eqd WHERE eqd.examQuestions.examQuestionsId = :examQuestionsId")
    public List<Question> getListQuestionByExamQuestionsId(Long examQuestionsId);

    @Query(value="SELECT new com.flearndriving.application.dto.AnswerDto(a.answerId,"
            + " a.content)"
            + " FROM Answer a"
            + " WHERE a.question.questionId = :questionsId")
    public List<AnswerDto> getListAnswerDtoByQuestionId(Long questionsId);

    @Query(value="SELECT new com.flearndriving.application.dto.QuestionDto(eqd.question.questionId,"
            + " eqd.question.number,"
            + " eqd.question.content)"
            + " FROM ExamQuestionsDetail eqd"
            + " WHERE eqd.examQuestions.examQuestionsId = :examQuestionsId")
    public List<QuestionDto> getListQuestionDtoByExamQuestionsId(Long examQuestionsId);
}
