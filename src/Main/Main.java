package Main;

import grade.GradeService;
import report.ShowGradeService;
import student.StudentService;
import subject.SubjectService;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        GradeService gradeService = new GradeService();
        ShowGradeService showGradeService = new ShowGradeService();

        int choice = 1;
        while (choice >= 1 && choice <= 8) {
            showMenu();
            do {
                try {
                    Scanner sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 8) {
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Your choice is not a number or out of range 1 to 8!");
                    System.out.print("Please choose a number from 1 to 8: ");
                }
            } while (true);

            switch (choice) {
                case 1 :
                    studentService.createStudent();
                    studentService.showContinueCreating();
                    break;
                case 2 :
                    studentService.updatingStudent();
                    break;
                case 3 :
                    subjectService.createSubject();
                    subjectService.showContinuteCreating();
                    break;
                case 4 :
                    subjectService.updatingSubject();
                    break;
                case 5 :
                    gradeService.enterStudentGrade();      
                    break;
                case 6 :
                    showGradeService.studentGradeReport();
                    break;
                case 7 :
                    showGradeService.subjectGradeReport();
                    break;
                case 8 :
//                    System.out.println("Completed program, see u again!");
//                    return;
                    studentService.showStudent();
            }
        }
    }

    public static void showMenu() {
        System.out.println("-------------------MENU------------------");
        System.out.println("1.  Add a new student");
        System.out.println("2.  Update student ");
        System.out.println("3. Add a subject");
        System.out.println("4. Update a subject");
        System.out.println("5. Enter a grade");
        System.out.println("6. Student grade report");
        System.out.println("7. Subject grade report");
        System.out.println("8. Others - Quit");
        System.out.print("PLEASE ENTER YOUR CHOICE: ");
    }

}
