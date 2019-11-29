package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CourseStatsPage extends JPanel 
{	
	JPanel mainPanel = new JPanel(new GridLayout(2,1,10,5));
    JPanel listPanel1 = new JPanel();
    JPanel listPanel2 = new JPanel();
    
	
	public CourseStatsPage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Course Statistics", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		
		mainPanel.add(listPanel1);
		mainPanel.add(listPanel2);
	}
	
}
