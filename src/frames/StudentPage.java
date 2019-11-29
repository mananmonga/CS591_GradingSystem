package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StudentPage extends JPanel implements ActionListener
{	
	JLabel sId = new JLabel("Student ID:");
	JLabel sName = new JLabel("Student Name:");
	JButton add = new JButton("   Add   ");
	JButton delete = new JButton(" Delete ");
	JButton confirm = new JButton("              Confirm              ");
	JTextField sIdText = new JTextField("",10);
	JTextField sNameText = new JTextField("",10);
	JLabel sData = new JLabel("Student Data:");
	JButton sLoad = new JButton(" Load... ");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(3,1,10,5));
	
	public StudentPage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Student Setting", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize mainPanel
		JPanel loadPanel = new JPanel( new FlowLayout(FlowLayout.LEFT));
		loadPanel.add(sData);
		loadPanel.add(sLoad);
		mainPanel.add(loadPanel,BorderLayout.NORTH);
		mainPanel.add(listPanel,BorderLayout.CENTER);
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(sId);
		textPanel.add(sIdText);
		textPanel.add(new JLabel("    "));
		textPanel.add(sName);
		textPanel.add(sNameText);
		textPanel.add(add);
		textPanel.add(delete);
		inputPanel.add(textPanel);
		
		inputPanel.add(new JLabel("    "));
		inputPanel.add(confirm);
		
		//arrange listeners
		add.addActionListener(this);
		delete.addActionListener(this);
		confirm.addActionListener(this);
		sLoad.addActionListener(this);
		this.setVisible(true);
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
