package classSrc;

public abstract class Curve {
	
	protected Double amount;
	
	public Curve(Double amount){
		this.amount = amount;
	}
	
	public Double getAmount() {
		return this.amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
<<<<<<< HEAD
	abstract public String getCurveString();
	//abstract public Double calculateCurvedPart(Double plainScore);
=======
	
	//different implementation depending on the curved type (flat, percentage, etc)
	abstract public Double ConvertRawToCurved(Double rawCredit);
	
>>>>>>> e6a37d14a4b5c320f4004247d324ce33cb8edd5e
}
