package classSrc;
import java.math.BigDecimal;

public class Assignment
{
	private String ID;
	private GradingType gradingType;
	private String name;
	private String description = "";
	private Double fullCredit;
	private Double weight;
	private Curve curve = new FlatCurve();

	public Assignment(String type_, String name_, String description_, Double fullCredit_, Double weight_){
		this.ID = UUIDGenerator.getUUID();
		this.setType(type_);
		this.name = name_;
		this.description = description_;
		this.fullCredit = fullCredit_;
		BigDecimal bg = new BigDecimal(weight_);
		this.weight = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Assignment(String type_, String name_, Double fullCredit_, Double weight_, String curvetype, Double curvevalue){
		this.ID = UUIDGenerator.getUUID();
		this.setType(type_);
		this.name = name_;
		this.fullCredit = fullCredit_;
		BigDecimal bg = new BigDecimal(weight_);
		this.weight = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Curve c = null;
		if(curvetype.equals("Flat")) {
    		c = new FlatCurve(curvevalue);
    	}else if(curvetype.equals("Percentage")) {
    		c = new PercentageCurve(curvevalue);
    	}
    	this.curve = c;
	}
	
	public Assignment(String ID, String type_, String name_, String description_, Double fullCredit_, Double weight_, String curvetype, Double curvevalue){
		this.ID = ID;
		this.setType(type_);
		this.name = name_;
		this.description = description_;
		this.fullCredit = fullCredit_;
		this.weight = weight_;
		Curve c = new FlatCurve(curvevalue);
		if(curvetype.equals("Flat")) {
    		c = new FlatCurve(curvevalue);
    	}else if(curvetype.equals("Percentage")) {
    		c = new PercentageCurve(curvevalue);
    	}
    	this.curve = c;
	}
	
	public Assignment(Assignment ass){
		this.ID = ass.getID();
		this.setType(ass.getType());
		this.name = ass.getName();
		this.description = ass.getDescription();
		this.fullCredit = ass.getFullCredit();
		this.weight = ass.getWeight();
		this.curve = ass.getCurve();
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getType() {
		return gradingType.toString();
	}

	public void setType(String type) {
		if(type.equals(GradingType.Absolute.toString()))
			this.gradingType = GradingType.Absolute;
		else if(type.equals(GradingType.Deduction.toString()))
			this.gradingType = GradingType.Deduction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getFullCredit() {
		BigDecimal bg = new BigDecimal(fullCredit);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public void setFullCredit(Double fullCredit) {
		this.fullCredit = fullCredit;
	}

	public Double getWeight() {
		BigDecimal bg = new BigDecimal(weight);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Curve getCurve() {
		return curve;
	}

	public void setCurve(Curve curve) {
		this.curve = curve;
	}
	
	public boolean hasCurve() {
		return curve != null;
	}
}
