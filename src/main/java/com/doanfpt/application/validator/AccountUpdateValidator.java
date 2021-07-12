/**
 * 
 */
package com.doanfpt.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.doanfpt.application.dto.AccountForm;
import com.doanfpt.application.utils.ValidationApplicationUtils;

/**
 * @author tamdu
 *
 */
@Component
public class AccountUpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Kiểm tra các field của AppUserForm.
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationApplicationUtils.rejectEmailIncorrectFormat(errors, "email", "Pattern.appUserForm.email");
    }
}
