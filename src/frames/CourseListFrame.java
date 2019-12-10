package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import classSrc.*;

public class CourseListFrame extends JFrame implements ActionListener, TableModelListener
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
    
    JScrollPane jScrollPane;
    String[] columnNames = {"Course ID", "Course Name", "Description", "Create Date"};
    HashSet<String> set = new HashSet<String>() {{
    	add("Create Date");
    }};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    ArrayList<Course> courses;
	
	public CourseListFrame(ArrayList<Course> courses_)
	{ 	
		//TODO: PASS LIST OF COURSES FROM DATABASE
		this.courses = courses_;
		courses.add(new Course());
		Course aa = new Course("Data science","CS 506");
		aa.getAssignments().add(new Assignment("Absolute Grading", "HW2", "", Double.valueOf(100.00), Double.valueOf(0.5)));
		aa.getAssignments().add(new Assignment("Deduction Grading", "Quiz2", "", Double.valueOf(150.00), Double.valueOf(0.5)));
		courses.add(aa);

		//organize frame
		this.setTitle("Courses");
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Course List",SwingConstants.CENTER),BorderLayout.NORTH);
		
		//organize listPanel
		settingTable();
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
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
	
	private void settingTable() {
		data.clear();
		for(Course c : courses) {
    		Vector<Object> list = new Vector<>();
			list.add(c.getID());
			list.add(c.getName());
			list.add(c.getDescription());
			list.add(c.getCreateDate());
			data.add(list);
    	}
		jTable = new JTable(new CustomizedTable(columnNames,data,set,this));
	    jTable.putClientProperty("terminateEditOnFocusLost", true);
    	jScrollPane = new JScrollPane(jTable);
    	jScrollPane.setBounds(62, 34, 624, 185);
    	listPanel.add(jScrollPane);
	}
	
	public void refreshCourseList() {
		cNameText.setText(null);
		cIdText.setText(null);
		listPanel.removeAll();
		settingTable();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			if(cNameText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the name of course!");
			}else if(cIdText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the ID of course!");
			}else {
				String name = cNameText.getText();
				String id = cIdText.getText();
				Course newCourse = new Course(name,id);
				courses.add(newCourse);
				refreshCourseList();
			}
		}
		else if(e.getSource()==delete) {
			if (jTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the course you want to delete.");
                return;
            }
			courses.remove(jTable.getSelectedRow());
        	refreshCourseList();	
		} 
		else if(e.getSource()==view) {
			//TODO: FIND WHICH COURSE IS SELECTED FROM THE UI LIST AND PASS IT INTO COURSE FRAME AS A PARAMETER
			if (jTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the course.");
                return;
            }
			new CourseFrame(courses.get(jTable.getSelectedRow()));
		}
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
				case "Course ID":
					courses.get(row).setID(content.toString());
					break;
				case "Course Name":
					courses.get(row).setName(content.toString());
					break;
				case "Description":
					courses.get(row).setDescription(content.toString());
					break;
            }
        }
	}

}
