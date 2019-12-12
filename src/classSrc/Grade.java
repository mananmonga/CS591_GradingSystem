package classSrc;

public class Grade {
	
	private String ID;
	private Double credit;
	private Assignment assignment;
	private String comments = "";
	
	public Grade(Assignment a){  //cannot have a completely empty constructor for Grade instances; every grade refers to an assignment
		this.ID = UUIDGenerator.getUUID();
		this.credit = 0.0;
		this.assignment = a;
	}
	
	public Grade(Assignment a, Double credit_, String comments_){
		this.ID = UUIDGenerator.getUUID();
		this.assignment = a;
		this.credit = credit_;
		this.comments = comments_;
	}
	
	public void setComment(String com) {
		this.comments = com;
	}
	
	public String getComment() {
		return this.comments;
	}
	
	public void setCredit(Double cre) {
		this.credit = cre;
	}
	
	public Double getCredit() {
		return this.credit;
	}
	
	public Assignment getAssignment() {
		return this.assignment;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null){
            return false;
        }

        if(obj instanceof Assignment){
        	Assignment other = (Assignment) obj;
            return this.getAssignment().getID().equals(other.getID());
        }

        return false;
    }
	
	/*
	//converts a student's grade on this assignment from raw score to percentage score, taking the curve into account if specified
	public Double CalculatePercentageScore(boolean curved) {
		if(assignment.hasCurve() && curved) { //check if the assignment is actually curved first
			return assignment.curve.calculateCurvedScore(credit, assignment.totalCredit);
		}
		else {
			return (credit / assignment.totalCredit);
		}
	}
	
	//the string representation of this grade in the UI, depends on which type of scoring method the assignment uses (deducted, absolute, etc)
	public String toString() {
		return assignment.GetScoreDisplay(credit);
	}*/
}
