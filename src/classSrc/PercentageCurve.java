package classSrc;

public class PercentageCurve extends Curve{

	PercentageCurve(Double amount_) {
		super(amount_);
	}
	
	PercentageCurve(){ //no amount specified
		super(1.0);
	}

	@Override
	public Double calculateCurvedScore(Double pointsEarned, Double totalPoints) {
		return (pointsEarned/totalPoints) * amount;
	}

}
