package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.CustomizedTable;

public class AssignmentPage extends JPanel implements ActionListener
{	
	JLabel aType = new JLabel("Assignment Type:");
	JLabel aDesc = new JLabel("Description:");
	JLabel aCredit = new JLabel("Full Credits:");
	JLabel aWeight = new JLabel("Weight(%):");
	
	JComboBox<String> aTypeBox = new JComboBox<>();
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
    
    JScrollPane jScrollPane;
    String[] columnNames = {"Type", "Description", "Full Credits","Weight(%)"};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    int selectedIndex = -1;
    	
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
		
		for(int i =0 ;i < 3;i++) {
    		Vector<Object> list = new Vector<>();
			list.add("absolute");
			list.add("HW"+i);
			list.add(100);
			list.add(20);
			data.add(list);
    	}
		settingTable();
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
		this.setVisible(true);
	}
	
	private void settingTable() {
		jTable = new JTable(new CustomizedTable(columnNames,data));
		//DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    //singleclick.setClickCountToStart(1);
	    //set the editor as default on every column
	    //for (int i = 0; i < jTable.getColumnCount(); i++) {
	    	//jTable.setDefaultEditor(jTable.getColumnClass(i), singleclick);
	    //}
	    jTable.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e) {
	    		selectedIndex = jTable.getSelectedRow();
	    		System.out.println("selectedIndex:"+selectedIndex);
	     	}
	    });
    	jScrollPane = new JScrollPane(jTable);
    	jScrollPane.setBounds(62, 34, 624, 185);
    	listPanel.add(jScrollPane);
	}
	
	public void refreshCourseList() {
		listPanel.removeAll();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
