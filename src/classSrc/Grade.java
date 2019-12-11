package classSrc;

public class Grade {
	private Double credit;
	private Assignment assignment;
	private String comments = "";
	
	public Grade(Assignment a){  //cannot have a completely empty constructor for Grade instances; every grade refers to an assignment
		this.credit = 0.0;
		this.assignment = a;
	}
	
	public Grade(Assignment a, Double credit_, String comments_){
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
