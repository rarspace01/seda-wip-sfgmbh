package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class FirstColumnGrayRenderer extends DefaultTableCellRenderer 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FirstColumnGrayRenderer() 
    {
        super();
        setOpaque(true);
    } 
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) 
    { 
        if(column == 0)
        {
            setForeground(Color.black);        
            setBackground(Color.gray);            
        }    
        else
        {    
            setBackground(Color.white);    
            setForeground(Color.black);    
        } 
        setText(value !=null ? value.toString() : "");
        return this;
    }
}