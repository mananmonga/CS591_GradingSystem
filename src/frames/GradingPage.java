package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import classSrc.*;


public class GradingPage extends JPanel implements ActionListener, SettingChangeListener, TableModelListener
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
	
    JScrollPane jScrollPane;
    Vector<String> columnNames = new Vector<String>();
    Vector<String> fixedColumn = new Vector<String>() {{
    	add("Student ID");
    	add("Student Name");
    	add("Bonus");
    }};

    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    Course course;
    CourseFrame rootframe;
    
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
		switchPanel.add(tSection);
		switchPanel.add(sectionBox);
		mainPanel.add(switchPanel,BorderLayout.NORTH);
		
		mainPanel.setPreferredSize(new Dimension(700,630));
		settingTable();
		listPanel.setPreferredSize(new Dimension(700,630));
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
	
	private void settingTable() {
		columnNames.clear();
		columnNames.addAll(fixedColumn);
		for(Assignment a : course.getAssignments()) {
			columnNames.add(a.getName());
    	}
		data.clear();
		for(EnrolledStudent s :course.getEnrollStudent()) {
    		Vector<Object> list = new Vector<>();
    		list.add(s.getID());
    		list.add(s.getName());
			list.add(s.getBonus());
			for(Grade g : s.getGrades()) {
				list.add(g.getCredit());
			}
			data.add(list);
    	}
		DefaultTableModel myModel = new DefaultTableModel(data,columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		    	if(column<2)
		    		return false;
		        return true;
		    }
		};
		myModel.addTableModelListener(this);
		jTable = new JTable(myModel);
		jTable.putClientProperty("terminateEditOnFocusLost", true);
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
			JOptionPane.showMessageDialog(getParent(), "Grades have been saved.");
		}
		else if(e.getSource()==search){
			/*
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
			refreshStudentStatsList(this.student); */
        }
	}

	public void saveGrade() {
		int column = jTable.getColumnCount();
		int row = jTable.getRowCount();
		for(int i = 0; i < row; i++) {
			for(int j = 2; j < column; j++) {
				if(j==2) {
					this.course.getEnrollStudent().get(i).setBonus(Double.valueOf(jTable.getValueAt(i, j).toString()));
				}else {
					this.course.getEnrollStudent().get(i).getGrades().get(j-3).setCredit(Double.valueOf(jTable.getValueAt(i, j).toString()));
				}
			}
		}
	}
	
	@Override
	public void updatePage()
	{
		refreshGradeList();
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		/*
		if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String colName = jTable.getColumnName(column);
            String content = jTable.getValueAt(row, column).toString();
            if(colName.equals("Bonus")) {
            	this.course.getEnrollStudent().get(row).setBonus(Double.valueOf(content));
            }else {
            	this.course.getEnrollStudent().get(row).getGrades().get(column-3).setCredit(Double.valueOf(content));
            }
        }*/
	}
}
//DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
//singleclick.setClickCountToStart(1);
//set the editor as default on every column
//for (int i = 0; i < jTable.getColumnCount(); i++) {
	//jTable.setDefaultEditor(jTable.getColumnClass(i), singleclick);
//}