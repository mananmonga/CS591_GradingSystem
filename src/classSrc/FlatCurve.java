package classSrc;

public class FlatCurve extends Curve{

	FlatCurve(Double amount_) {
		super(amount_);
	}
	
	FlatCurve(){ //no amount specified
		super(0.0);
	}

	@Override
	public Double calculateCurvedScore(Double pointsEarned, Double totalPoints) {
		return (pointsEarned + amount) / totalPoints;
	}

}
