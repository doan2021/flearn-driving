package com.doanfpt.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doanfpt.application.dto.FormSearchExam;
import com.doanfpt.application.respone.ResponeData;
import com.doanfpt.application.services.AddressServices;
import com.doanfpt.application.services.ExamService;

@Controller
public class ExamController {
    
    @Autowired
    ExamService examServices;
    
    @Autowired
    AddressServices addressServices;

    @GetMapping(value = { "/register-exam" })
    public String visitExamPage(Model model) {
        return "register-exam";
    }
    
    @GetMapping(value = { "/search-exam" })
    public @ResponseBody ResponeData searchExam(FormSearchExam formSearchExam) {
        return examServices.getDataRegisterExamPage(formSearchExam);
    }
    
    @GetMapping(value = { "/load-district" })
    public @ResponseBody ResponeData loadDistrict(Long provinceId) {
        ResponeData responeData = new ResponeData();
        responeData.putResult("listDistrict", addressServices.findAllDistrictByProvinceId(provinceId));
        return responeData;
    }
    
    @GetMapping(value = { "/load-ward" })
    public @ResponseBody ResponeData loadWard(Long districtId) {
        ResponeData responeData = new ResponeData();
        responeData.putResult("listWard", addressServices.findAllWardByDistrictId(districtId));
        return responeData;
    }

    @GetMapping(value = { "/update-info-driving-licence" })
    public String visitPageRegistExam(Model model) {
        return "update-info-driving-licence";
    }
    
    @GetMapping(value = { "/update-info-driving-licence1" })
    public String visitPageRegistExam1(Model model) {
        return "update-info-driving-licence1";
    }
}
