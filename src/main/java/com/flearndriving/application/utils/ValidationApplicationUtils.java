/**
 * 
 */
package com.flearndriving.application.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.flearndriving.application.common.Common;

/**
 * @author tamdu
 *
 */
public class ValidationApplicationUtils extends ValidationUtils {
	/* validation email - Account */
	public static void rejectEmailIncorrectFormat(Errors errors, String field, String errorCode) {
		rejectIfEmptyOrWhitespace(errors, field, errorCode, null, null);
	}

	public static void rejectEmailIncorrectFormat(Errors errors, String field, String errorCode,
			@Nullable Object[] errorArgs, @Nullable String defaultMessage) {
		Assert.notNull(errors, "Errors object must not be null");
		Object value = errors.getFieldValue(field);
		if (Common.isValidEmailAddress(value.toString())) {
			errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		}
	}

	/* validation phonenumber - Account */
	public static void rejectPhoneNumberIncorrectFormat(Errors errors, String field, String errorCode) {
		rejectPhoneNumberIncorrectFormat(errors, field, errorCode, null, null);
	}

	public static void rejectPhoneNumberIncorrectFormat(Errors errors, String field, String errorCode,
			@Nullable Object[] errorArgs, @Nullable String defaultMessage) {
		Assert.notNull(errors, "Errors object must not be null");
		Object value = errors.getFieldValue(field);
		if (!Common.isValidPhoneNumber(value.toString())) {
			errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		}
	}
}
