package com.flearndriving.application.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.flearndriving.application.dto.CustomOAuth2User;

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
        return fullNameGoogle.split(" ")[0];
    }

    public static String getMiddleName(String fullNameGoogle) {
        if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
            return fullNameGoogle;
        }
        String[] name = fullNameGoogle.split(" ");
        String middleName = "";
        for (int i = 1; i < name.length - 1; i++) {
            if (i == name.length - 2) {
                middleName = middleName.concat(name[i]);
            } else {
                middleName = middleName.concat(name[i]).concat(" ");
            }
        }
        return middleName;
    }

    public static boolean isValidEmailAddress(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_EMAIL);
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
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    public static Date getLastOfTheDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 11);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static String getUsernameLogin() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return oAuth2User.getEmail();
        }
        return null;
    }

    public static Date getSystemDate() {
        return new Date();
    }

    public static String dateToString(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static String generateFileName(MultipartFile multipartFile, String label) {
        // Init extension new
        String extension = MimeTypes.lookupExt(multipartFile.getContentType());
        // Remove extension old
        String fileName = multipartFile.getOriginalFilename().substring(0,
                multipartFile.getOriginalFilename().lastIndexOf("."));
        return Common.dateToString(Common.getSystemDate(), Constant.PATTERN_FORMAT_DATE_TIME) + "_" + label + "_"
                + fileName + "." + extension;
    }

    public static boolean isValidPhoneNumber(String numberPhone) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_PHONENUMBER);
        java.util.regex.Matcher m = p.matcher(numberPhone);
        return m.matches();
    }

}
