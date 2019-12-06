package classSrc;

import java.util.ArrayList;

public class enrolledStudent  {
    Student studentDetails;
    ArrayList<Grade> grades;
    Double bonus;

    public Student getStudentDetails() {
        return studentDetails;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public enrolledStudent(Student studentDetails) {
        this.studentDetails = studentDetails;
    }
}
