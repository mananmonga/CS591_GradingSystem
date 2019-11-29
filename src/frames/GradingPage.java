package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GradingPage extends JPanel implements ActionListener
{	
	JButton view = new JButton("     View Student     ");
	JButton save = new JButton("      Save Grade      ");
	JButton search = new JButton("Search");
	JLabel name = new JLabel("Name:");
	JLabel id = new JLabel("ID:");
	JTextField nameText = new JTextField("",10);
	JTextField idText = new JTextField("",10);	
	JLabel tSection = new JLabel("Switch Section");
	JComboBox<String> sectionBox = new JComboBox<>();
	
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(4,1,10,5));
	
	public GradingPage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Grading Page", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize mainPanel
		switchPanel.add(tSection);
		switchPanel.add(sectionBox);
		mainPanel.add(switchPanel,BorderLayout.NORTH);
		mainPanel.add(listPanel,BorderLayout.CENTER);
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(name);
		textPanel.add(nameText);
		textPanel.add(new JLabel("    "));
		textPanel.add(id);
		textPanel.add(idText);
		textPanel.add(new JLabel("    "));
		textPanel.add(search);
		inputPanel.add(textPanel);
		inputPanel.add(new JLabel("    "));
		
		FlowLayout f= new FlowLayout();
		f.setHgap(150);
		JPanel btnPanel = new JPanel(f);
		btnPanel.add(view);
		btnPanel.add(save);
		inputPanel.add(btnPanel);
		inputPanel.add(new JLabel("    "));

		//arrange listeners
		view.addActionListener(this);
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
