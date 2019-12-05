package classSrc;

public class Grade {
	
	public Double credit;
	public Assignment assignment;
	public String comments;
	
	Grade(Assignment a){  //cannot have a completely empty constructor for Grade instances; every grade refers to an assignment
		credit = 0.0;
		assignment = a;
		comments = "No comments.";
	}
	
	Grade(Assignment a, Double credit_, String comments_){
		assignment = a;
		credit = credit_;
		comments = comments_;
	}
	
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
	}
	
}
