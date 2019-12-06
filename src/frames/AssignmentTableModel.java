package frames;

import classSrc.Assignment;
import classSrc.Curve;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AssignmentTableModel extends AbstractTableModel {
    private ArrayList<Assignment> assignments;
    private String[] columnNames= new String[] {
            "Type","Name","Description","Total Credit","Weight", "Curve"
    };
    private Class[] columnClass= new Class[] {
            String.class, String.class, String.class, Double.class,Double.class, int.class
    };

    public AssignmentTableModel(ArrayList<Assignment> assignments, String[] columnNames, Class[] columnClass) {
        this.assignments = assignments;

    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
    public Class<?> getColumnClass (int columnIndex){
        return columnClass[columnIndex];

    }
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Assignment row = assignments.get(rowIndex);
        if (0 == columnIndex) {
            return row.getType();
        }
        if (1 == columnIndex) {
            return row.getName();
        }
        if (2 == columnIndex) {
            return row.getDescription();
        }
        if (3 == columnIndex) {
            return row.getTotalCredit();
        }
        if (4 == columnIndex) {
            return row.getWeight();
        }
        if (5 == columnIndex) {
            return row.getCurve();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Assignment row =assignments.get(rowIndex);
        if (0 == columnIndex) {
            row.setType((String)aValue);
        }
        if (1 == columnIndex) {
            row.setName((String)aValue);;
        }
        if (2 == columnIndex) {
            row.setDescription((String)aValue);;
        }
        if (3 == columnIndex) {
            row.setTotalCredit((Double) aValue);
        }
        if (4 == columnIndex) {
            row.setWeight((Double) aValue);
        }
        if (5 == columnIndex) {
            row.setCurve((Curve)aValue);
        }

    }
}
