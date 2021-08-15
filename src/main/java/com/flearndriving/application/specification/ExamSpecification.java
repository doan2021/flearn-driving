package com.flearndriving.application.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.flearndriving.application.common.Common;
import com.flearndriving.application.entities.Exam;
import com.flearndriving.application.entities.Exam_;

public class ExamSpecification {
	public static Specification<Exam> hasName(String name) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.NAME), name);
	}

	public static Specification<Exam> likeContent(String description) {
		return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
	}

	public static Specification<Exam> hasDateRegisExamEndFrom(Date dateRegisExamEndFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END), dateRegisExamEndFrom);
	}

	public static Specification<Exam> hasDateRegisExamEndTo(String dateRegisExamEndTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END), Common.stringToDate(dateRegisExamEndTo));
	}

	public static Specification<Exam> hasUpdateFrom(String updateAtFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.UPDATE_AT), Common.stringToDate(updateAtFrom));
	}
	public static Specification<Exam> hasUpdateTo(String updateAtTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.UPDATE_AT), Common.stringToDate(updateAtTo));
	}

	public static Specification<Exam> hasDescription(String description) {
        return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
	}
}
