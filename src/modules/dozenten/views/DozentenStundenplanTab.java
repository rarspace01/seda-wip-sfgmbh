package modules.dozenten.views;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JLayeredPane;

import services.*;
import javax.swing.table.DefaultTableModel;

public class DozentenStundenplanTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable DozentStundenplanTable;

	
	

	/**
	 * Create the panel.
	 */
	public DozentenStundenplanTab() {
		setAutoscrolls(true);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 51, 564, 301);
		add(scrollPane);
		
		DozentStundenplanTable = new JTable();
		
		scrollPane.setViewportView(DozentStundenplanTable);
		
	
		DozentStundenplanTable.setBackground(SystemColor.activeCaption);
		DozentStundenplanTable.setShowVerticalLines(false);
		DozentStundenplanTable.setModel(Bootstrap.serviceManager.getDozStunenplanTableModel());
		DozentStundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(0).setMaxWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(1).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		DozentStundenplanTable.getColumnModel().getColumn(1).setMinWidth(25);
		DozentStundenplanTable.getColumnModel().getColumn(1).setMaxWidth(25);
		DozentStundenplanTable.getColumnModel().getColumn(2).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(2).setMinWidth(75);
		DozentStundenplanTable.getColumnModel().getColumn(2).setMaxWidth(75);
		DozentStundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		DozentStundenplanTable.getColumnModel().getColumn(3).setMinWidth(80);
		DozentStundenplanTable.getColumnModel().getColumn(3).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		DozentStundenplanTable.getColumnModel().getColumn(4).setMinWidth(70);
		DozentStundenplanTable.getColumnModel().getColumn(4).setMaxWidth(200);
		
		
	

	}
	
}

