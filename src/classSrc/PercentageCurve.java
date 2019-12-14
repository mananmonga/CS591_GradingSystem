package classSrc;

public class PercentageCurve extends Curve{

	public PercentageCurve(Double amount) {
		super(amount);
	}
	
	public PercentageCurve(){ //no amount specified
		super(0.0);
	}
	
	public String getCurveString() {
		return "Percentage";
	}
/*
	@Override
	public Double calculateCurvedPart(Double plainScore) {
		return this.getAmount()*0.01*plainScore;
	};*/
}