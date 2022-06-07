/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import grade.Grade;
import grade.GradeService;
import student.StudentService;
import subject.Subject;
import subject.SubjectService;

import java.util.Map;
import java.util.Scanner;

public class ShowGradeService {

    StudentService studentService = new StudentService();
    SubjectService subjectService = new SubjectService();
    GradeService gradeService = new GradeService();
    Scanner sc = new Scanner(System.in);

    private String inputStudentId() {
        System.out.print("Please input student ID: ");
        String inputStudentId = sc.nextLine();
        return inputStudentId;
    }

    private String inputSubjectId() {
        System.out.print("Please input subject ID: ");
        String inputSubjectId = sc.nextLine();
        return inputSubjectId;
    }

    private boolean checkIsSubjectExist(String subject) {
        return subjectService.isExistSubjectInHashMap(subject);
    }

    private boolean checkIsStudentExist(String studentId) {
        return studentService.isExistStudentInHashMap(studentId);
    }

    //fun6
    public void studentGradeReport() {
        boolean flag;
        do {
            String studentIdInput = inputStudentId().toUpperCase();
            if (!checkIsStudentExist(studentIdInput)) {
                System.out.println("Student does not exist!");
                return;
            }
            if (!gradeService.checkIsGradeExist(studentIdInput)) {
                System.out.println(studentIdInput + " have not taken any subject yet! Please try again!");
                return;
            }
            Map<String, Grade> subjectsAndGradeByStudentID = gradeService.getSubjectsNameAndGradeByStudentID(studentIdInput);
            Map<String, Subject> sortBySubjectName = subjectService
                    .sortSubjectName(subjectsAndGradeByStudentID.keySet());
            
            System.out.println("-StudentID " + studentIdInput);
            System.out.println("-Student name: " + studentService.getFNameAndLNameOfStudentById(studentIdInput));
            System.out.println("List of subject sort by Subject Name:");
            System.out.println("| ++No++ | +++++++Subject name+++++++ | ++Average mark++ | ++Status++ |");

            int count = 1;
            for (String subjectId : sortBySubjectName.keySet()) {
                System.out.format("|%5d   | %20s       | %f         |  %s     |\n",
                        count,
                        sortBySubjectName.get(subjectId).getSubJectName(),
                        gradeService.getAverageBySubjectId(studentIdInput, subjectId),
                        gradeService.getStatus(studentIdInput, subjectId)
                );
                count++;
            }
            System.out.println();
            flag = false;

        } while (flag);
    }

    //fun7
    public void subjectGradeReport() {
        boolean flag;
        do {

            String subjectIdInput = inputSubjectId().toUpperCase();
            if (!checkIsSubjectExist(subjectIdInput)) {
                System.out.println("Subject does not exist!");
                return;
            }
            Map<String, Grade> studentIdAndStudentNameBySubjectID = gradeService.getStudentIdAndStudentNameBySubjectID(subjectIdInput);
            Map<String, String> sortByStudentName = studentService.sortByStudentName(studentIdAndStudentNameBySubjectID.keySet());
            
            if (studentIdAndStudentNameBySubjectID.isEmpty()) {
                System.out.println("No students have taken " + subjectIdInput + " yet! please try again!");
                return;
            }

            System.out.println("-SubjectID: " + subjectIdInput);
            System.out.println("-Subject name: " + subjectService.getSubjectNameBySubjectId(subjectIdInput));
            System.out.println("|++Student ID++|++++++Student name++++++|++Average mark++|++Status++|");
            System.out.println("List of Student sort by Student Name:");

            for (String studentId : sortByStudentName.keySet()) {
                System.out.format("|%14s|%24s|%9.2f       |%10s|\n",
                        studentId,
                        studentService.getFNameAndLNameOfStudentById(studentId),
                        gradeService.getAverageBySubjectId(studentId, subjectIdInput),
                        gradeService.getStatus(studentId, subjectIdInput)
                );
            }
            System.out.println();
            flag = false;
        } while (flag);
    }
}
