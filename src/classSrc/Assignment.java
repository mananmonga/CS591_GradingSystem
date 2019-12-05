package classSrc;

abstract public class Assignment {
	public String type;
	public String name;
	public String description;
	public Double totalCredit;
	public Double weight;
	public Curve curve;
	
	
	Assignment(){
		type = "Assignment";
		name = "Assignment";
		description = "No description available.";
		totalCredit = 100.0;
		weight = 0.1;
		
		curve = null;
	}
	
	Assignment(String type_, String name_, String description_, Double totalCredit_, Double weight_, Curve curve_){
		type = type_;
		name = name_;
		description = description_;
		totalCredit = totalCredit_;
		weight = weight_;
		curve = curve_;
	}
	
	public boolean hasCurve() {
		return curve != null;
	}
	
	//converts a student's grade on this assignment from raw score to percentage score, taking the curve into account if specified
	public Double CalculatePercentageScore(Double score, boolean curved) {
		if(hasCurve() && curved) { //check if the assignment is actually curved first
			return curve.calculateCurvedScore(score, totalCredit);
		}
		else {
			return (score / totalCredit);
		}
	}
	
	//returns a string representation of a student's grade on the assignment. Called from instances of Grade.java
	//string representation varies depending on the type of assignment (deducted score vs absolute score)
	abstract public String GetScoreDisplay(Double score);

}
