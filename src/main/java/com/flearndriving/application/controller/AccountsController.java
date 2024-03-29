package com.flearndriving.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.AccountForm;
import com.flearndriving.application.dto.AccountUpdateForm;
import com.flearndriving.application.entities.Chapter;
import com.flearndriving.application.services.AccountServices;
import com.flearndriving.application.services.ChapterServices;
import com.flearndriving.application.services.DrivingLicenseServices;
import com.flearndriving.application.services.ExamQuestionsServices;
import com.flearndriving.application.services.QuestionServices;
import com.flearndriving.application.services.RoleServices;
import com.flearndriving.application.services.TrialExamResultServices;
import com.flearndriving.application.validator.AccountUpdateValidator;
import com.flearndriving.application.validator.AccountValidator;

@Controller
public class AccountsController {

    @Autowired
    ExamQuestionsServices examQuestionsServices;

    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @Autowired
    TrialExamResultServices trialExamResultServices;

    @Autowired
    AccountServices accountsServices;

    @Autowired
    RoleServices roleServices;

    @Autowired
    private AccountUpdateValidator accountUpdateValidator;

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    ChapterServices chapterServices;

    @Autowired
    QuestionServices questionServices;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == AccountForm.class) {
            dataBinder.setValidator(accountValidator);
        }

        if (target.getClass() == AccountUpdateForm.class) {
            dataBinder.setValidator(accountUpdateValidator);
        }
    }

    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        model.addAttribute("accountUpdateForm", accountsServices.getAccountLoginInfo());
        return "view-profile";
    }

    @PostMapping(value = { "/update-profile" })
    public String updateProfile(@Validated AccountUpdateForm accountUpdateForm, BindingResult result, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            return "update-profile";
        }
        accountsServices.updateAccount(accountUpdateForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Cập nhật thông tin thành công!");
        return "redirect:view-profile";
    }

    @GetMapping(value = { "/view-profile-registed-exam" })
    public String viewProfileRegistedExam(Model model) {
        model.addAttribute("account", accountsServices.getAccountLoginInfo());
        return "view-profile-registed-exam";
    }

    @GetMapping(value = { "/view-profile-learning-progress" })
    public String viewProfileLearningProgressLong(Model model, Chapter chapter) {
        model.addAttribute("account", accountsServices.getAccountLoginInfo());
        model.addAttribute("listLearningProgressChapter", chapterServices.learningProgressChapter());
        return "view-profile-learning-progress";
    }

    @GetMapping(value = { "/view-history-trial-test" })
    public String viewHistoryTrialTest(Model model) {
        model.addAttribute("account", accountsServices.getAccountLoginInfo());
        model.addAttribute("listTrialExamResult", trialExamResultServices.findAllTrialExamResult());
        model.addAttribute("totalTrialExamResult", trialExamResultServices.countTrialExamResult());
        model.addAttribute("numberOfPass", trialExamResultServices.getNumberOfPass());
        return "view-history-trial-test";
    }

    @GetMapping(value = { "/detail-history-trial-test" })
    public String detailHistoryTrialTest(Long trialExamResultId, Model model) {
        model.addAttribute("trialExamResult", trialExamResultServices.getOne(trialExamResultId));
        return "detail-history-trial-test";
    }

    @PostMapping(value = { "/upload-avatar" })
    public String uploadAvatar(MultipartFile data, RedirectAttributes redirAttrs) {
        accountsServices.uploadAvatar(data);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Sửa ảnh đại diện thành công!");
        return "redirect:view-profile";
    }
}
