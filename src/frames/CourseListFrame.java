package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.*;

public class CourseListFrame extends JFrame implements ActionListener
{	
	JLabel cId = new JLabel("Course ID:");
	JLabel cName = new JLabel("Course Name:");
	JButton add = new JButton("   Add   ");
	JButton delete = new JButton(" Delete ");
	JButton view = new JButton("              View Course              ");
	JTextField cIdText = new JTextField("",10);
	JTextField cNameText = new JTextField("",10);
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(3,1,10,5));
    
    ArrayList<Course> courses;
	
	public CourseListFrame(ArrayList<Course> courses_)
	{ 	//organize frame
		this.setTitle("Courses");
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Course List",SwingConstants.CENTER),BorderLayout.NORTH);
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize listPanel
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(cId);
		textPanel.add(cIdText);
		textPanel.add(new JLabel("    "));
		textPanel.add(cName);
		textPanel.add(cNameText);
		textPanel.add(add);
		textPanel.add(delete);
		inputPanel.add(textPanel);
		
		inputPanel.add(new JLabel("    "));
		inputPanel.add(view);
		
		//arrange listeners
		add.addActionListener(this);
		delete.addActionListener(this);
		view.addActionListener(this);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(150, 80, 600, 500); 
		
		courses = courses_;
		//TODO: GENERATE LIST OF COURSES IN UI FROM THE COURSE ARRAYLIST
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			String newCourseID = cIdText.getText();
			String newCourseName = cNameText.getText();
			String newCourseDesc = ""; //TODO: ADD UI INPUT FOR NEW COURSE DESCRIPTION
			Course newCourse = new Course(newCourseName, newCourseID, newCourseDesc);
			courses.add(newCourse);
			//TODO: SAVE NEW COURSE IN DATABASE
			//TODO: ADD NEW COURSE TO THIS FRAME'S UI
			
		}else if(e.getSource()==delete) {
			//TODO: FIND WHICH COURSE IS SELECTED FROM THE UI LIST AND DELETE IT FROM COURSES LIST IN THIS FRAME INSTANCE
			//TODO: REMOVE DELETED COURSE FROM UI
			//TODO: REMOVE THE DELETED COURSE FROM THE DATABASE
			
		}else if(e.getSource()==view) {
			//TODO: FIND WHICH COURSE IS SELECTED FROM THE UI LIST AND PASS IT INTO COURSE FRAME AS A PARAMETER
			new CourseFrame(new Course());
		}
	}
}
