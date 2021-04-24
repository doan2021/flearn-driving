/**
 * 
 */
package com.ktgroup.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ktgroup.application.dto.AppUserForm;
import com.ktgroup.application.responsitories.AppUserRespository;

/**
 * @author tamdu
 *
 */
@Component
public class AppUserValidator implements Validator {

    @Autowired
    private AppUserRespository appUserRespository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserForm appUserForm = (AppUserForm) target;
        // Kiểm tra các field của AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");

        boolean existEmail = appUserRespository.existsByEmail(appUserForm.getEmail());
        if (existEmail) {
            // Email đã được sử dụng bởi tài khoản khác.
            errors.rejectValue("email", "Duplicate.appUserForm.email");
        }
        if (!errors.hasFieldErrors("userName")) {
            boolean existUserName = appUserRespository.existsByUserName(appUserForm.getUserName());
            if (existUserName) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
            }
        }

        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
            }
        }
    }

}
