package classSrc;

public class DeductedScoreAssignment extends Assignment{

	public DeductedScoreAssignment(){
		super();
	}
	
	public DeductedScoreAssignment(String ID_, String type_, String name_, String description_, Double totalCredit_, Double weight_, Curve curve_){
		super(ID_, type_, name_, description_, totalCredit_, weight_, curve_);
		this.scoringType = AssignmentScoringType.Deducted;
	}
	
	@Override
	public String GetScoreDisplay(Double score) {
		return "-" + Double.toString(totalCredit - score);
	}

}
