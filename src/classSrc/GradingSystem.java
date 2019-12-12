package classSrc;

import java.util.ArrayList;

public class GradingSystem
{	
	private ArrayList<Assignment> assignmentTemplate = new ArrayList<Assignment>() {{
		add(new Assignment("Absolute Grading", "HW1", "",Double.valueOf(100.00), Double.valueOf(0.3)));
		add(new Assignment("Deduction Grading", "Quiz1", "",Double.valueOf(100.00), Double.valueOf(0.7)));
	}};
	
	private static class GradingSystemHolder {
		private static GradingSystem instance = new GradingSystem();
	}
	
	public static GradingSystem getInstance() {    //use singleton module to get the GradingSystem instance
		return GradingSystemHolder.instance;
	}
	
	private GradingSystem() {
		//initBank();  //initialize bank with fixed data
	}
	
	public boolean LoginCheck(String Uid, String password) {
        return true;
    }
	
	public ArrayList<Assignment> getAssignmentTemplate() {
		return this.assignmentTemplate;
	}
	
	public void setAssignmentTemplate(ArrayList<Assignment> temp) {
		this.assignmentTemplate = temp;
	}
}
