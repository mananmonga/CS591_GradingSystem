package classSrc;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


public class CustomizedTable extends AbstractTableModel {
	private String headName[];
	private Object data[][];
	private HashSet<String> uneditableCol;
	
    public CustomizedTable(String[] headName, Object[][] data, HashSet<String> set, TableModelListener listener) {
    	this.addTableModelListener(listener);
    	this.uneditableCol = set;
    	this.headName = headName;
    	this.data = data;
 	  }
 
    public CustomizedTable(String[] headName, Vector<Vector<Object>> obj, HashSet<String> set, TableModelListener listener) {
    	this.uneditableCol = set;
    	this.addTableModelListener(listener);
    	this.headName = headName;
    	Object[][] temp = new Object[obj.size()][];
    	for(int i = 0; i < obj.size(); i++) {
    		temp[i]= obj.get(i).toArray();
    	}
    	this.data = temp;
    }
    
    public CustomizedTable(Vector<String> headName, Vector<Vector<Object>> obj, HashSet<String> set, TableModelListener listener) {
    	this.uneditableCol = set;
    	this.addTableModelListener(listener);
    	this.headName = (String[]) headName.toArray();
    	Object[][] temp = new Object[obj.size()][];
    	for(int i = 0; i < obj.size(); i++) {
    		temp[i]= obj.get(i).toArray();
    	}
    	this.data = temp;
    }
    
    public int getColumnCount() {
        return headName.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return headName[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the boolean column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        if(uneditableCol.contains(getColumnName(col)))
        	return false;
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}