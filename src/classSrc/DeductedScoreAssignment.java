package classSrc;

public class DeductedScoreAssignment extends Assignment{

	public DeductedScoreAssignment(){
		super();
	}
	
	public DeductedScoreAssignment(String type_, String name_, String description_, Double totalCredit_, Double weight_, Curve curve_){
		super(type_, name_, description_, totalCredit_, weight_, curve_);
	}
	
	@Override
	public String GetScoreDisplay(Double score) {
		return "-" + Double.toString(totalCredit - score);
	}

}