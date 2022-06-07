/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grade;

import student.StudentService;
import subject.SubjectService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeService {

    private static Map<String, Map<String, Grade>> studentIdMapWithSubjectIdMapWithGrade = new HashMap<>();

    private Scanner sc = new Scanner(System.in);

    StudentService studentService = new StudentService();
    SubjectService subjectService = new SubjectService();

    public Map<String, Grade> getSubjectsNameAndGradeByStudentID(String studentID) {
        return studentIdMapWithSubjectIdMapWithGrade.get(studentID);
    }

    public boolean checkIsGradeExist(String subjectID) {
        return studentIdMapWithSubjectIdMapWithGrade.containsKey(subjectID);
    }

    public Map<String, Grade> getStudentIdAndStudentNameBySubjectID(String subjectID) {
        Map<String, Grade> map = new HashMap<>();
        for (String studentId : studentIdMapWithSubjectIdMapWithGrade.keySet()) {
            Map<String, Grade> subjectWithGrade = studentIdMapWithSubjectIdMapWithGrade.get(studentId);
            if (subjectWithGrade.containsKey(subjectID)) {
                Grade grade = subjectWithGrade.get(subjectID);
                map.put(studentId, grade);
            }
        }
        return map;
    }

    public Double getAverageBySubjectId(String studentId, String subjectId) {
        return studentIdMapWithSubjectIdMapWithGrade.get(studentId).get(subjectId).getAverage();
    }

    public String getStatus(String studentId, String subjectId) {
        if (getAverageBySubjectId(studentId, subjectId) > 4.0) {
            return "Pass!";
        }
        return "Not pass!";
    }


    private String inputStudentId() {

        System.out.print("Please input student ID: ");
        String inputStudentId = sc.nextLine();
        while (inputStudentId == null || inputStudentId.isEmpty()) {
            System.out.print("your input is wrong! Please try again!: ");
            inputStudentId = sc.nextLine();
        }
        return inputStudentId;
    }

    private String inputSubjectId() {

        System.out.print("Please input subject ID: ");
        String inputSubjectId = sc.nextLine();
        while (inputSubjectId == null || inputSubjectId.isEmpty()) {
            System.out.print("your input is wrong! Please try again!: ");
            inputSubjectId = sc.nextLine();
        }
        return inputSubjectId;
    }

    public boolean isExistStudentId(String studentId) {
        return studentIdMapWithSubjectIdMapWithGrade.containsKey(studentId);
    }


    public void enterStudentGrade() {

        String studentIdInput = inputStudentId().toUpperCase();
        boolean isStudentIdExist = studentService.isExistStudentInHashMap(studentIdInput);
        if (isStudentIdExist == false) {
            System.out.println("Student does not exist!!!!");
            return;
        }

        String subjectIdInput = inputSubjectId().toUpperCase();
        boolean isSubjectIdExist = subjectService.isExistSubjectInHashMap(subjectIdInput);
        if (isSubjectIdExist == false) {
            System.out.println("Subject does not exist!!!!");
            return;
        }

        if (checkInputIsExitOrNot(subjectIdInput, studentIdInput)) {
            overWriteGrade(studentIdInput, subjectIdInput);
            return;
        }

        enterNewGrade(studentIdInput, subjectIdInput);

    }
    private boolean isOverWritingGradeOrNot() {
        System.out.println("Your Grade is already EXIST! Do you want overwrite it or not?"
                + "(Y/N): ");
        String input = sc.nextLine().toUpperCase();
        return "Y".equals(input);
    }

    private double inputGradeOfProgessTests() {
        double progessTestsGrade;
        do {
            try {
                System.out.print("Please input Progess Tests Grade: ");
                progessTestsGrade = Double.parseDouble(sc.nextLine());
                if (progessTestsGrade >= 0 && progessTestsGrade <= 10) {
                    break;
                } else {
                    System.out.println("input number from 1-10!");
                }
            } catch (NumberFormatException e) {
                System.out.println("your input must be valid! Please input credit again!!");
            }
        } while (true);
        return progessTestsGrade;
    }

    private double inputGradeOfFinalExam() {
        double gradeOfFinalExam;
        do {
            try {
                System.out.print("Please input grade Of Final Exam: ");
                gradeOfFinalExam = Double.parseDouble(sc.nextLine());
                if (gradeOfFinalExam >= 0 && gradeOfFinalExam <= 10) {
                    break;
                } else {
                    System.out.println("input number from 1-10!");
                }
            } catch (NumberFormatException e) {
                System.out.println("your input must be valid! Please input credit again!!");
            }
        } while (true);
        return gradeOfFinalExam;
    }

    private double inputGradeOfLabs() {
        double gradeOfLabs;
        do {
            try {
                System.out.print("Please input grade Of Labs: ");
                gradeOfLabs = Double.parseDouble(sc.nextLine());
                if (gradeOfLabs >= 0 && gradeOfLabs <= 10) {
                    break;
                } else {
                    System.out.println("input number from 1-10!");
                }
            } catch (NumberFormatException e) {
                System.out.println("your input must be valid! Please input credit again!!");
            }
        } while (true);
        return gradeOfLabs;
    }

    private boolean isStudentIdExistInMyGrade(String studentId) {
        return studentIdMapWithSubjectIdMapWithGrade.containsKey(studentId);
    }

    private boolean checkInputIsExitOrNot(String subjectId, String studentId) {
        if (isStudentIdExistInMyGrade(studentId)) {
            Map<String, Grade> subjectIdMapWithGrade = studentIdMapWithSubjectIdMapWithGrade.get(studentId);
            return subjectIdMapWithGrade.containsKey(subjectId);
        }
        return false;
    }

    public void enterNewGrade(String studentId, String subjectId) {

        double labs = inputGradeOfLabs();
        double progressTest = inputGradeOfProgessTests();
        double finalExam = inputGradeOfFinalExam();
        Grade grade = new Grade(labs, progressTest, finalExam);

        Map<String, Grade> subjectIdMapWithGrade;

        if (!isStudentIdExistInMyGrade(studentId)) {
            subjectIdMapWithGrade = new HashMap<>();
        } else {
            subjectIdMapWithGrade = studentIdMapWithSubjectIdMapWithGrade.get(studentId);
        }

        subjectIdMapWithGrade.put(subjectId, grade);
        studentIdMapWithSubjectIdMapWithGrade.put(studentId, subjectIdMapWithGrade);
    }

    public void overWriteGrade(String studentId, String subjectId) {
        boolean choice = isOverWritingGradeOrNot();
        if (choice == true) {
            Map<String, Grade> subjectIdMapWithGrade = studentIdMapWithSubjectIdMapWithGrade.get(studentId);

            double labs = inputGradeOfLabs();
            double progressTest = inputGradeOfProgessTests();
            double finalExam = inputGradeOfFinalExam();
            Grade gradeOverWrite = new Grade(labs, progressTest, finalExam);

            subjectIdMapWithGrade.put(subjectId, gradeOverWrite);

            studentIdMapWithSubjectIdMapWithGrade.put(studentId, subjectIdMapWithGrade);

        }
    }

    public void removeStudent(String studentId) {
        studentIdMapWithSubjectIdMapWithGrade.remove(studentId);
    }

    public void removeSubject(String subjectId) {
        for (String studentId: studentIdMapWithSubjectIdMapWithGrade.keySet()) {
            studentIdMapWithSubjectIdMapWithGrade.get(studentId).remove(subjectId);
        }
    }
}
