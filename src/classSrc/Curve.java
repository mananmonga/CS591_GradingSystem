package classSrc;

public abstract class Curve {
	
	private Double amount;
	
	public Curve(Double amount){
		this.amount = amount;
	}
	
	public Double getAmount() {
		return this.amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	abstract public Double calculateCurvedPart(Double plainScore);
}
