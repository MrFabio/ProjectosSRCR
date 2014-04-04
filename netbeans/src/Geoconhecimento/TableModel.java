/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chalkos
 */
public class TableModel extends AbstractTableModel{
    private String[] columnNames;
    private String[][] data;
    
    public TableModel(String[] columnNames){
        super();
        this.columnNames = new String[2];
        this.columnNames[0] = columnNames[0];
        this.columnNames[1] = columnNames[1];
        
        data = new String[0][0];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public void setDados(String id, HashMap<String,String> propriedades){
        data = new String[1+propriedades.size()][2];
        
        data[0][0] = "ID";
        data[0][1] = id;
        int i=1;
        
        for(Map.Entry<String, String> pair : propriedades.entrySet()) {
            data[i][0] = pair.getKey();
            data[i][1] = pair.getValue();
            i++;
        }
        
        fireTableDataChanged();
    }
    
    public void setCaminho(){
        
    }
}
