package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.*;



public class AssignmentPage extends JPanel implements ActionListener
{	
	
	ArrayList<Assignment> UnconfirmedAssignments; //a list of assignments the teacher is currently entering in. When the teacher wants to confirm, this list needs to be validated before it is added to Course.Assignments
	
	JLabel aType = new JLabel("Assignment Type:");
	JLabel aDesc = new JLabel("Description:");
	JLabel aCredit = new JLabel("Full Credits:");
	JLabel aWeight = new JLabel("Weight(%):");
	
	JComboBox<String> aTypeBox = new JComboBox<>(new String[] {"Deducted Score", "Absolute Score"});
	JTextField aDescText = new JTextField("",10);
	JTextField aCreditText = new JTextField("",10);
	JTextField aWeightText = new JTextField("",10);
	
	JButton add = new JButton("      Add      ");
	JButton delete = new JButton("     Delete    ");
	JButton save = new JButton("Save Template"); 
	JButton confirm = new JButton("              Confirm              ");

	JLabel tData = new JLabel("Template:");
	JButton tLoad = new JButton(" Load... ");
	
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(4,1,10,5));
	
	public AssignmentPage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Assignment Setting", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize mainPanel
		JPanel loadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loadPanel.add(tData);
		loadPanel.add(tLoad);
		mainPanel.add(loadPanel,BorderLayout.NORTH);
		mainPanel.add(listPanel,BorderLayout.CENTER);
		
		//organize inputPanel
		JPanel textPanel = new JPanel(new GridLayout(1,4,10,5));
		JPanel tPanel1 = new JPanel();
		tPanel1.add(aType);
		tPanel1.add(aTypeBox);
		
		JPanel tPanel2 = new JPanel();
		tPanel2.add(aDesc);
		tPanel2.add(aDescText);
		
		JPanel tPanel3 = new JPanel();
		tPanel3.add(aCredit);
		tPanel3.add(aCreditText);
		
		JPanel tPanel4 = new JPanel();
		tPanel4.add(aWeight);
		tPanel4.add(aWeightText);
		
		textPanel.add(tPanel1);
		textPanel.add(tPanel2);
		textPanel.add(tPanel3);
		textPanel.add(tPanel4);
		inputPanel.add(textPanel);
		
		FlowLayout f= new FlowLayout();
		f.setHgap(80);
		JPanel btnPanel = new JPanel(f);
		btnPanel.add(add);
		btnPanel.add(delete);
		btnPanel.add(save);
		inputPanel.add(btnPanel);
		
		inputPanel.add(new JLabel("    "));
		inputPanel.add(confirm);
		
		//arrange listeners
		add.addActionListener(this);
		delete.addActionListener(this);
		confirm.addActionListener(this);
		save.addActionListener(this);
		tLoad.addActionListener(this);
		aTypeBox.addActionListener(this);
		this.setVisible(true);
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == add) { //add a new assignment to unconfirmed assignments
			
			Assignment unconfirmedNewAssignment;
			Double newAssignmentCredit;
			Double newAssignmentWeight;
			
			try {
				newAssignmentCredit = Double.parseDouble(aCreditText.getText());
				newAssignmentWeight = Double.parseDouble(aWeightText.getText());
				
				switch((String)aTypeBox.getSelectedItem()) {
					case "Deducted Score":
						unconfirmedNewAssignment = new DeductedScoreAssignment("", "", "", aDescText.getText(), newAssignmentCredit, newAssignmentWeight, null); //no input for curves for now
						break;
					case "Absolute Score":
						unconfirmedNewAssignment = new AbsoluteScoreAssignment("", "", "", aDescText.getText(), newAssignmentCredit, newAssignmentWeight, null);
						break;
					default:
						unconfirmedNewAssignment = new AbsoluteScoreAssignment();
						break;
				}
				
				UnconfirmedAssignments.add(unconfirmedNewAssignment);
			}
			catch(Exception ex) {
				//TODO: NEED TO DISPLAY A MESSAGE FOR PARSING ERROR
			}
		
			
		}
		
		if(e.getSource() == confirm) { //teacher is trying to confirm a new assignment configuration for the course
			if(ValidateAssignmentWeights()) {
				//TODO: ADD UNCONFIRMED ASSIGNMENTS TO COURSE.ASSIGNMENTS
				//TODO: SAVE UPDATED COURSE ASSIGNMENT STRUCTURE TO DATABASE
				//TODO: SHOW SUCCESS MESSAGE IN UI
				//TODO: ADD NEW ROW IN ASSIGNMENT TABLE FOR THIS NEWLY CONFIRMED ASSIGNMENT
			}
			else {
				//TODO: DISPLAY MESSAGE TELLING TEACHER TO MAKE THE WEIGHTS ADD TO 100
			}
			
		}
		
		if(e.getSource() == save) { //teacher is trying to save this assignment template
			if(ValidateAssignmentWeights()) {
				CourseTemplate newCourseTemplate = new CourseTemplate((Assignment[]) UnconfirmedAssignments.toArray());
				//TODO: ADD NEW COURSE TEMPLATE TO TEACHER.COURSETEMPLATES
				//TODO: SAVE NEW COURSE TEMPLATE TO DATABASE
			}
			else {
				//TODO: DISPLAY MESSAGE TELLING TEACHER TO MAKE THE WEIGHTS ADD TO 100
			}
		}
		
		if(e.getSource() == tLoad) { //teacher is trying to load a previous assignment structure template
			//TODO: CREATE A MENU ALLOWING TEACHER TO CHOOSE WHICH COURSE TEMPLATE THEY WANT FROM TEACHER.COURSETEMPLATES
			//TODO: UnconfirmedAssignments = loadedCourseTemplate.assignments
			//TODO: SHOW SUCCESS MESSAGE FOR TEMPLATE IMPORT
			//TODO: POPULATE TABLE FOR NEW ASSIGNMENT STRUCTURE
			
		}
		
	}
	
	//checks that unconfirmed assignment weights add to 100
	private boolean ValidateAssignmentWeights() {
		Double totalWeight = 0.0;
		for(Assignment unconfirmed : UnconfirmedAssignments) {
			totalWeight += unconfirmed.weight;
		}
		return totalWeight == 100.0;
	}
}
