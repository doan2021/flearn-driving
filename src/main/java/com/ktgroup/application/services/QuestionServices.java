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
import com.ktgroup.application.entities.Answers;
import com.ktgroup.application.entities.Images;
import com.ktgroup.application.entities.Questions;
import com.ktgroup.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;

    @Autowired
    private Environment env;

    @Transactional
    public void createNewQuestion(QuestionForm form) {
        Questions question = new Questions();
        question.setContent(form.getContent());
        question.setNumber(form.getNumber());
        question.setListImages(handleImage(question, form.getImages()));
        List<Answers> listAnswers = new ArrayList<>();
        for (AnswerForm answer : form.getListAnswers()) {
            Answers ans = new Answers();
            ans.setContent(answer.getContent());
            ans.setTrue(answer.getIsTrue());
            ans.setQuestions(question);
            listAnswers.add(ans);
        }
        question.setAnswers(listAnswers);
        questionsRespository.save(question);
    }

    public List<Images> handleImage(Questions question, MultipartFile[] data) {
        List<Images> images = new ArrayList<>();
        for (MultipartFile c : data) {
            if (!c.isEmpty()) {
                Images image = new Images();
                image.setFileName(c.getOriginalFilename());
                image.setUrl(writeFile(c));
                image.setDescription("Create new question");
                image.setQuestions(question);
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

    public List<Questions> getAllQuestions() {
        return questionsRespository.findAll();
    }
}
