package com.ktgroup.application.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktgroup.application.dto.AnswerForm;
import com.ktgroup.application.dto.QuestionForm;
import com.ktgroup.application.entities.Answer;
import com.ktgroup.application.entities.Image;
import com.ktgroup.application.entities.Question;
import com.ktgroup.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;

    @Autowired
    private Environment env;

    @Transactional
    public void createNewQuestion(QuestionForm form) {
        Question question = new Question();
        question.setContent(form.getContent());
        question.setNumber(form.getNumber());
        question.setListImage(handleImage(question, form.getImages()));
        List<Answer> listAnswers = new ArrayList<>();
        for (AnswerForm answer : form.getListAnswers()) {
            Answer ans = new Answer();
            ans.setContent(answer.getContent());
            ans.setTrue(answer.getIsTrue());
            ans.setQuestion(question);
            listAnswers.add(ans);
        }
        question.setAnswer(listAnswers);
        questionsRespository.save(question);
    }

    public List<Image> handleImage(Question question, MultipartFile[] data) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile c : data) {
            if (!c.isEmpty()) {
                Image image = new Image();
                image.setFileName(c.getOriginalFilename());
                image.setUrl(writeFile(c));
                image.setDescription("Create new question");
                image.setQuestion(question);
                images.add(image);
            }
        }
        return images;
    }

    public String writeFile(MultipartFile fileImage) {
        byte data[];
        String pathClassPath = env.getProperty("url-class-path");
        String pathFolderUpload = env.getProperty("url-upload-folder");
        String fileName = fileImage.getOriginalFilename();
        try {
            data = fileImage.getBytes();
            File file = new File(pathClassPath + pathFolderUpload + "/" + fileName);
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pathFolderUpload + "/" + fileName;
    }

    public List<Question> getAllQuestions() {
        return questionsRespository.findAll();
    }
}
