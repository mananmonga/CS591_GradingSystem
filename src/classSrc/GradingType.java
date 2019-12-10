package classSrc;

public enum GradingType {
	Absolute("Absolute Grading"), 
	Deduction("Deduction Grading");
	
	private final String TypeText;
	GradingType(String TypeText) {
        this.TypeText = TypeText;
    }

    @Override
    public String toString() {
        return TypeText;
    }
}

