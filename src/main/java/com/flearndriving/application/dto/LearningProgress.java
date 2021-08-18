package com.flearndriving.application.dto;

import com.flearndriving.application.entities.Chapter;

public class LearningProgress {
	public Chapter chapter;
	public Double progressChapter;
	public int knowledge;
	public int familier;
	public int rest;

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Double getProgressChapter() {
		return progressChapter;
	}

	public void setProgressChapter(Double progressChapter) {
		this.progressChapter = progressChapter;
	}

	public int getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}

	public int getFamilier() {
		return familier;
	}

	public void setFamilier(int familier) {
		this.familier = familier;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

}
