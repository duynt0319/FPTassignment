package assesement.pkg01;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        SubjectService subjectService = new SubjectService();
        GradeService gradeService = new GradeService();
        ShowGradeService soutGrade = new ShowGradeService();

        int choice = 1;
        while (choice >= 1 && choice <= 9) {
            showMenu();
            do {
                try {
                    Scanner sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 9) {
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Your choice is not a number or out of range 1 to 7!");
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
                    gradeService.test();
                    break;

                case 6 :
                    soutGrade.studentGradeReport();
                    break;

                case 7 :
                    soutGrade.subjectGradeReport();
                    break;

                case 8 :
                    System.out.println("Completed program, see u again!");
                    break;

                case 9 :
                    studentService.showStudent();
                    subjectService.showSubject();
                    gradeService.showGrade();

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
