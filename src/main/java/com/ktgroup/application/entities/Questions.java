package com.ktgroup.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "questions", uniqueConstraints = { @UniqueConstraint(name = "QUESTIONS_UK", columnNames = "number") })
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "number")
    private int number;

    @Column(name = "content")
    private String content;

    @Column(name = "is_delete", columnDefinition = "boolean default false")
    private boolean isDelete;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Answers> answers;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<Images> listImages;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public List<Images> getListImages() {
        return listImages;
    }

    public void setListImages(List<Images> listImages) {
        this.listImages = listImages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
