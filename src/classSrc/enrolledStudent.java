package classSrc;

import java.util.ArrayList;

public class enrolledStudent extends Person {
    ArrayList<Grade> grades;
    Double bonusPoints;

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public Double getBonus() {
        return bonusPoints;
    }

    public void setBonus(Double bonus) {
        this.bonusPoints = bonus;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getID() {
    	return this.ID;
    }

    public enrolledStudent(String name, String ID) {
        super(name, ID);
        bonusPoints = 0.0;
        grades = new ArrayList<Grade>();
    }
    
    public void AddGrade(Grade g) {
    	grades.add(g);
    	//TODO: ADD NEW GRADE TO DATABASE
    }
    
    //must implement equals method so that an enrolled student can be removed from Course.EnrolledStudents
    @Override
    public boolean equals(Object other) {
    	  
        if (other == this) { 
            return true; 
        } 
  
        if (!(other instanceof enrolledStudent)) { 
            return false; 
        } 
          
        enrolledStudent otherStudent = (enrolledStudent)other; 
          
        return this.ID == otherStudent.ID;
    }
}
