package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


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
	
	public CourseListFrame()
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
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			
		}else if(e.getSource()==delete) {
			
		}else if(e.getSource()==view) {
			new CourseFrame();
		}
	}
}
