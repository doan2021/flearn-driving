package com.doanfpt.application.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.doanfpt.application.model.AccountPrincipal;

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

    
    public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateReturn = new Date();
        try {
            dateReturn = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateReturn;
    }
    
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static String getUsernameLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return loginedUser.getUsername();
    }
    
    public static Date getSystemDate() {
        return new Date();
    }
    
    public static String writeFile(MultipartFile fileImage, String urlClassPath, String urlUploadFolder) {
        byte data[];
        String fileName = fileImage.getOriginalFilename();
        try {
            data = fileImage.getBytes();
            File file = new File(urlClassPath + urlUploadFolder + "/" + fileName);
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlUploadFolder + "/" + fileName;
    }
}
