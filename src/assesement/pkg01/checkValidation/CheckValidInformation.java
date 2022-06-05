/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesement.pkg01.checkValidation;

import java.util.regex.Pattern;

/**
 *
 * @author Duy
 */
public class CheckValidInformation {

    public boolean checkStudentIdValid(String studentId) {
        String regexStudentId = "[A-Z]{2}[0-9]{6}";
        boolean isMatches = Pattern.compile(regexStudentId).matcher(studentId).matches();
        return isMatches;
    }
    
    public void checkNull(String input){    
            if (input == null || input.isEmpty()) {
                System.out.println("your input must be valid! Please icheck again!!"); 
            }
    }
}
