/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkValidation;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Duy
 */
public class CheckValidInformation {

    private Scanner sc = new Scanner(System.in);

    public boolean checkIsStudentIdValid(String studentId) {
        String regexStudentId = "[A-Z]{2}[0-9]{6}";
        boolean isMatches = Pattern.compile(regexStudentId).matcher(studentId).matches();
        return isMatches;
    }

    public boolean checkIsPhoneValid(String phoneNumber) {
        if ((phoneNumber.length() < 9 || phoneNumber.length() > 12) || phoneNumber.charAt(0) != '0') {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }
    private boolean checkYearValid(int year) {
        //get current year, month, day
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return !(year > currentYear || year < 1900);
    }

    private boolean checkMonthValid(int month) {
        return !(month > 12 || month < 1);
    }

    private boolean IsNamNhuan(int year) {
        if (year % 400 == 0) {
            return true;
        } else {
            return year % 4 == 0 && year % 100 != 0;
        }
    }

    public boolean checkIsValidDate(int year, int month, int day) {
        if(!checkYearValid(year)){
            return false;
        }
        if(!checkMonthValid(month)){
            return false;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                // day 31
                if (day > 31 || day <= 0) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                //day 30
                if (day > 30 || day <= 0) {
                    return false;
                }
                break;
            case 2:
                if (IsNamNhuan(year)) {
                    if (day > 29 || day <= 0) {
                        return false;
                    }
                } else {
                    if (day > 28 | day <= 0) {
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }
    
}
