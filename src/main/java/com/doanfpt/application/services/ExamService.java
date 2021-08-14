package com.doanfpt.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doanfpt.application.common.Common;
import com.doanfpt.application.common.Constant;
import com.doanfpt.application.common.MimeTypes;
import com.doanfpt.application.component.DataSettingComponent;
import com.doanfpt.application.dto.FormSearchExam;
import com.doanfpt.application.dto.RegisterExamForm;
import com.doanfpt.application.dto.ResponeData;
import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.Document;
import com.doanfpt.application.entities.Exam;
import com.doanfpt.application.entities.ExamProfile;
import com.doanfpt.application.entities.Province;
import com.doanfpt.application.entities.Ward;
import com.doanfpt.application.responsitories.DocumentRespository;
import com.doanfpt.application.responsitories.ExamProfileResponsitory;
import com.doanfpt.application.responsitories.ExamRepository;
import com.doanfpt.application.responsitories.ProvinceResponsitory;
import com.doanfpt.application.responsitories.WardResponsitory;
import com.doanfpt.application.specification.ExamSpecification;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private AddressServices addressServices;
    
    @Autowired
    ExamProfileResponsitory examProfileResponsitory;
    
    @Autowired
    WardResponsitory wardResponsitory;
    
    @Autowired
    ProvinceResponsitory provinceResponsitory;
    
    @Autowired
    AccountServices accountServices;
    
    @Autowired
    private DataSettingComponent dataSetting;
    
    @Autowired
    private DocumentRespository documentRespository;

    public ResponeData getDataRegisterExamPage(FormSearchExam formSearchExam) {
        ResponeData responeData = new ResponeData();
        if (formSearchExam.getPageNumber() == null) {
            formSearchExam.setPageNumber(0);
        }
        Specification<Exam> conditions = Specification.where(ExamSpecification.hasDateRegisExamEndFrom(Common.getSystemDate()));
        PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), 9);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        responeData.putResult(Constant.PAGE_CONTENT_NAME, listExam);
        responeData.putResult("listProvince", addressServices.findAllProvince());
        return responeData;
    }

    @Transactional
    public ResponeData registerExam(RegisterExamForm registerExamForm) {
        ResponeData responeData = new ResponeData();
        if (registerExamForm == null) {
            responeData.putResult("status", "error");
            responeData.putResult("message", "Tham số truyền vào không đúng!");
            return responeData;
        }
        Account account = accountServices.getAccountLogin();
        Exam exam = examRepository.getOne(registerExamForm.getExamId());
        // Kiểm tra người dùng đã đăng ký kì thi này chưa
        Boolean exists = examProfileResponsitory.existsByExamAndAccount(exam, account);
        if (exists) {
            responeData.putResult("status", "exists");
            responeData.putResult("message", "Bạn đã đăng ký kì thi này rồi!");
            return responeData;
        }
        ExamProfile examProfile = new ExamProfile();
        examProfile.setExam(exam);
        examProfile.setAccount(account);
        examProfile.setStatus(Constant.STS_SUBMITTED);
        examProfile.setLastName(registerExamForm.getLastName());
        examProfile.setMiddleName(registerExamForm.getMiddleName());
        examProfile.setFirstName(registerExamForm.getFirstName());
        examProfile.setGender(registerExamForm.getGender());
        examProfile.setBirthDay(Common.stringToDate(registerExamForm.getBirthDay()));
        examProfile.setAddress1(registerExamForm.getAddress1());
        examProfile.setAddress2(registerExamForm.getAddress2());
        Ward ward = wardResponsitory.getOne(registerExamForm.getWardId());
        examProfile.setWard(ward);
        examProfile.setEmail(registerExamForm.getEmail());
        examProfile.setNumberPhone(registerExamForm.getNumberPhone());
        examProfile.setWorkingUnit(registerExamForm.getWorkingUnit());
        examProfile.setIdentityCardNumber(registerExamForm.getIdentityCardNumber());
        Province province = provinceResponsitory.getOne(registerExamForm.getPlaceOfIssue());
        examProfile.setPlaceOfIssue(province);
        examProfile.setIssueDate(Common.stringToDate(registerExamForm.getIssueDate()));
        examProfileResponsitory.save(examProfile);
        
        // Handle document
        List<Document> listDocuments = new ArrayList<>();
        // Upload file CMND mặt trước
        if (registerExamForm.getIdentityCardImageFront() == null) {
            responeData.putResult("status", "missing");
            responeData.putResult("message", "File ảnh CMND mặt trước không tồn tại!");
            return responeData;
        } else {
            // Get file to client
            MultipartFile multipartFile = registerExamForm.getIdentityCardImageFront();
            // Init object document
            Document identityCardImageFront = new Document();
            identityCardImageFront.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_IDENTITY_CARD_IMAGE_FRONT_LABEL));
            identityCardImageFront.setOriginFileName(multipartFile.getOriginalFilename());
            identityCardImageFront.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
            identityCardImageFront.setContentType(multipartFile.getContentType());
            identityCardImageFront.setSize(multipartFile.getSize());
            identityCardImageFront.setType(Constant.TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_FRONT);
            identityCardImageFront.setDescription("Ảnh CMND mặt trước");
            identityCardImageFront.setCreateAt(Common.getSystemDate());
            identityCardImageFront.setCreateBy(Common.getUsernameLogin());
            identityCardImageFront.setPath(dataSetting.getDataSettingUploadImage().getValue());
            listDocuments.add(identityCardImageFront);
        }
        // Upload file CMND mặt sau
        if (registerExamForm.getIdentityCardImageBack() == null) {
            responeData.putResult("status", "missing");
            responeData.putResult("message", "File ảnh CMND mặt sau không tồn tại!");
            return responeData;
        } else {
            // Get file to client
            MultipartFile multipartFile = registerExamForm.getIdentityCardImageBack();
            Document identityCardImageBack = new Document();
            identityCardImageBack.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_IDENTITY_CARD_IMAGE_BACK_LABEL));
            identityCardImageBack.setOriginFileName(multipartFile.getOriginalFilename());
            identityCardImageBack.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
            identityCardImageBack.setContentType(multipartFile.getContentType());
            identityCardImageBack.setSize(multipartFile.getSize());
            identityCardImageBack.setType(Constant.TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_BACK);
            identityCardImageBack.setDescription("Ảnh CMND mặt sau");
            identityCardImageBack.setCreateAt(Common.getSystemDate());
            identityCardImageBack.setCreateBy(Common.getUsernameLogin());
            identityCardImageBack.setPath(dataSetting.getDataSettingUploadImage().getValue());
            listDocuments.add(identityCardImageBack);
        }
        // Upload file ảnh chân dung
        if (registerExamForm.getImagePortrait() == null) {
            responeData.putResult("status", "missing");
            responeData.putResult("message", "File ảnh chân dung không tồn tại!");
            return responeData;
        } else {
            MultipartFile multipartFile = registerExamForm.getImagePortrait();
            Document imagePortrait = new Document();
            imagePortrait.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_IMAGE_PORTRAIT_LABEL));
            imagePortrait.setOriginFileName(multipartFile.getOriginalFilename());
            imagePortrait.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
            imagePortrait.setContentType(multipartFile.getContentType());
            imagePortrait.setSize(multipartFile.getSize());
            imagePortrait.setType(Constant.TYPE_DOCUMENT_IMAGE_PORTRAIT);
            imagePortrait.setDescription("Ảnh chân dung");
            imagePortrait.setCreateAt(Common.getSystemDate());
            imagePortrait.setCreateBy(Common.getUsernameLogin());
            imagePortrait.setPath(dataSetting.getDataSettingUploadImage().getValue());
            listDocuments.add(imagePortrait);
        }
        // Upload file phiều khám sk
        if (registerExamForm.getImagePortrait() == null) {
            responeData.putResult("status", "missing");
            responeData.putResult("message", "File mềm phiếu khám sức khỏe không tồn tại!");
            return responeData;
        } else {
            MultipartFile multipartFile = registerExamForm.getHealthCertificationFile();
            Document healthCertificationFile = new Document();
            healthCertificationFile.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_HEALTH_CERTIFICATION_FILE_LABEL));
            healthCertificationFile.setOriginFileName(multipartFile.getOriginalFilename());
            healthCertificationFile.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
            healthCertificationFile.setContentType(multipartFile.getContentType());
            healthCertificationFile.setSize(multipartFile.getSize());
            healthCertificationFile.setType(Constant.TYPE_DOCUMENT_HEALTH_CERTIFICATION_FILE);
            healthCertificationFile.setDescription("Phiếu khám sức khỏe");
            healthCertificationFile.setCreateAt(Common.getSystemDate());
            healthCertificationFile.setCreateBy(Common.getUsernameLogin());
            healthCertificationFile.setPath(dataSetting.getDataSettingUploadPdf().getValue());
            listDocuments.add(healthCertificationFile);
        }
        documentRespository.saveAll(listDocuments);
        responeData.putResult("status", "success");
        responeData.putResult("message", "Đăng ký thành công!");
        return responeData;
    }
}
