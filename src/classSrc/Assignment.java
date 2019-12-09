package classSrc;

abstract public class Assignment {
	public String ID;
	public String type;
	public String name;
	public String description;
	public Double totalCredit;
	public Double weight;
	public Curve curve;
	public AssignmentScoringType scoringType;
	
	
	Assignment(){
		type = "Assignment";
		name = "Assignment";
		description = "No description available.";
		totalCredit = 100.0;
		weight = 0.1;
		ID = "999999999";
		curve = null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Curve getCurve() {
		return curve;
	}

	public void setCurve(Curve curve) {
		this.curve = curve;
	}

	Assignment(String ID_, String type_, String name_, String description_, Double totalCredit_, Double weight_, Curve curve_){
		type = type_;
		name = name_;
		description = description_;
		totalCredit = totalCredit_;
		weight = weight_;
		curve = curve_;
		ID = ID_;
	}
	
	public boolean hasCurve() {
		return curve != null;
	}
	
	//must implement equals method so that an assignment can be removed from Course.Assignments
    @Override
    public boolean equals(Object other) {
    	  
        if (other == this) { 
            return true; 
        } 
  
        if (!(other instanceof Assignment)) { 
            return false; 
        } 
          
        Assignment otherAssignment = (Assignment)other; 
          
        return this.ID == otherAssignment.ID;
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
