package classSrc;

public abstract class Curve {
	
	public Double amount;
	
	Curve(Double amount_){
		amount = amount_;
	}
	
	abstract public Double calculateCurvedScore(Double pointsEarned, Double totalPoints);
	

}
