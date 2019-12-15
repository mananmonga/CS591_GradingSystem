package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
    	add("ID");
    	add("Name");
    	add("Section");
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
			columnNames1.add(a.getName()+"/C");
			columnNames1.add(a.getName()+"/R");
    	}
		columnNames1.add("Total/C");
		columnNames1.add("Total/R");
		String columnToolTips1[] = new String[columnNames1.size()];
		columnToolTips1[0]="Student ID.";
		columnToolTips1[1]="Student Name.";
		columnToolTips1[2]="Student Section.";
		columnToolTips1[3]="Bonus point for the student.";
		for(int i=4; i < columnNames1.size()-3; i+=2) {
			String tooltip = "Curverd, Full Credit:"+course.getAssignments().get((i-4)/2).getFullCredit();
			columnToolTips1[i] = tooltip;
			String tooltip1 = "Raw, Full Credit:"+course.getAssignments().get((i-4)/2).getFullCredit();
			columnToolTips1[i+1] = tooltip1;
		}
		columnToolTips1[columnNames1.size()-2] = "Final Score, Curved.";
		columnToolTips1[columnNames1.size()-1] = "Final Score, Raw.";
		data1.clear();
		for(EnrolledStudent s :course.getEnrollStudent()) {
    		Vector<Object> list = new Vector<>();
    		list.add(s.getID());
    		list.add(s.getName());
    		list.add(s.getSection());
			list.add(s.getBonus());
			for(Grade g : s.getGrades()) {
				list.add(g.calculatePercentageScore(true) );
				list.add(g.calculatePercentageScore(false) );
			}
			list.add(this.course.GetOverallScore(s, true).toString() );
			list.add(this.course.GetOverallScore(s, false).toString() );
			data1.add(list);
    	}
		DefaultTableModel model = new DefaultTableModel(data1,columnNames1){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		    public Class getColumnClass(int column){
		    	  Class returnValue;
		    	  if ((column >= 0) && (column < getColumnCount()))
		    	  {
		    	  //Double a = Double.valueOf(getValueAt(0, column).toString());
		    	  returnValue =	getValueAt(0, column).getClass();
		    	  }
		    	  else{
		    	  returnValue = Object.class;
		    	  }
		    	  return returnValue;
		    	  }
		};
		jTable1 = new JTable(model) {
			protected JTableHeader createDefaultTableHeader() {
		        return new JTableHeader(columnModel) {
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int index = columnModel.getColumnIndexAtX(p.x);
		                int realIndex = 
		                        columnModel.getColumn(index).getModelIndex();
		                return columnToolTips1[realIndex];
		            }
		        };
		    }
		};
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
		columnNames2.add("Criteria");
		for(Assignment a : course.getAssignments()) {
			columnNames2.add(a.getName()+" Curved/Raw"); 
    	}
		columnNames2.add("Total Curved/Raw");
		
		String columnToolTips2[] = new String[columnNames2.size()];
		columnToolTips2[0] = "Criteria";
		for(int i=1; i < columnNames2.size()-1; i++) {
			String tooltip = "Full Credit:"+course.getAssignments().get(i-1).getFullCredit();
			columnToolTips2[i] = tooltip;
		}
		columnToolTips2[columnNames2.size()-1] = "Final Score.";
		data2.clear();
		String[] cre = {"mean","max","min","median", };
		
		//calculate the statistics for each assignment
		ArrayList<StatisticsHolder> rawAssignmentsStats = new ArrayList<StatisticsHolder>();
		ArrayList<StatisticsHolder> curvedAssignmentsStats = new ArrayList<StatisticsHolder>();
		for(Assignment a : this.course.getAssignments()) {
			rawAssignmentsStats.add(this.course.CalculateAssignmentStats(a, false));
			curvedAssignmentsStats.add(this.course.CalculateAssignmentStats(a, true));
		}
		
		//calculate the statistics for the overall course grades
		StatisticsHolder rawOverallCourseStats = this.course.CalculateOverallCourseStats(false);
		StatisticsHolder curvedOverallCourseStats = this.course.CalculateOverallCourseStats(true);
		
		for(String s : cre) {
    		Vector<Object> list = new Vector<>();
    		list.add(s);
			for(int i = 0; i < this.course.getAssignments().size(); i += 1) {
				//list.add("");
				String statDisplay = ""; //default
				if(s.equals("mean")) {
					statDisplay = curvedAssignmentsStats.get(i).GetAverage().toString() +" / "+  rawAssignmentsStats.get(i).GetAverage().toString();
				}
				else if(s.equals("max")) {
					statDisplay = curvedAssignmentsStats.get(i).GetMax().toString() +" / "+ rawAssignmentsStats.get(i).GetMax().toString();
				}
				else if(s.equals("min")) {
					statDisplay = curvedAssignmentsStats.get(i).GetMin().toString() +" / "+ rawAssignmentsStats.get(i).GetMin().toString();
				}
				else if(s.equals("median")) {
					statDisplay = curvedAssignmentsStats.get(i).GetMedian().toString() +" / "+ rawAssignmentsStats.get(i).GetMedian().toString() ;
				}
				list.add(statDisplay);
				
			}
			
			if(s.equals("mean")) {
				list.add(curvedOverallCourseStats.GetAverage().toString() +" / "+ rawOverallCourseStats.GetAverage().toString() );
			}
			else if(s.equals("max")) {
				list.add(curvedOverallCourseStats.GetMax().toString() +" / "+ rawOverallCourseStats.GetMax().toString() );
			}
			else if(s.equals("min")) {
				list.add(curvedOverallCourseStats.GetMin().toString() +" / "+ rawOverallCourseStats.GetMin().toString() );
			}
			else if(s.equals("median")) {
				list.add(curvedOverallCourseStats.GetMedian().toString() +" / "+ rawOverallCourseStats.GetMedian().toString() );
			}
			else {
				list.add("total"); //default
			}
			
			data2.add(list);
    	}
		jTable2 = new JTable(new DefaultTableModel(data2,columnNames2)){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		    protected JTableHeader createDefaultTableHeader() {
		        return new JTableHeader(columnModel) {
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int index = columnModel.getColumnIndexAtX(p.x);
		                int realIndex = 
		                        columnModel.getColumn(index).getModelIndex();
		                return columnToolTips2[realIndex];
		            }
		        };
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
