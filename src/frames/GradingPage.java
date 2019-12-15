package frames;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import classSrc.*;
import database.*;

public class GradingPage extends JPanel implements ActionListener, SettingChangeListener
{	
	JButton view = new JButton("     View Student     ");
	JButton save = new JButton("      Save Grade      ");
	JButton search = new JButton("Search");
	JLabel name = new JLabel("Name:");
	JLabel id = new JLabel("ID:");
	JLabel section = new JLabel("Section:");
	JTextField nameText = new JTextField("",10);
	JTextField idText = new JTextField("",10);
	JTextField sectionText = new JTextField("",10);	
	JLabel sectionBox = new JLabel("  ");
	
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(4,1,10,5));
	
    JScrollPane jScrollPane;
    Vector<String> columnNames = new Vector<String>();
    Vector<String> fixedColumn = new Vector<String>() {{
    	add("Student ID");
    	add("Student Name");
    	add("Section");
    	add("Bonus");
    }};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    Course course;
    CourseFrame rootframe;
    database db = new database();
    
	public GradingPage(Course course_, CourseFrame rootframe_)
	{ 	
		this.course = course_;
		this.rootframe = rootframe_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Grading Page", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize mainPanel
		switchPanel.add(sectionBox);
		mainPanel.add(switchPanel,BorderLayout.NORTH);
		
		mainPanel.setPreferredSize(new Dimension(700,630));
		settingTable();
		listPanel.setPreferredSize(new Dimension(700,630));
		mainPanel.add(listPanel,BorderLayout.CENTER);
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(id);
		textPanel.add(idText);
		textPanel.add(new JLabel("    "));
		textPanel.add(name);
		textPanel.add(nameText);
		textPanel.add(new JLabel("    "));
		textPanel.add(section);
		textPanel.add(sectionText);
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
	
	private void settingTable() {
		columnNames.clear();
		columnNames.addAll(fixedColumn);
		for(Assignment a : course.getAssignments()) {
			columnNames.add(a.getName());
    	}
		String columnToolTips[] = new String[columnNames.size()];
		columnToolTips[0]="Student ID is not editable here.";
		columnToolTips[1]="Student Name is not editable here.";
		columnToolTips[2]="Student Section is not editable here.";
		columnToolTips[3]="Bonus point for the student.";
		for(int i=4; i < columnNames.size(); i++) {
			String tooltip = course.getAssignments().get(i-4).getType()+", Full Credit:"+course.getAssignments().get(i-4).getFullCredit();
			columnToolTips[i] = tooltip;
		}
		data.clear();
		for(EnrolledStudent s :course.getEnrollStudent()) {
    		Vector<Object> list = new Vector<>();
    		list.add(s.getID());
    		list.add(s.getName());
    		list.add(s.getSection());
			list.add(s.getBonus());
			for(Grade g : s.getGrades()) {
				list.add(g.getCredit());
			}
			data.add(list);
    	}
		DefaultTableModel myModel = new DefaultTableModel(data,columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		    	if(column<3 )
		    		return false;
		        return true;
		    }
		};
		jTable = new JTable(myModel) {
			protected JTableHeader createDefaultTableHeader() {
		        return new JTableHeader(columnModel) {
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int index = columnModel.getColumnIndexAtX(p.x);
		                int realIndex = 
		                        columnModel.getColumn(index).getModelIndex();
		                return columnToolTips[realIndex];
		            }
		        };
		    }
		};
		jTable.putClientProperty("terminateEditOnFocusLost", true);
		TableRowSorter sorter = new TableRowSorter(myModel){
		    @Override public boolean isSortable(int column) {
			      return false;
			    }
			  };
		jTable.setRowSorter(sorter);  
    	jScrollPane = new JScrollPane(jTable);
    	jScrollPane.setPreferredSize(new Dimension(700,630));
    	listPanel.add(jScrollPane);
	}
	
	public void refreshGradeList() {
		listPanel.removeAll();
		settingTable();
		listPanel.updateUI();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==view) {
			if (jTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the student you want to review.");
                return;
            }	
			rootframe.viewStudentStatsPage(course.getEnrollStudent().get(jTable.getSelectedRow()));
		}
		else if(e.getSource()==save) {
			saveGrade();
		}
		else if(e.getSource()==search){
			String name = nameText.getText();
			String id = idText.getText();
			String section = sectionText.getText();
			if(name.length() == 0 && id.length() == 0 && section.length()==0) {
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(null);  
			}else if(name.length() != 0 && id.length() == 0&& section.length()==0) {
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(name, 1)); 
			}else if(name.length() == 0 && id.length() != 0&& section.length()==0) {
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(id, 0)); 
			}else if(name.length() != 0 && id.length() != 0&& section.length()==0){
				ArrayList<RowFilter<Object,Object>> rfs = 
			            new ArrayList<RowFilter<Object,Object>>();
				rfs.add(RowFilter.regexFilter(name, 1));
				rfs.add(RowFilter.regexFilter(id, 0));
				RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(rfs);
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(rf); 
			}
			//
			else if(name.length() == 0 && id.length() == 0 && section.length()!=0) {
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(RowFilter.regexFilter(section, 2));  
			}else if(name.length() != 0 && id.length() == 0&& section.length()!=0) {
				ArrayList<RowFilter<Object,Object>> rfs = 
			            new ArrayList<RowFilter<Object,Object>>();
				rfs.add(RowFilter.regexFilter(name, 1));
				rfs.add(RowFilter.regexFilter(section, 2));
				RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(rfs);
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(rf); 
			}else if(name.length() == 0 && id.length() != 0&& section.length()!=0) {
				ArrayList<RowFilter<Object,Object>> rfs = 
			            new ArrayList<RowFilter<Object,Object>>();
				rfs.add(RowFilter.regexFilter(id, 0));
				rfs.add(RowFilter.regexFilter(section, 2));
				RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(rfs);
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(rf); 
			}else if(name.length() != 0 && id.length() != 0&& section.length()!=0){
				ArrayList<RowFilter<Object,Object>> rfs = 
			            new ArrayList<RowFilter<Object,Object>>();
				rfs.add(RowFilter.regexFilter(name, 1));
				rfs.add(RowFilter.regexFilter(id, 0));
				rfs.add(RowFilter.regexFilter(section, 2));
				RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(rfs);
				((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(rf); 
			}
        }
	}

	public void saveGrade() {
		nameText.setText(null);
		idText.setText(null);
		((TableRowSorter)this.jTable.getRowSorter()).setRowFilter(null); 
		int column = jTable.getColumnCount();
		int row = jTable.getRowCount();
		for(int i = 0; i < row; i++) {
			for(int j = 3; j < column; j++) {
				if(j==3) {
					this.course.getEnrollStudent().get(i).setBonus(Double.valueOf(jTable.getValueAt(i, j).toString()));
				}else {
					if(!this.course.getEnrollStudent().get(i).getGrades().get(j-4).setCredit(Double.valueOf(jTable.getValueAt(i, j).toString()))) {
						JOptionPane.showMessageDialog(getParent(), "StudentId:"+this.course.getEnrollStudent().get(i).getID()+" Assignment:"+this.course.getEnrollStudent().get(i).getGrades().get(j-4).getAssignment().getName()+ " error!");
						return ;
					}
				}
			}
		}
		db.updateGrade(this.course);
		JOptionPane.showMessageDialog(getParent(), "Grades have been saved.");
	}
	
	@Override
	public void updatePage()
	{
		refreshGradeList();
	}
}
