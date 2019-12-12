package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import classSrc.*;

public class AssignmentPage extends JPanel implements ActionListener, TableModelListener
{	
	JLabel aType = new JLabel("Type:");
	JLabel aName = new JLabel("Name:");
	JLabel aCredit = new JLabel("Full Credits:");
	JLabel aWeight = new JLabel("Weight:");
	
	JComboBox<String> aTypeBox = new JComboBox<String>(new String[] {GradingType.Absolute.toString(), GradingType.Deduction.toString()});
	JTextField aNameText = new JTextField("",10);
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
    String[] columnNames = {"Type", "Name", "Description", "Full Credits", "Weight"};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    HashSet<String> set = new HashSet<String>() {{
    	add("Type");
    }};
    Course course;
    ArrayList<Assignment> assignments;
    HashMap<String,SettingChangeListener> listeners = new HashMap<String,SettingChangeListener>();
    
	public AssignmentPage(Course course_)
	{ 	
		this.course = course_;
		//organize panel
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
		
		assignments = new ArrayList<Assignment>();
		for(Assignment a :course.getAssignments()) 
			assignments.add(new Assignment(a));
		settingTable();
		mainPanel.add(listPanel,BorderLayout.CENTER);
		mainPanel.setPreferredSize(new Dimension(700,630));
		listPanel.setPreferredSize(new Dimension(700,630));
		
		//organize inputPanel
		JPanel textPanel = new JPanel(new GridLayout(1,4,10,5));
		JPanel tPanel1 = new JPanel();
		tPanel1.add(aType);
		tPanel1.add(aTypeBox);
		
		JPanel tPanel2 = new JPanel();
		tPanel2.add(aName);
		tPanel2.add(aNameText);
		
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
		aTypeBox.addActionListener(this);
		this.setVisible(true);
	}
	
	private void settingTable() {
		data.clear();
		for(Assignment a : assignments) {
    		Vector<Object> list = new Vector<>();
    		list.add(a.getType());
			list.add(a.getName());
			list.add(a.getDescription());
			list.add(a.getFullCredit());
			list.add(a.getWeight());
			data.add(list);
    	}
		jTable = new JTable(new CustomizedTable(columnNames,data,set,this));
		jTable.putClientProperty("terminateEditOnFocusLost", true);
    	jScrollPane = new JScrollPane(jTable);
    	jScrollPane.setPreferredSize(new Dimension(700,630));
    	listPanel.add(jScrollPane);
	}
	
	public void refreshAssignmentList() {
		listPanel.removeAll();
		aNameText.setText(null);
		aCreditText.setText(null);
		aWeightText.setText(null);
		settingTable();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add) { //add a new assignment to unconfirmed assignments
			if(aNameText.getText().isEmpty()||aCreditText.getText().isEmpty()||aWeightText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please fill all the blanks!");
			}else if(aTypeBox.getSelectedIndex()==-1) {
            	JOptionPane.showMessageDialog(getParent(), "Please select an assignment Type!");
			}else {
				String type = aTypeBox.getSelectedItem().toString();
				System.out.println(type);
				String name = aNameText.getText();
				Double credit = Double.valueOf(aCreditText.getText());
				Double weight = Double.valueOf(aWeightText.getText());
				Assignment temp = new Assignment(type, name, "", credit, weight);
				assignments.add(temp);
				refreshAssignmentList();
			}
		}
		else if(e.getSource()==delete) {
			if (jTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the assignment you want to delete.");
                return;
            }
			assignments.remove(jTable.getSelectedRow());
			refreshAssignmentList();	
		}
		if(e.getSource() == confirm) { //teacher is trying to confirm a new assignment configuration for the course
			if(ValidateAssignmentWeights()) {
				course.setAssignments(assignments);
				notifyALLListener();
			}
			else {
				JOptionPane.showMessageDialog(getParent(), "Please re-adjust the of weight of assignments.");
			}
		}
		if(e.getSource() == save) { //teacher is trying to save this assignment template
			if(ValidateAssignmentWeights()) {
				GradingSystem.getInstance().getAssignmentTemplate().clear();
				for(Assignment a : assignments) 
					GradingSystem.getInstance().getAssignmentTemplate().add(new Assignment(a));
			}
			else {
				JOptionPane.showMessageDialog(getParent(), "Please re-adjust the of weight of assignments.");
			}
		}	
		if(e.getSource() == tLoad) { //teacher is trying to load a previous assignment structure template
			assignments = new ArrayList<Assignment>();
			for(Assignment a : GradingSystem.getInstance().getAssignmentTemplate()) 
				assignments.add(new Assignment(a));
			refreshAssignmentList();
		}
	}
	
	public void test() {
		for(Assignment a : assignments) 
			System.out.println(a.getID()+" "+a.getName()+" "+a.getType()+" "+a.getDescription()+" "+
					a.getFullCredit()+" "+a.getWeight());
	}
	
	//checks that unconfirmed assignment weights add to 1
	private boolean ValidateAssignmentWeights() {
		Double totalWeight = 0.0;
		for(Assignment a : assignments) {
			totalWeight += a.getWeight();
		}
		return totalWeight == 1.0;
	}
	
	public void addListener(String page, SettingChangeListener listener) {
		this.listeners.put(page,listener);
	}
	
	public void notifyALLListener() {
		for(String key:listeners.keySet()) 
			listeners.get(key).updatePage();
	}
	
	@Override
	public void tableChanged(TableModelEvent e)
	{
		if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String colName = jTable.getColumnName(column);
            Object content = jTable.getValueAt(row, column);
            switch(colName) {
				case "Name":
					assignments.get(row).setName(content.toString());
					break;
				case "Description":
					assignments.get(row).setDescription(content.toString());
					break;	
				case "Full Credits":
					assignments.get(row).setFullCredit(Double.valueOf(content.toString()));
					break;
				case "Weight":
					assignments.get(row).setWeight(Double.valueOf(content.toString()));
					break;
            }
        }
	}
}
