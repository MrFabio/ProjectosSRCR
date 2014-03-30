/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

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
        
        this.data = new String[10][10];
        
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++)
                this.data[i][j] = "~~";
        }
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
    
}
