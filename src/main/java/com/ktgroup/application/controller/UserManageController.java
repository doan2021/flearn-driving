package com.ktgroup.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktgroup.application.common.Constant;
import com.ktgroup.application.dto.AppUserForm;
import com.ktgroup.application.entities.AppUser;
import com.ktgroup.application.entities.Role;
import com.ktgroup.application.services.AppUserServices;
import com.ktgroup.application.services.RoleServices;
import com.ktgroup.application.validator.AppUserValidator;

@Controller
@RequestMapping("/user-manage")
public class UserManageController {

    @Autowired
    AppUserServices appUserServices;

    @Autowired
    RoleServices roleServices;
    
    @Autowired
    private AppUserValidator appUserValidator;
    
    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == AppUserForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @GetMapping(value = { "/index" })
    public String welcomePage(Model model) {
        List<AppUser> listUser = appUserServices.findAllUser();
        List<Role> listRole = roleServices.findAllRole();
        AppUserForm appUserForm = new AppUserForm();
        model.addAttribute("listUser", listUser);
        model.addAttribute(Constant.LABEL_NAME_SCREEN, "user");
        model.addAttribute("appUserForm", appUserForm);
        model.addAttribute("listRole", listRole);
        return "user-managerment";
    }

    @PostMapping(value = { "/create-user" })
    public String createUser(@ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, BindingResult result, final RedirectAttributes redirectAttributes, Model model) {
        // Validate result
        if (result.hasErrors()) {
            List<Role> listRole = roleServices.findAllRole();
            model.addAttribute("listRole", listRole);
            return "user-managerment";
        }
        appUserServices.createAppUser(appUserForm);
        return "user-managerment";
    }
}
