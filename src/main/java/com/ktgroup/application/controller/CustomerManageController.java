package com.ktgroup.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ktgroup.application.common.Constant;
import com.ktgroup.application.respone.ResponeData;
import com.ktgroup.application.services.CustomerServices;

@Controller
@RequestMapping("/customer-manage")
public class CustomerManageController {

    @Autowired
    CustomerServices customerServices;

    @GetMapping(value = { "/index " })
    public String pageListDataCustomer(Model model) {
    	model.addAttribute(Constant.LABEL_NAME_SCREEN, "listData");
        return "list-data";
    }

    @GetMapping(value = { "/get-list-data-customer" })
    @ResponseBody
    public ResponeData getListDataCustomer(int pageCurrent) {
        return customerServices.findAllCustomer(pageCurrent);
    }

    @PostMapping(value = { "/import-data" })
    @ResponseBody
    public ResponeData importData(MultipartFile fileCsv, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return customerServices.importDataCustomer(fileCsv, loginedUser.getUsername());
    }

    @GetMapping(value = { "/get-history-customer" })
    @ResponseBody
    public ResponeData getHistoryCustomer(long customerId) {
    	return customerServices.getHistoryCustomer(customerId);
    }
}
