package classSrc;

public class PercentageCurve extends Curve{

	public PercentageCurve(Double amount) {
		super(amount);
	}
	
	public PercentageCurve(){ //no amount specified
		super(0.0);
	}
	
<<<<<<< HEAD
	public String getCurveString() {
		return "Percentage";
	}
/*
=======
>>>>>>> e6a37d14a4b5c320f4004247d324ce33cb8edd5e
	@Override
	public Double ConvertRawToCurved(Double rawCredit) {
		return rawCredit * (1.0 + amount);
	}
}