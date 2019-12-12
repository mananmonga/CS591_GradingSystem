package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.*;


public class CurvePage extends JPanel implements ActionListener, SettingChangeListener
{	

	JButton confirm = new JButton("                         Confirm                         ");
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(2,1,10,5));
	
    JScrollPane jScrollPane;
    String[] columnNames = {"Assignment Name", "Curve type", "Value"};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    HashSet<String> set = new HashSet<String>() {{
    	add("Assignment Name");
    }};
    JComboBox<String> curveType = new JComboBox<String>() {{
    	addItem("Flat");
    	addItem("Percentage");
    }};
    Course course;
    
	public CurvePage(Course course_)
	{ 	
		this.course = course_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Curve Setting", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		
		settingTable();
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize inputPanel
		JPanel tPanel1 = new JPanel();
		tPanel1.add(confirm);
		inputPanel.add(tPanel1);
		inputPanel.add(new JLabel("    "));
		
		//arrange listeners
		confirm.addActionListener(this);
		this.setVisible(true);
	}
	
	private void settingTable() {
		data.clear();
		for(Assignment a : course.getAssignments()) {
    		Vector<Object> list = new Vector<>();
			list.add(a.getName());
			if(a.getCurve() == null) {
				list.add(curveType.getItemAt(0));
				list.add("");
			}else if(a.getCurve() instanceof FlatCurve){
				list.add(curveType.getItemAt(0));
				list.add(a.getCurve().getAmount());
			}else if(a.getCurve() instanceof PercentageCurve){
				list.add(curveType.getItemAt(1));
				list.add(a.getCurve().getAmount());
			}
			data.add(list);
    	}   
		Vector<Object> list = new Vector<>();
		list.add("Course Curve");
		if(course.getCurve() == null) {
			list.add(curveType.getItemAt(0));
			list.add("");
		}else if(course.getCurve() instanceof FlatCurve){
			list.add(curveType.getItemAt(0));
			list.add(course.getCurve().getAmount());
		}else if(course.getCurve() instanceof PercentageCurve){
			list.add(curveType.getItemAt(1));
			list.add(course.getCurve().getAmount());
		}
		data.add(list);
		jTable = new JTable(new CustomizedTable(columnNames,data,set,null));
		jTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(curveType));
		jTable.setColumnSelectionAllowed(true);
		jTable.putClientProperty("terminateEditOnFocusLost", true);
    	jScrollPane = new JScrollPane(jTable);
    	listPanel.add(jScrollPane);
	}

	public void refreshCurveList() {
		listPanel.removeAll();
		settingTable();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void updatePage()
	{	
		refreshCurveList();
	}
}
