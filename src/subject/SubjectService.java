/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subject;

import checkValidation.CheckValidInformation;
import comparartor.SubjectNameComparator;
import grade.GradeService;

import java.util.*;

/**
 *
 * @author Duy
 */
public class SubjectService {

    private static Map<String, Subject> mySubject = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    CheckValidInformation checkValidInformation = new CheckValidInformation();

    public String getNameOfSubjectById(String subjectID) {
        Subject subject = mySubject.get(subjectID);
        return subject.getSubJectName();
    }


    //function3
    public void createSubject() {

        String subjectId = inputIdSubject();

        String subJectName = inputSubjectName();

        int credit = inputCredit();

        Subject subject = new Subject(subjectId, subJectName, credit);

        mySubject.put(subjectId, subject);
        System.out.println("Created success!");
        System.out.println(mySubject);
    }

    private String inputIdSubject() {
        boolean isExistSubjectId;
        String subjectId;
        do {

            while (true) {
                System.out.print("Please input a subject ID: ");
                subjectId = sc.nextLine().toUpperCase();
                if (subjectId == null || subjectId.isEmpty()) {
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
            System.out.print("Please input subject name: ");
            name = sc.nextLine();
            if (name == null || name.isEmpty()) {
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
    private void removeSubject(String subjectId) {
        mySubject.remove(subjectId);
    }
    
    public void confirmDelete(String subjectId) {
        System.out.println("Do you really want to delete this subject (Y/N)");
        System.out.print("Please choose (Y/N): ");
        String choose = sc.nextLine().toUpperCase();
        final GradeService gradeService = new GradeService();
        switch (choose) {
            case "Y":
                removeSubject(subjectId);
                gradeService.removeSubject(subjectId);
                
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

    private String inputSubjectId() {
        System.out.print("Please enter the subject ID: ");
        String inputId = sc.nextLine().toUpperCase();
        return inputId;
    }

    private String optionDeleteOrUpdate() {
        System.out.print("PLEASE ENTER YOUR CHOICE:");
        String option = sc.nextLine();
        return option;
    }

    private String inputSubjectNameForUpdate() {
        String name;
        System.out.print("Please input subject name: ");
        name = sc.nextLine();
        return name;
    }

    private int inputCreditForUpdate(String studentId) {
        int credit = 0;
        do {

            System.out.print("Please input credit: ");
            try {
                credit = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("keep old value");
                credit = mySubject.get(studentId).getCredit();
                System.out.println(credit);
            }
            if (credit >= 0 && credit <= 10) {
                break;
            } else {
                System.out.println("input credit from 1-10!");
            }
        } while (true);
        return credit;
    }
    public void showSubjetc(){
        System.out.println(mySubject);
    }
    private void updateSubject(String studentId) {

        String subjectName = inputSubjectNameForUpdate();
        if (subjectName.trim().equals("")) {
            System.out.println("Keep old value");
            mySubject.get(studentId).getSubJectName();
            System.out.println(mySubject.get(studentId).getSubJectName());
        }
        int credit = inputCreditForUpdate(studentId);

        Subject subject = new Subject(studentId, subjectName, credit);
        mySubject.put(studentId, subject);
        
        System.out.println("Update successfully!");
        System.out.println(mySubject);
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
            String continuousOrGoBack = sc.nextLine().toUpperCase();
            switch (continuousOrGoBack) {
                case "Y":
                    createSubject();
                    break;
                case "N":
                    System.out.println("THIS IS YOUR MENU");
                    checkTrueFlase = false;
                    break;
            }
        } while (checkTrueFlase);
    }

    public Map<String, Subject> sortSubjectName(Set<String> subjectIds) {
        Map<String, Subject> subjectIdMapWithSubject = new HashMap<>();

        for (String subjectId : subjectIds) {
            subjectIdMapWithSubject.put(subjectId, mySubject.get(subjectId));
        }
        SubjectNameComparator subjectNameComparator = new SubjectNameComparator(subjectIdMapWithSubject);
        TreeMap<String, Subject> sortedSubjectNameByTreeMap = new TreeMap<>(subjectNameComparator);
        sortedSubjectNameByTreeMap.putAll(subjectIdMapWithSubject);
        return sortedSubjectNameByTreeMap;
    }
}
