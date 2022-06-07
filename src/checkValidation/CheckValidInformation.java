/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Duy
 */
public class CheckValidInformation {

    private Scanner sc = new Scanner(System.in);

    public boolean checkStudentIdValid(String studentId) {
        String regexStudentId = "[A-Z]{2}[0-9]{6}";
        boolean isMatches = Pattern.compile(regexStudentId).matcher(studentId).matches();
        return isMatches;
    }

    private String inputFirstName() {
        String firstName;
        boolean flag;
        do {
            System.out.print("Please input student's first name: ");
            firstName = sc.nextLine();
            if (firstName == null || firstName.isEmpty()) {
                System.out.println("your input must be valid! Please input first name again!!");
                flag = true;
            } else {
                break;
            }
        } while (flag);
        return firstName;
    }

    private String inpuLastName() {

        String lastName;
        boolean flag;
        do {
            System.out.print("Please input student's last name: ");
            lastName = sc.nextLine();
            if (lastName == null || lastName.isEmpty()) {
                System.out.println("your input must be valid! Please input last name again!!");
                flag = true;
            } else {
                break;
            }
        } while (flag);
        return lastName;
    }

    private String inputGender() {
        boolean flag;
        do {
            System.out.println("Please input student's gender: (Male or Female)");
            System.out.print("Please choose (M/F): ");
            String choose = sc.nextLine().toUpperCase();
            if (choose == null || choose.isEmpty()) {
                System.out.println("your input must be valid! Please input last name again!!");
                flag = true;
            } else {
                switch (choose) {
                    case "M":
                        return "Male";
                    case "F":
                        return "Female";
                    default:
                        System.out.println("Please choose again!!");
                        flag = true;
                }
            }
        } while (flag);
        return null;
    }

    private Date validDate() {
        Date date = new Date();

        Date currentDate = new Date();
        boolean isInputCorrectDate = true;
        do {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Please input a date of birth student: format:(dd/MM/yyyy): ");
                String input = sc.nextLine();
                date = simpleDateFormat.parse(input);

                if (date.after(currentDate)) {
                    System.out.println("Please input date before current date");
                } else {
                    isInputCorrectDate = false;
                }

            } catch (ParseException e) {
                System.out.println("your input must be valid! Please input again!!");
            }
        } while (isInputCorrectDate);

        return date;
    }

    
        private  String inputEmail() {
        String regexPattern = "^(.+)@(\\S+)$";
        String email;
        while (true) {        
            System.out.print("Please input your email: ");
            email = sc.nextLine();
            boolean isMatches = Pattern.compile(regexPattern)
                    .matcher(email)
                    .matches();
            
            if (!isMatches || email.trim().equals(" ")) {
                System.out.println("your input is wrong! Please input your email again!: ");
            }else{
                break;
            }
        }
        return email;
    }

    private String inputPhoneNumber() {
        String phoneNumber;
        System.out.print("Please input student's phone number: ");
        phoneNumber = sc.nextLine();
        while (phoneNumber.length() < 9 || phoneNumber.length() > 12 || phoneNumber.charAt(0) != '0' || phoneNumber.trim().equals(" ")) {
            System.out.print("your input is wrong! Please try again!: ");
            phoneNumber = sc.nextLine();
        }
        return phoneNumber;
    }
    
}
