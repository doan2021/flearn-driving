package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.application.common.Common;
import com.doanfpt.application.common.Constant;
import com.doanfpt.application.dto.FormSearchExam;
import com.doanfpt.application.entities.Exam;
import com.doanfpt.application.respone.ResponeData;
import com.doanfpt.application.responsitories.ExamRepository;
import com.doanfpt.application.specification.ExamSpecification;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private AddressServices addressServices;

    public ResponeData getDataRegisterExamPage(FormSearchExam formSearchExam) {
        ResponeData responeData = new ResponeData();
        if (formSearchExam.getPageNumber() == null) {
            formSearchExam.setPageNumber(0);
        }
        Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false)
                .and(ExamSpecification.hasDateRegisExamEndFrom(Common.getSystemDate())));
        PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), 9);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        responeData.putResult(Constant.PAGE_CONTENT_NAME, listExam);
        responeData.putResult("listProvince", addressServices.findAllProvince());
        return responeData;
    }

}
