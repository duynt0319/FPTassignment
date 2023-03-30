/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

import checkValidation.CheckValidInformation;
import comparartor.StudentNameComparator;
import grade.GradeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author Duy
 */
public class StudentService {

    private static Map<String, Student> myStudents = new HashMap<>();
    private Scanner sc = new Scanner(System.in);
    CheckValidInformation checkValidInformation = new CheckValidInformation();

    public String getFNameAndLNameOfStudentById(String studentID) {
        Student student = myStudents.get(studentID);
        return student.getFirstName() + " " + student.getLastName();
    }

    public void createStudent() {

        String studentId = inputIdStudent();

        String firstName = inputFirstName();

        String lastName = inpuLastName();

        String gender = inputGender();

        Date dob = validDate();

        String email = inputEmail();

        String phoneNumber = inputPhoneNumber();

        Student st = new Student(studentId, firstName, lastName, gender, dob, email, phoneNumber);

        myStudents.put(studentId, st);
        System.out.println("create success");
        System.out.println(myStudents);
    }

    private String inputIdStudent() {
        boolean isExistStudentId;
        String studentId;

        do {
            boolean flag;
            do {
                System.out.print("Please input a student ID (ex: SE160821): ");
                studentId = sc.nextLine().trim().toUpperCase();
                if (studentId == null || studentId.isEmpty()) {
                    System.out.println("your input must be valid! Please input again!!");
                    flag = true;
                } else if (checkValidInformation.checkIsStudentIdValid(studentId)) {
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

    private Date getDate(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

    private Date validDate() {
        boolean check = true;
        Date date = null;
        int day, month, year;
        while (check) {
            String dateFormat = "\\d{2}/\\d{2}/\\d{4}";
            System.out.print("Input your date of birth (Ex: dd/MM/yyyy): ");
            String inputDate = sc.nextLine();
            if (inputDate.matches(dateFormat)) {
                try {
                    CheckValidInformation validation = new CheckValidInformation();
                    /*
                    StringTokenizer ho tro cat chuoi data thanh nhung chuoi nho tai vi tri "/"
                     */
                    StringTokenizer st = new StringTokenizer(inputDate, "/");
                    day = Integer.parseInt(st.nextToken());
                    month = Integer.parseInt(st.nextToken());
                    year = Integer.parseInt(st.nextToken());
                    if (validation.checkIsValidDate(year, month, day)) {
                        date = getDate(inputDate);
                        check = false;
                    } else {
                        /*
                        truong hop nay sai khi nhap fake ngay sinh
                         */
                        System.out.println("Please input your real date!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                /*
                sai khi nhap sai dinh dang
                 */
                System.out.println("Please input like form example(dd/MM/yyyy)!");
            }
        }
        return date;
    }

    private String inputEmail() {
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
            } else {
                break;
            }
        }
        return email;
    }

    private String inputPhoneNumber() {
        String inputPhoneNumber;
        sc = new Scanner(System.in);
        System.out.print("Input your phone number: ");
        inputPhoneNumber = sc.nextLine();
        while (checkValidInformation.checkIsPhoneValid(inputPhoneNumber) == false) {
            System.out.print("Pleases input valid phone number: ");
            inputPhoneNumber = sc.nextLine();
        }
        return inputPhoneNumber;
    }

    public void showContinueCreating() {
        boolean checkTrueFlase = true;
        do {
            System.out.println("Do you want to continuous create new student or go back to the main menu:");
            System.out.print("Choose [Y/N]: ");
            String continuousOrGoBack = sc.nextLine().toUpperCase();
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

    private void deleteStudent(String studentIdd) {
        myStudents.remove(studentIdd);
    }

    public void confirmDelete(String studentId) {
        boolean flag = false;
        do {
            System.out.println("Do you really want to delete this student (Y/N)");
            System.out.print("Please choose (Y/N): ");
            String choose = sc.nextLine().toUpperCase();
            final GradeService gradeService = new GradeService();
            switch (choose) {
                case "Y":
                    deleteStudent(studentId);
                    gradeService.removeStudent(studentId);
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
        String inputId = sc.nextLine();
        return inputId;
    }

    private String optionUpdateOrDelete() {
        System.out.print("PLEASE ENTER YOUR CHOICE:");
        return sc.nextLine();
    }

    private String inputFirstNameForUpdate() {
        String firstName;
        System.out.print("Please input student's first name: ");
        firstName = sc.nextLine();
        return firstName;
    }

    private String inpuLastNameForUpdate() {
        String lastName;
        System.out.print("Please input student's last name: ");
        lastName = sc.nextLine();
        return lastName;
    }

    private String inputGenderForUpdate() {
        boolean flag;
        do {
            System.out.println("Please input student's gender: (Male or Female)");
            System.out.print("Please choose (M/F): ");
            String choose = sc.nextLine().toUpperCase();
            switch (choose) {
                case "M":
                    return "Male";
                case "F":
                    return "Female";
            }
            if (" ".trim().equals(choose)) {
                break;
            } else if (!"M".equals(choose) || !"F".equals(choose)) {
                flag = true;
            }
        } while (true);
        return null;
    }

    private Date validDateForUpdate(String StudentId) {
        boolean check = true;
        Date date = null;
        int day, month, year;
        while (check) {
            String dateFormat = "\\d{2}/\\d{2}/\\d{4}";
            System.out.print("Input your date of birth (Ex: dd/MM/yyyy): ");
            String data = sc.nextLine();
            if (data.isEmpty()) {
                System.out.println("Keep old value");
                return myStudents.get(StudentId).getDateOfBirth();
            }
            if (data.matches(dateFormat)) {
                try {
                    CheckValidInformation validation = new CheckValidInformation();
                    /*
                    StringTokenizer ho tro cat chuoi data thanh nhung chuoi nho tai vi tri "/"
                     */
                    StringTokenizer st = new StringTokenizer(data, "/");
                    day = Integer.parseInt(st.nextToken());
                    month = Integer.parseInt(st.nextToken());
                    year = Integer.parseInt(st.nextToken());
                    if (validation.checkIsValidDate(year, month, day)) {
                        date = getDate(data);
                        check = false;
                    } else {
                        /*
                        truong hop nay sai khi nhap fake ngay sinh
                         */
                        System.out.println("Please input your real date!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                /*
                sai khi nhap sai dinh dang
                 */
                System.out.println("Please input like form example(dd/MM/yyyy)!");
            }
        }
        return date;
    }

    private String inputEmailForUpDate(String studentId) {
        String regexPattern = "^(.+)@(\\S+)$";
        String email;
        while (true) {
            System.out.print("Please input your email: ");
            email = sc.nextLine();
            if (email != null && !email.trim().equals("")) {
                boolean isMatches = Pattern.compile(regexPattern)
                        .matcher(email)
                        .matches();

                if (!isMatches) {
                    System.out.println("your input is wrong! Please input your email again!: ");
                } else {
                    break;
                }
            } else {
                System.out.println("keep old value");
                email = myStudents.get(studentId).getEmail();
                break;
            }
        }
        return email;
    }

    private String inputPhoneNumberForUpdate(String studentId) {
        String phoneNumber;
        sc = new Scanner(System.in);
        System.out.print("Input your phone number: ");
        phoneNumber = sc.nextLine();
        if (phoneNumber.isEmpty()) {
            return myStudents.get(studentId).getPhoneNumber();
        }
        while (checkValidInformation.checkIsPhoneValid(phoneNumber) == false) {
            System.out.print("Pleases input your real phone number: ");
            phoneNumber = sc.next();
        }
        return phoneNumber;
    }

    private void updateStudent(String studentId) {

        String firstName = inputFirstNameForUpdate();
        if (firstName == null || firstName.isEmpty()) {
            System.out.println("keep old value");
            firstName = myStudents.get(studentId).getFirstName();
        }

        String lastName = inpuLastNameForUpdate();
        if (lastName == null || lastName.isEmpty()) {
            System.out.println("keep old value");
            lastName = myStudents.get(studentId).getLastName();
        }

        String gender = inputGenderForUpdate();
        if (gender == null || gender.isEmpty()) {
            System.out.println("keep old value");
            gender = myStudents.get(studentId).getGender();
        }

        Date dob = validDateForUpdate(studentId);

        String email = inputEmailForUpDate(studentId);

        String phoneNumber = inputPhoneNumberForUpdate(studentId);

        Student st = new Student(studentId, firstName, lastName, gender, dob, email, phoneNumber);

        myStudents.put(studentId, st);
        System.out.println("Update Success!!");
        System.out.println(myStudents);
    }

    public void showStudent() {
        System.out.println(myStudents);
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
        String chooseBackToTheUpdateMenu = sc.nextLine().toUpperCase();
        while (!chooseBackToTheUpdateMenu.equals("Y") && !chooseBackToTheUpdateMenu.equals("N")) {
            System.out.println("Do you want to go back to the ''update menu''?");
            System.out.print("Please choose (Y/N): ");
            chooseBackToTheUpdateMenu = sc.nextLine().toUpperCase();
        }
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

    public Map<String, String> sortByStudentName(Set<String> studentIds) {
        Map<String, String> studentIdAndStudentName = new HashMap<>();
        for (String studentId : studentIds) {
            studentIdAndStudentName.put(studentId, getFNameAndLNameOfStudentById(studentId));
        }

        StudentNameComparator studentNameComparator = new StudentNameComparator(studentIdAndStudentName);
        Map<String, String> sortStudentNameByTreeMap = new TreeMap<>(studentNameComparator);
        sortStudentNameByTreeMap.putAll(studentIdAndStudentName);
        return sortStudentNameByTreeMap;
    }
}
