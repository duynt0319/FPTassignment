/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assesement.pkg01.subject;
public class Subject {
    private String subjectId;
    private String subJectName;
    private int credit;

    public Subject(String subjectId, String subJectName, int credit) {
        this.subjectId = subjectId;
        this.subJectName = subJectName;
        this.credit = credit;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubJectName() {
        return subJectName;
    }

    public void setSubJectName(String subJectName) {
        this.subJectName = subJectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }


    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subJectName=" + subJectName + ", credit=" + credit + '}';
    }
    
    
}
