/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesement.pkg01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Duy
 */
public class StudentService {

    private static Map<String, Student> myStudents = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    static {
        Student student = new Student("DUY", "firstName", "lastName", "gender", new Date(), "email", "phoneNumber");
        myStudents.put("DUY", student);
    }  //testttttttttttttttttttttttttttttttttttttttttt

    static {
        Student student = new Student("KHANG", "firstName", "lastName", "gender", new Date(), "email", "phoneNumber");
        myStudents.put("KHANG", student);
    }

    public void showStudent() {
        System.out.println(myStudents);
    }

    public String getNameOfStudentById(String studentID) {
        Student student = myStudents.get(studentID);
        return student.getFirstName() + " " + student.getLastName();
    }

//    public Student getStudentByStudentId(String studentID) {
//        return myStudents.get(studentID);
//    }
    //function1
    public void createStudent() {

        String studentId = inputIdStudent();

        String firstName = inputFirstName();

        String lastName = inpuLastName();

        String gender = inputGender();

        Date dob = tesstdate();

        String email = inputEmail();

        String phoneNumber = inputPhoneNumber();

        Student st = new Student(studentId, firstName, lastName, gender, dob, email, phoneNumber);

        myStudents.put(studentId, st);
    }

    
    
    private String inputIdStudent() {
        boolean isExistStudentId;
        String studentId;
        CheckValidInformation checkValidInformation = new CheckValidInformation();
        do {
            boolean flag;
            do {
                System.out.print("Please input a student ID (ex: SE160821): ");
                studentId = sc.nextLine().trim().toUpperCase();
                if (studentId == null || studentId.isBlank() || studentId.isEmpty()) {
                    System.out.println("your input must be valid! Please input again!!");
                    flag = true;
                } else if (checkValidInformation.checkStudentIdValid(studentId)) {
                    break;
                } else {
                    System.out.println("your input must be valid! Please input again!!");
                    flag = true;
                }

            } while (flag);
            isExistStudentId = myStudents.containsKey(studentId);
            if (isExistStudentId) {
                System.out.println("Your input is already exist! ");
            }
        } while (isExistStudentId);
        return studentId;
    }

    private String inputFirstName() {
//        Scanner sc = new Scanner(System.in);
        String firstName;
        boolean flag;
        do {
            System.out.print("Please input student's first name: ");
            firstName = sc.nextLine();
            if (firstName == null || firstName.isBlank() || firstName.isEmpty()) {
                System.out.println("your input must be valid! Please input first name again!!");
                flag = true;
            } else {
                break;
            }
        } while (flag);
        return firstName;
    }

    private String inpuLastName() {
//        Scanner sc = new Scanner(System.in);
        String lastName;
        boolean flag;
        do {
            System.out.print("Please input student's last name: ");
            lastName = sc.nextLine();
            if (lastName == null || lastName.isBlank() || lastName.isEmpty()) {
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
            if (choose == null || choose.isBlank() || choose.isEmpty()) {
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

    private Date validateDate() {
        Date result = new Date();
        boolean isContinue = true;
        while (isContinue) {
            try {
                System.out.print("Please input a date of birth student: format:(MM/DD/YY) ");
                String dateOfBirth = sc.next();
                result = new Date(dateOfBirth);
                isContinue = false;

            } catch (Exception e) {
                System.out.println("Your input is wrong! Please input date of birth again!: ");
            }
        }
        return result;
    }

    private Date tesstdate() {
        Date date = new Date();

        do {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Please input a date of birth student: format:(dd/MM/yyyy): ");
                String input = sc.nextLine();
                date = simpleDateFormat.parse(input);
                if (!input.trim().equals(" ")) {
                    break;
                }
            } catch (ParseException e) {
                System.out.println("your input must be valid! Please input again!!");
            }
        } while (true);

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

    public void showContinuteCreating() {
        boolean checkTrueFlase = true;
        do {
            System.out.println("Do you want to continuous create new student or go back to the main menu:");
            System.out.print("Choose [Y/N]: ");
            String continuousOrGoBack = sc.next().toUpperCase();
            switch (continuousOrGoBack) {
                case "Y":
                    createStudent();
                    break;
                case "N":
                    System.out.println("THIS IS YOUR MENU");
                    checkTrueFlase = false;
                    break;
            }
        } while (checkTrueFlase);
    }

    //if stdId in HashMAp return true else false
    public boolean isExistStudentInHashMap(String studentId) {
        return myStudents.containsKey(studentId);
    }

    private void printStudentExistOrNot(String studentId) {
        if (myStudents.containsKey(studentId) != true) {
            System.out.println("Student dose not exist");
        } else {
            System.out.println("Student is exist");
        }
    }

    // UPDATE FUNCTION 
    public void updatingStudent() {
        String studentId = inputStudentId().toUpperCase();
        printStudentExistOrNot(studentId);
        boolean flag = isExistStudentInHashMap(studentId);
        if (flag == true) {
            miniMenuForUpdateStudent();
            String optionUpdateOrDelete = optionUpdateOrDelete();
            switch (optionUpdateOrDelete) {
                case "1":
                    updateStudent(studentId);
                    backToTheUpdateMenu();
                    break;
                case "2":
                    confirmDelete(studentId);
                    break;
                case "3":
                    break;
            }
        }
    }

    public void confirmDelete(String studentId) {
        boolean flag = false;
        do {
            System.out.println("Do you really want to delete this student (Y/N)");
            System.out.print("Please choose (Y/N): ");
            String choose = sc.next().toUpperCase();
            switch (choose) {
                case "Y":
                    deleteStudent(studentId);
                    System.out.println("Delete success");
                    backToTheUpdateMenu();
                    break;
                case "N":
                    System.out.println("Delete fail");
                    backToTheUpdateMenu();
                    break;
                default:
                    System.out.println("please input again: ");
                    flag = true;
            }
        } while (flag);
    }

    private String inputStudentId() {
        System.out.print("Please enter the student ID: ");
        String inputId = sc.next();
        return inputId;
    }

    private String optionUpdateOrDelete() {
        System.out.print("PLEASE ENTER YOUR CHOICE:");
        String option = sc.next();
        return option;
    }

    private void deleteStudent(String studentIdd) {
        myStudents.remove(studentIdd);
    }

    private void updateStudent(String Id) {

        String firstName = inputFirstName();

        String lastName = inpuLastName();

        String gender = inputGender();

        // Date dob = validateDate();
        Date dob = tesstdate();

        String email = inputEmail();

        String phoneNumber = inputPhoneNumber();

        Student st = new Student(Id, firstName, lastName, gender, dob, email, phoneNumber);

        myStudents.put(Id, st);
        System.out.println("Update Success!!");
    }

    private void miniMenuForUpdateStudent() {
        System.out.println("THIS IS A UPDATE MENU");
        System.out.println("--------------- MENU--------------");
        System.out.println("1. Update a Student");
        System.out.println("2. Delete a Student");
        System.out.println("3. Back to the main menu");
    }

    private String optionBackToTheUpdateMenu() {
        System.out.println("Do you want to go back to the ''update menu''?");
        System.out.print("Please choose (Y/N): ");
        String chooseBackToTheUpdateMenu = sc.next().toUpperCase();
        return chooseBackToTheUpdateMenu;
    }

    private void backToTheUpdateMenu() {
        String choice = optionBackToTheUpdateMenu();
        switch (choice) {
            case "Y":
                miniMenuForUpdateStudent();
                String updateOrDelete = optionUpdateOrDelete();
                if ("3".equals(updateOrDelete)) {
                    return;
                }
                updatingStudent();
                break;
            case "N":
                System.out.println("This is your main menu");
                break;
        }
    }
}
