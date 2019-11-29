package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CurvePage extends JPanel implements ActionListener
{	

	JButton confirm = new JButton("                         Confirm                         ");
	JButton view = new JButton("            View Course Statistics           ");
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(4,1,10,5));
	
	public CurvePage()
	{ 	//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Curve Setting", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize inputPanel
		JPanel tPanel1 = new JPanel();
		tPanel1.add(confirm);
		JPanel tPanel2 = new JPanel();
		tPanel2.add(view);
		inputPanel.add(tPanel1);
		inputPanel.add(new JLabel("    "));
		inputPanel.add(tPanel2);
		inputPanel.add(new JLabel("    "));
		
		//arrange listeners
		confirm.addActionListener(this);
		view.addActionListener(this);
		this.setVisible(true);
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
