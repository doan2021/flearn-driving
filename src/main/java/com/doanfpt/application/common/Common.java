package com.doanfpt.application.common;

public class Common {

	public static float percentQuestion(int correctNumber, int incorrectNumber) {
		return (correctNumber * 100.0f) / (correctNumber + incorrectNumber);
	}

	public static String getFirstName(String fullNameGoogle) {
		if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
			return fullNameGoogle;
		}
		String[] name = fullNameGoogle.split(" ");
		return name[name.length - 1];
	}

	public static String getLastName(String fullNameGoogle) {
		if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
			return fullNameGoogle;
		}
		String[] name = fullNameGoogle.split(" ");
		String lastName = "";
		for (int i = 0; i < name.length - 1; i++) {
			if (i == name.length - 2) {
				lastName = lastName.concat(name[i]);
			} else {
				lastName = lastName.concat(name[i]).concat(" ");
			}
		}
		return lastName;
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
