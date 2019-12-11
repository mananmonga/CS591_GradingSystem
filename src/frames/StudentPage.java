package frames;
import classSrc.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class StudentPage extends JPanel implements ActionListener, TableModelListener
{	
	JLabel sId = new JLabel("Student ID:");
	JLabel sName = new JLabel("Student Name:");
	JButton add = new JButton("   Add   ");
	JButton delete = new JButton(" Delete ");
	JButton confirm = new JButton("              Confirm              ");
	JTextField sIdText = new JTextField("",10);
	JTextField sNameText = new JTextField("",10);
	JLabel sData = new JLabel("Student Data:");
	JButton sLoad = new JButton(" Load... ");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel listPanel = new JPanel();
    JPanel inputPanel = new JPanel(new GridLayout(3,1,10,5));
    JScrollPane jScrollPane;
    String[] columnNames = {"Student Name", "Student ID", "Section"};
    JTable jTable;
    Vector<Vector<Object>> data = new Vector<>();
    HashSet<String> set = new HashSet<String>();
    Course course;
    ArrayList<EnrolledStudent> students;
    HashMap<String,SettingChangeListener> listeners = new HashMap<String,SettingChangeListener>();
    
	public StudentPage(Course course_)
	{ 	
		this.course = course_;
		//organize panel
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Student Setting", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(label,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(inputPanel,BorderLayout.SOUTH);
		
		//organize mainPanel
		JPanel loadPanel = new JPanel( new FlowLayout(FlowLayout.LEFT));
		loadPanel.add(sData);
		loadPanel.add(sLoad);
		mainPanel.add(loadPanel,BorderLayout.NORTH);
		
		//Try new thing
    	students = new ArrayList<EnrolledStudent>();
		for(EnrolledStudent s :course.getEnrollStudent()) 
			students.add(new EnrolledStudent(s));
		settingTable();
		mainPanel.add(listPanel,BorderLayout.CENTER);
		
		//organize inputPanel
		JPanel textPanel = new JPanel();
		textPanel.add(sId);
		textPanel.add(sIdText);
		textPanel.add(new JLabel("    "));
		textPanel.add(sName);
		textPanel.add(sNameText);
		textPanel.add(add);
		textPanel.add(delete);
		inputPanel.add(textPanel);
		
		inputPanel.add(new JLabel("    "));
		inputPanel.add(confirm);
		
		//arrange listeners
		add.addActionListener(this);
		delete.addActionListener(this);
		confirm.addActionListener(this);
		sLoad.addActionListener(this);
		this.setVisible(true);
	}
	
	private void settingTable() {
		data.clear();
		for(EnrolledStudent s : students) {
    		Vector<Object> list = new Vector<>();
    		list.add(s.getName());
			list.add(s.getID());
			list.add("");
			data.add(list);
    	}
		jTable = new JTable(new CustomizedTable(columnNames,data,set,this));
    	jScrollPane = new JScrollPane(jTable);
    	jTable.putClientProperty("terminateEditOnFocusLost", true);
    	listPanel.add(jScrollPane);
	}
	
	public void refreshStudentList() {
		listPanel.removeAll();
		sNameText.setText(null);
		sIdText.setText(null);
		settingTable();
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sLoad) {
			try {
				
				students = new ArrayList<EnrolledStudent>();
				Vector<Vector<Object>> tmp = ReadExcel.getSheet(SelectFile.select());
				for(int i = 0; i<tmp.size(); i++)
					students.add(new EnrolledStudent(tmp.get(i).get(0).toString(),tmp.get(i).get(1).toString()));
				refreshStudentList();
			}
			catch(Exception exception) {
				System.out.println(exception);
			}
		}
		if(e.getSource()==confirm) {
			course.setEnrollStudent(students);
			notifyALLListener();
		}
		if(e.getSource()==add) {
			if(sNameText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the name of student!");
			}else if(sIdText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the ID of student!");
			}else {
				String name = sNameText.getText();
				String id = sIdText.getText();
				EnrolledStudent temp = new EnrolledStudent(name, id);
				students.add(temp);
				refreshStudentList();
			}
		} 
		if(e.getSource()==delete) {
			if (jTable.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the student you want to delete.");
                return;
            }	
			students.remove(jTable.getSelectedRow());
			refreshStudentList();
		}
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
				case "Student Name":
					students.get(row).setName(content.toString());
					break;
				case "Student ID":
					students.get(row).setID(content.toString());
					break;	
            }
        }
	}
}
