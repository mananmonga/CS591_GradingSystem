package classSrc;

import java.util.ArrayList;

public class EnrolledStudent extends Person {
    ArrayList<Grade> grades = new ArrayList<Grade>();;
    Double bonusPoints = 0.0;

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

    public EnrolledStudent(String name, String ID) {
        super(name, ID);
    }
    
    public EnrolledStudent(EnrolledStudent s) {
        super(s.getName(),s.getID());
        this.bonusPoints = s.getBonus();
        this.grades = s.getGrades();
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
  
        if (!(other instanceof EnrolledStudent)) { 
            return false; 
        } 
          
        EnrolledStudent otherStudent = (EnrolledStudent)other; 
          
        return this.ID == otherStudent.ID;
    }
}
