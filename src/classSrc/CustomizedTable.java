package classSrc;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;


public class CustomizedTable extends AbstractTableModel {
	private boolean DEBUG = true;
	private String headName[];
	private Object data[][];
    
    public CustomizedTable(String[] headName, Object[][] data) {
 	   this.headName = headName;
 	   this.data = data;
 	  }
 
    public CustomizedTable(String[] headName, Vector<Vector<Object>> obj) {
    	this.headName = headName;
    	Object[][] temp = new Object[obj.size()][];
    	for(int i = 0; i < obj.size(); i++) {
    		temp[i]= obj.get(i).toArray();
    	}
    	this.data = temp;
    }
    
    public CustomizedTable(Vector<String> headName, Vector<Vector<Object>> obj) {
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
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    public void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}