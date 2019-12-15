package classSrc;

import java.util.ArrayList;

public class EnrolledStudent extends Student {
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private Double bonusPoints = 0.0;
    private String comments ="";
    private String section = "1";
    
    public EnrolledStudent(String name, String ID, ArrayList<Grade> grades_, Double bonusPoints_, String comments_, String section_) {
    	super(name, ID);
    	grades = grades_;
    	bonusPoints = bonusPoints_;
    	comments = comments_;
    	section = section_;
    }
    
    public EnrolledStudent(String name, String ID) {
    	super(name, ID);
    }
    
    public EnrolledStudent(String name, String ID, String section_) {
    	super(name, ID);
    	section = section_;
    }
    
    public EnrolledStudent(EnrolledStudent s) {
        super(s.getName(),s.getID());
        this.bonusPoints = s.getBonus();
        this.grades = s.getGrades();
        this.section = s.getSection();
    }
    
    public String getSection() {
    	return this.section;
    }
    
    public void setSection(String section) {
    	this.section = section;
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
}
