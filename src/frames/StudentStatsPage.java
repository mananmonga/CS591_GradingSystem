package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.*;


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
    JPanel listPanel = new JPanel(new BorderLayout());
    JPanel inputPanel = new JPanel(new GridLayout(3,1,10,5));
    EnrolledStudent student;
    JScrollPane jScrollPane;
    String[] columnNames = {"Assignment", "Credit", "Comments"};
    HashSet<String> set = new HashSet<String>() {{
    	add("Assignment");
    }};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    Course course;
    
	public StudentStatsPage(Course course_, EnrolledStudent student_)
	{ 	
		this.student = student_;
		this.course = course_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Student Statistics", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(listPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
				
		//organaize listPanel
		settingTable();
		
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
	
	private void settingTable() {
		JPanel showPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String studentName = this.student == null ? "":student.getName();
		String studentId = this.student == null ? "":student.getID();
		name = new JLabel("Name: "+studentName);
		id = new JLabel("ID: "+studentId);
		showPanel.add(name);
		showPanel.add(new JLabel("    "));
		showPanel.add(id);
		listPanel.add(showPanel,BorderLayout.NORTH);
		data.clear();
		if(student!=null) {
			for(Grade g : student.getGrades()) {
    			Vector<Object> list = new Vector<>();
    	    	list.add(g.getAssignment().getName());
    			list.add(g.getCredit());
 				list.add(g.getComment());
 				data.add(list);
    		}
    		Vector<Object> list = new Vector<>();
	    	list.add("Bonus Point");
			list.add(student.getBonus());
			list.add(student.getComment());
			data.add(list);
		}
		jTable = new JTable(new CustomizedTable(columnNames,data,set,null));
    	jScrollPane = new JScrollPane(jTable);
    	jTable.putClientProperty("terminateEditOnFocusLost", true);
    	listPanel.add(jScrollPane,BorderLayout.CENTER);
	}
	
	public void refreshStudentStatsList(EnrolledStudent student_) {
		this.student = student_;
		listPanel.removeAll();
		settingTable();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==search){
			if(nameText.getText().isEmpty()&&idText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please fill in the blank!");
            	return;
			}else if(nameText.getText().isEmpty()&&!idText.getText().isEmpty()) {
				for(EnrolledStudent s : course.getEnrollStudent()) {
					if(s.getID().equals(idText.getText().toString())) {
						this.student = s;
						break;
					}
				}
			}else if(!nameText.getText().isEmpty()&&idText.getText().isEmpty()) {
				for(EnrolledStudent s : course.getEnrollStudent()) {
					if(s.getName().equals(nameText.getText().toString())) {
						this.student = s;
						break;
					}
				}
			}else {
				for(EnrolledStudent s : course.getEnrollStudent()) {
					if(s.getName().equals(nameText.getText().toString())&&s.getID().equals(idText.getText().toString())) {
						this.student = s;
						break;
					}
				}
			}
			refreshStudentStatsList(this.student);
        }
	}
}
