/**
 * 
 */
package com.flearndriving.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.application.dto.AccountForm;
import com.flearndriving.application.responsitories.AccountsRespository;

/**
 * @author tamdu
 *
 */
@Component
public class AccountValidator implements Validator {

    @Autowired
    private AccountsRespository appUserRespository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm appUserForm = (AccountForm) target;
        // Kiểm tra các field của AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.accountForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.accountForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.accountForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.accountForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.accountForm.gender");

        if (appUserRespository.findByEmail(appUserForm.getEmail()) != null) {
            // Email đã được sử dụng bởi tài khoản khác.
            errors.rejectValue("email", "Duplicate.accountForm.email");
        }
        if (!errors.hasFieldErrors("userName")) {
            boolean existUserName = appUserRespository.existsByUserName(appUserForm.getUserName());
            if (existUserName) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.accountForm.userName");
            }
        }
        if (appUserForm.getPassword().length() < 8) {
            // Email đã được sử dụng bởi tài khoản khác.
            errors.rejectValue("password", "Pattern.accountForm.password");
        }
        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.accountForm.confirmPassword");
            }
        }
    }
}
