package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StudentStatsPage extends JPanel implements ActionListener
{	

	JButton save = new JButton("Save");
	JButton search = new JButton("Search");
	JLabel name;
	JLabel id;	
	JLabel name1 = new JLabel("Name:");
	JLabel id1 = new JLabel("ID:");
	JTextField nameText = new JTextField("",10);
	JTextField idText = new JTextField("",10);
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(3,1,10,5));
	
	public StudentStatsPage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Student Statistics", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize listPanel
		JPanel showPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String studentName = "Name: " + "Sean";
		String studentID = "ID: " + "U1106";
		name = new JLabel(studentName);
		id = new JLabel(studentID);
		showPanel.add(name);
		showPanel.add(new JLabel("    "));
		showPanel.add(id);
		listPanel.add(showPanel,BorderLayout.NORTH);
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(name1);
		textPanel.add(nameText);
		textPanel.add(new JLabel("    "));
		textPanel.add(id1);
		textPanel.add(idText);
		textPanel.add(new JLabel("    "));
		textPanel.add(search);
		inputPanel.add(textPanel,BorderLayout.NORTH);
		inputPanel.add(new JLabel("    "));
		inputPanel.add(save,BorderLayout.SOUTH);
		
		//arrange listeners
		save.addActionListener(this);
		search.addActionListener(this);
		this.setVisible(true);
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
