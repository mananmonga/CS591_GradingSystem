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
	
	//different implementation depending on the curved type (flat, percentage, etc)
	abstract public Double ConvertRawToCurved(Double rawCredit);
	
}
