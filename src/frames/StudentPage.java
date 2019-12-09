package frames;
import classSrc.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import classSrc.ReadExcel;
import classSrc.SelectFile;

public class StudentPage extends JPanel implements ActionListener
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
    int selectedIndex = -1;
	public StudentPage()
	{ 	//organize panel
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
    	for(int i =0 ;i < 3;i++) {
    		Vector<Object> list = new Vector<>();
			list.add("liao "+i);
			list.add("U"+i);
			list.add(i);
			data.add(list);
    	}
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
		jTable = new JTable(new CustomizedTable(columnNames,data));
	    jTable.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e) {
	    		selectedIndex = jTable.getSelectedRow();
	    		System.out.println("selectedIndex:"+selectedIndex);
	     	}
	    });
    	jScrollPane = new JScrollPane(jTable);
    	jScrollPane.setBounds(62, 34, 624, 185);
    	listPanel.add(jScrollPane);
	}
	
	public void refreshCourseList(Vector<Vector<Object>> data) {
		listPanel.removeAll();
		sNameText.setText(null);
		sIdText.setText(null);
		settingTable();
		selectedIndex = -1;
		listPanel.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sLoad) {
			try {
				data.clear();
				data = ReadExcel.getSheet(SelectFile.select());
				for(int i = 0; i<data.size();i++ )
					System.out.print(data.get(i).get(0)+" "+data.get(i).get(1));
				refreshCourseList(data);
			}
			catch(Exception exception) {
				System.out.println(exception);
			}
		}
		if(e.getSource()==confirm) {
			int numRows = jTable.getRowCount();
	        int numCols = jTable.getColumnCount();

	        for (int i=0; i < numRows; i++) {
	            System.out.print("    row " + i + ":");
	            for (int j=0; j < numCols; j++) {
	                System.out.print("  " + jTable.getValueAt(i, j));
	            }
	            System.out.println();
	        }
		}
		if(e.getSource()==add) {
			if(sNameText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the name of Student!");
			}else if(sIdText.getText().isEmpty()) {
            	JOptionPane.showMessageDialog(getParent(), "Please input the ID of Student!");
			}else {
				String name = sNameText.getText();
				String id = sIdText.getText();
				Vector<Object> list = new Vector<>();
				list.add(name);
				list.add(id);
				list.add(null);
				data.add(list);
				refreshCourseList(data);
			}
		} 
		if(e.getSource()==delete) {
			if (selectedIndex == -1){
                JOptionPane.showMessageDialog(getParent(), "Please select the student you want to delete.");
                return;
            }
			data.remove(selectedIndex);
        	refreshCourseList(data);
		}
	}
}
