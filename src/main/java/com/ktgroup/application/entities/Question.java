package com.ktgroup.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "question", uniqueConstraints = { @UniqueConstraint(name = "QUESTIONS_UK", columnNames = "number") })
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "number")
    private int number;

    @Column(name = "content")
    private String content;

    @Column(name = "isParalysis", columnDefinition = "boolean default false")
    private boolean isParalysis;

    @Column(name = "is_delete", columnDefinition = "boolean default false")
    private boolean isDelete;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> listAnswers;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Image> listImage;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<StatusLearn> listStatusLearn;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public List<Answer> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<Answer> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public List<Image> getListImage() {
        return listImage;
    }

    public void setListImage(List<Image> listImage) {
        this.listImage = listImage;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public boolean isParalysis() {
        return isParalysis;
    }

    public void setParalysis(boolean isParalysis) {
        this.isParalysis = isParalysis;
    }

    public List<StatusLearn> getListStatusLearn() {
        return listStatusLearn;
    }

    public void setListStatusLearn(List<StatusLearn> listStatusLearn) {
        this.listStatusLearn = listStatusLearn;
    }

}
