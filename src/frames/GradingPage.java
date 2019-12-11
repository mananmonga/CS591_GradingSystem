package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import classSrc.*;


public class GradingPage extends JPanel implements ActionListener, SettingChangeListener
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
    
	public GradingPage(Course course_)
	{ 	
		this.course = course_;
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
			
			ArrayList<Grade> orderedGrades = course.GetStudentGradesInAssignmentOrder(s);
			int i = 0;
			
			while(list.size()<columnNames.size()) {
				list.add(orderedGrades.get(i).getCredit());
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
		
	}

	@Override
	public void updatePage()
	{
		refreshGradeList();
	}
}
//DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
//singleclick.setClickCountToStart(1);
//set the editor as default on every column
//for (int i = 0; i < jTable.getColumnCount(); i++) {
	//jTable.setDefaultEditor(jTable.getColumnClass(i), singleclick);
//}