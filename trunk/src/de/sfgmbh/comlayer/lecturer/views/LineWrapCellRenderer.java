package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class LineWrapCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	
	private int maxEntries_;
	
	public LineWrapCellRenderer(int entries) {
		this.maxEntries_=entries;
	}
	
  @Override public Component getTableCellRendererComponent(
    JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column )
  {
  
		this.setText((String)value);

        //set the JTextArea to the width of the table column
        setSize(table.getColumnModel().getColumn(column).getWidth(),getPreferredSize().height);
 		if (table.getRowHeight(row) != getPreferredSize().height) {
 			//set the height of the table row to the calculated height of the JTextArea
 			if(this.maxEntries_>0){
        	table.setRowHeight(row, getPreferredSize().height* (this.maxEntries_));
 			}else{
 				table.setRowHeight(row, getPreferredSize().height);	
 			}
   		}

		return this;
	
	  
  }
}
