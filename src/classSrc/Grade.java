package classSrc;

public class Grade {
	
	private String ID;
	private Double credit; //will be a negative number if this is a deducted assignment, positive if it is absolute
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
	
	public Grade(String id, Assignment a, Double credit_, String comments_){
		this.ID = id;
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
	
	//calculates the student's percentage grade by taking into account their credit earned, the assignment's max credit, and the curve of the assignment (if any)
	public Double calculatePercentageScore(boolean curved) {
		Double adjustedCredit = curved && assignment.hasCurve() ? assignment.getCurve().ConvertRawToCurved(credit) : credit;
		Double percentageScore = adjustedCredit / assignment.getFullCredit();
		return percentageScore * 100.0;
	}
}
