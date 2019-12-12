package classSrc;

import java.util.ArrayList;

public class EnrolledStudent extends Student {
    private ArrayList<Grade> grades = new ArrayList<Grade>();;
    private Double bonusPoints = 0.0;
    private String comments ="";
    
    public EnrolledStudent(String name, ArrayList<Grade> grades_, Double bonusPoints_, String comments_) {
    	super(name, UUIDGenerator.getUUID());
    	grades = grades_;
    	bonusPoints = bonusPoints_;
    	comments = comments_;
    }
    
    public EnrolledStudent() {
    	super("No Name", UUIDGenerator.getUUID());
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
        //TODO: UPDATE ALL OF THIS STUDENTS' GRADES IN DATABASE (just overwrite any previous tuples if the primary key matches
    }

    public Double getBonus() {
        return bonusPoints;
    }

    public void setBonus(Double bonus) {
        this.bonusPoints = bonus;
    }
    
    public void setComment(String com) {
		this.comments = com;
	}
	
	public String getComment() {
		return this.comments;
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
