/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grade;
public class Grade {
    private double labs;
    private double progressTest;
    private double finalExam;

    public Grade(double labs, double progressTest, double finalExam) {
        this.labs = labs;
        this.progressTest = progressTest;
        this.finalExam = finalExam;
    }

    public double getLabs() {
        return labs;
    }

    public void setLabs(double labs) {
        this.labs = labs;
    }

    public double getProgressTest() {
        return progressTest;
    }

    public void setProgressTest(double progressTest) {
        this.progressTest = progressTest;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }
    
    public double getAverage(){
        return this.finalExam * 0.4 + this.labs * 0.3 + this.progressTest * 0.3;
    }

    @Override
    public String toString() {
        return "Grade(" + "labs: " + labs + " progressTest: " + progressTest + " finalExam: " + finalExam + ')'+"\n";
    }
    
    
}
