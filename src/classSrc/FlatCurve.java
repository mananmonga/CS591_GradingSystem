package classSrc;

public class FlatCurve extends Curve{

	public FlatCurve(Double amount) {
		super(amount);
	}
	
	public FlatCurve(){ //no amount specified
		super(0.0);
	}
	
	public String getCurveString() {
		return "Flat";
	}

	@Override
	public Double ConvertRawToCurved(Double rawCredit) {
		return rawCredit + amount;
	}

}
