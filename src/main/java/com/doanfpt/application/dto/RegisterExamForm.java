package com.doanfpt.application.dto;

import org.springframework.web.multipart.MultipartFile;

public class RegisterExamForm {

    public Long examId;
    public String lastName;
    public String middleName;
    public String firstName;
    public Integer gender;
    public String birthDay;
    public String address1;
    public String address2;
    public Long wardId;
    public String email;
    public String numberPhone;
    public String workingUnit;
    public String identityCardNumber;
    public Long placeOfIssue;
    public String typeDrivingLicense;
    public String issueDate;
    public MultipartFile identityCardImageFront;
    public MultipartFile identityCardImageBack;
    public MultipartFile imagePortrait;
    public MultipartFile healthCertificationFile;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getWorkingUnit() {
        return workingUnit;
    }

    public void setWorkingUnit(String workingUnit) {
        this.workingUnit = workingUnit;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public Long getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(Long placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getTypeDrivingLicense() {
        return typeDrivingLicense;
    }

    public void setTypeDrivingLicense(String typeDrivingLicense) {
        this.typeDrivingLicense = typeDrivingLicense;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public MultipartFile getIdentityCardImageFront() {
        return identityCardImageFront;
    }

    public void setIdentityCardImageFront(MultipartFile identityCardImageFront) {
        this.identityCardImageFront = identityCardImageFront;
    }

    public MultipartFile getIdentityCardImageBack() {
        return identityCardImageBack;
    }

    public void setIdentityCardImageBack(MultipartFile identityCardImageBack) {
        this.identityCardImageBack = identityCardImageBack;
    }

    public MultipartFile getImagePortrait() {
        return imagePortrait;
    }

    public void setImagePortrait(MultipartFile imagePortrait) {
        this.imagePortrait = imagePortrait;
    }

    public MultipartFile getHealthCertificationFile() {
        return healthCertificationFile;
    }

    public void setHealthCertificationFile(MultipartFile healthCertificationFile) {
        this.healthCertificationFile = healthCertificationFile;
    }

}
