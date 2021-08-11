package com.doanfpt.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.Chapter;
import com.doanfpt.application.entities.Question;

@Repository
public interface QuestionsRespository extends JpaRepository<Question, Long> {

	public Question findByNumber(int number);

	public List<Question> findByChapterAndIsDelete(Chapter chapter, Boolean isDelete);

	public List<Question> findByQuestionIdNotInAndChapter(List<Long> listIds, Chapter chapter);

	public List<Question> findByQuestionIdIn(List<Long> listIds);

	@Query("SELECT q " + "FROM Question q " + "WHERE q.chapter = :chapter " + "    AND q NOT IN (SELECT sl.question "
			+ "                  FROM StatusLearn sl " + "                  WHERE sl.account = :account "
			+ "                      AND (sl.statusQuestion = 2 or sl.statusQuestion = 3))")
	public List<Question> getListQuestionRest(Chapter chapter, Account account);

	@Query("SELECT count(q) FROM Question q WHERE q.isDelete = false")
	Integer countQuestion();
}
