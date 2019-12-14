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
	
	public Double ConvertRawToCurved(Double rawCredit) {
		return rawCredit * (1.0 + amount);
	}
}