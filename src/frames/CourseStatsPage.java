package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import classSrc.*;

public class CourseStatsPage extends JPanel 
{	
	JPanel mainPanel = new JPanel(new GridLayout(2,1,10,5));
    JPanel listPanel1 = new JPanel(new BorderLayout());
    JPanel listPanel2 = new JPanel(new BorderLayout());
    
    Vector<String> columnNames1 = new Vector<String>();
    Vector<String> columnNames2 = new Vector<String>();
    Vector<String> fixedColumn = new Vector<String>() {{
    	add("Student ID");
    	add("Student Name");
    	add("Bonus");
    }};
    Vector<Vector<Object>> data1 = new Vector<>();
    Vector<Vector<Object>> data2 = new Vector<>();
    JScrollPane jScrollPane1;
    JScrollPane jScrollPane2;
    JTable jTable1;
    JTable jTable2;
    Course course;
	
	public CourseStatsPage(Course course_)
	{ 	
		this.course = course_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Course Statistics", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		settingTableStudent();
		settingTableCourse();
		mainPanel.add(listPanel1);
		mainPanel.add(listPanel2);
	}
	
	public void settingTableStudent(){
		JLabel label1 = new JLabel("  Curved Grades");
		label1.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		listPanel1.add(label1,BorderLayout.NORTH);
		columnNames1.clear();
		columnNames1.addAll(fixedColumn);
		for(Assignment a : course.getAssignments()) {
			columnNames1.add(a.getName());
    	}
		columnNames1.add("Final Score");
		data1.clear();
		for(EnrolledStudent s :course.getEnrollStudent()) {
    		Vector<Object> list = new Vector<>();
    		list.add(s.getID());
    		list.add(s.getName());
			list.add(s.getBonus());
			for(Grade g : s.getGrades()) {
				list.add(g.getCredit());
			}
			list.add("total");
			data1.add(list);
    	}
		DefaultTableModel model = new DefaultTableModel(data1,columnNames1){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		jTable1 = new JTable(model);
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);  
		jTable1.setRowSorter(sorter); 
    	jScrollPane1 = new JScrollPane(jTable1);
    	listPanel1.add(jScrollPane1);
	}
	
	public void settingTableCourse(){
		JLabel label2 = new JLabel("  Overall Statistics");
		label2.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		listPanel2.add(label2,BorderLayout.NORTH);
		columnNames2.clear();
		columnNames2.add("Cretiria");
		for(Assignment a : course.getAssignments()) {
			columnNames2.add(a.getName());
    	}
		columnNames2.add("Final Score");
		data2.clear();
		String[] cre = {"mean","max","min","median"};
		for(String s : cre) {
    		Vector<Object> list = new Vector<>();
    		list.add(s);
			for(Assignment g : this.course.getAssignments()) {
				list.add("");
			}
			list.add("total");
			data2.add(list);
    	}
		jTable2 = new JTable(new DefaultTableModel(data2,columnNames2)){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
    	jScrollPane2 = new JScrollPane(jTable2);
    	listPanel2.add(jScrollPane2);
	}
	
	public void refreshCourseStatsList() {
		listPanel1.removeAll();
		settingTableStudent();
		listPanel1.updateUI();
		listPanel2.removeAll();
		settingTableCourse();
		listPanel2.updateUI();
	}
	
}
