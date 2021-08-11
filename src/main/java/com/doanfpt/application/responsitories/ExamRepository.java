package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.doanfpt.application.entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>, PagingAndSortingRepository<Exam, Long>, JpaSpecificationExecutor<Exam>{
}
