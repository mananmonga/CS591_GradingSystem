package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.*;

public class CourseStatsPage extends JPanel 
{	
	JPanel mainPanel = new JPanel(new GridLayout(2,1,10,5));
    JPanel listPanel1 = new JPanel();
    JPanel listPanel2 = new JPanel();
    Course course;
	
	public CourseStatsPage(Course course_)
	{ 	
		this.course = course_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Course Statistics", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		
		mainPanel.add(listPanel1);
		mainPanel.add(listPanel2);
	}
	
	public void refreshCourseStatsList() {
	}
	
}
