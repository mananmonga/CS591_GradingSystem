package classSrc;

public class PercentageCurve extends Curve{

	PercentageCurve(Double amount) {
		super(amount);
	}
	
	PercentageCurve(){ //no amount specified
		super(0.0);
	}

	@Override
	public Double calculateCurvedPart(Double plainScore) {
		return this.getAmount()*0.01*plainScore;
	};
}