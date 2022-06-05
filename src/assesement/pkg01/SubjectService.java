/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesement.pkg01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Duy
 */
public class SubjectService {

    private static Map<String, Subject> mySubject = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    static {
        Subject subject = new Subject("toan cap cap", "subJectName", 9);
        mySubject.put("TOAN", subject);
    } //testttttttttttttttttttttttttttttttttttttt

    static {
        Subject subject = new Subject("ky nang lam vie nhom", "subJectName", 7);
        mySubject.put("SSL", subject);
    }

    CheckValidInformation checkValidInformation = new CheckValidInformation();

    public String getNameOfSubjectById(String subjectID) {
        Subject subject = mySubject.get(subjectID);
        return subject.getSubJectName();
    }

    public void showSubject() {
        System.out.println(mySubject);
    }

    public String getSubjectNameBySubjectId(String subjectId) {
        return mySubject.get(subjectId).getSubJectName();
    }

    //function3
    public void createSubject() {

        String subjectId = inputIdSubject();

        String subJectName = inputSubjectName();

        int credit = inputCredit();

        Subject subject = new Subject(subjectId, subJectName, credit);

        mySubject.put(subjectId, subject);
        System.out.println("Created success!");
    }

    private String inputIdSubject() {
        boolean isExistSubjectId;
        String subjectId;
        do {
//            checkValidInformation.checkNull(subjectId);
            while (true) {
                System.out.print("Please input a subject ID: ");
                subjectId = sc.nextLine().toUpperCase();
                if (subjectId == null || subjectId.isBlank() || subjectId.isEmpty()) {
                    System.out.println("your input must be valid! Please input subject name again!!");
                } else {
                    break;
                }
            }

            isExistSubjectId = mySubject.containsKey(subjectId);
            if (isExistSubjectId) {
                System.out.print("Your subject is already exist! ");
            }
        } while (isExistSubjectId);
        return subjectId;
    }

    private String inputSubjectName() {
        String name;
        boolean flag;
        do {
            System.out.print("Please input student's subject name: ");
            name = sc.nextLine();
            if (name == null || name.isBlank() || name.isEmpty()) {
                System.out.println("your input must be valid! Please input subject name again!!");
                flag = true;
            } else {
                break;
            }
        } while (flag);
        return name;
    }

    private int inputCredit() {
        int credit;
        do {
            try {
                System.out.print("Please input credit: ");
                credit = Integer.parseInt(sc.nextLine());
                if (credit >= 0 && credit <= 10) {
                    break;
                } else {
                    System.out.println("input credit from 1-10!");
                }
            } catch (Exception e) {
                System.out.println("your input must be valid! Please input credit again!!");
            }
        } while (true);
        return credit;
    }
    //function4

    public void updatingSubject() {

        String subjectId = inputSubjectId();
        printSubjectExistOrNot(subjectId);
        boolean flag = isExistSubjectInHashMap(subjectId);
        if (flag == true) {
            miniMenuForUpdateSubject();
            String optionDeleteOrUpdate = optionDeleteOrUpdate();
            //         updateOrDelete(deleteOrUpdate, subjectId);
            switch (optionDeleteOrUpdate) {
                case "1":
                    updateSubject(subjectId);
                    backToTheUpdateMenu();
                    break;
                case "2":
                    confirmDelete(subjectId);
                case "3":
                    break;
            }
        }
    }

    //    public void updatingStudent() {
//        String studentId = inputStudentId();
//        printStudentExistOrNot(studentId);
//        boolean flag = isExistStudentInHashMap(studentId);
//        if (flag == true) {
//            miniMenuForUpdateStudent();
//            String optionUpdateOrDelete = optionUpdateOrDelete();
//            switch (optionUpdateOrDelete) {
//                case "1":
//                    updateStudent(studentId);
//                    backToTheUpdateMenu();
//                    break;
//                case "2":
//                    confirmDelete(studentId);
//                    break;
//                case "3":
//                    break;
//            }
//        }
//    }
//    public void updateOrDelete(String choice, String subjectId){
//        switch (choice) {
//                case "1":
//                    updateSubject(subjectId);
//                    backToTheUpdateMenu();
//                    break;
//                case "2":
//                    confirmDelete(subjectId);
//                case "3":
//                    break;
//        }
//    }
    public void confirmDelete(String subjectId) {
        System.out.println("Do you really want to delete this subject (Y/N)");
        System.out.print("Please choose (Y/N): ");
        String choose = sc.next().toUpperCase();
        switch (choose) {
            case "Y":
                removeSubject(subjectId);
                System.out.println("Delete success");
                backToTheUpdateMenu();

                break;
            case "N":
                System.out.println("Delete fail");
                backToTheUpdateMenu();
                break;
        }
    }

    public void printSubjectExistOrNot(String subjectId) {
        if (mySubject.containsKey(subjectId) == false) {
            System.out.println("subject does not exist!");
        } else {
            System.out.println("subject exist");
        }
    }

    public boolean isExistSubjectInHashMap(String subjectId) {
        return mySubject.containsKey(subjectId);
    }

    private void removeSubject(String subjectId) {
        mySubject.remove(subjectId);
    }

    private String inputSubjectId() {
        System.out.print("Please enter the subject ID: ");
        String inputId = sc.next();
        return inputId;
    }

    private String optionDeleteOrUpdate() {
        System.out.print("PLEASE ENTER YOUR CHOICE:");
        String option = sc.next();
        return option;
    }

    private void updateSubject(String Id) {
        String subjectName = inputSubjectName();
        int credit = inputCredit();
        Subject subject = new Subject(Id, subjectName, credit);
        mySubject.put(Id, subject);
        System.out.println("Update successfully!");
    }

    private void miniMenuForUpdateSubject() {
        System.out.println("THIS IS A UPDATE MENU");
        System.out.println("--------------- MENU--------------");
        System.out.println("1. Update a Subject");
        System.out.println("2. Delete a Subject");
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
                miniMenuForUpdateSubject();
                String updateOrDelete = optionDeleteOrUpdate();
                if ("3".equals(updateOrDelete)) {
                    return;
                }
                updatingSubject();
                break;
            case "N":
                System.out.println("This is your main menu");
                break;
        }
    }

    public void showContinuteCreating() {
        boolean checkTrueFlase = true;
        do {
            System.out.println("Do you want to continuous create new subject or go back to the main menu:");
            System.out.print("Choose [Y/N]: ");
            String continuousOrGoBack = sc.next().toUpperCase();
            switch (continuousOrGoBack) {
                case "Y":
                    createSubject();
//                    checkTrueFlase = false;
                    break;
                case "N":
                    System.out.println("THIS IS YOUR MENU");
                    checkTrueFlase = false;
                    break;
            }
        } while (checkTrueFlase);
    }

}
