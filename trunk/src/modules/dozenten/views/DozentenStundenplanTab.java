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
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

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
		scrollPane.setBounds(20, 51, 854, 214);
		add(scrollPane);
		
		DozentStundenplanTable = new JTable();
		
		scrollPane.setViewportView(DozentStundenplanTable);
		
	
		DozentStundenplanTable.setBackground(Color.WHITE);
		DozentStundenplanTable.setModel(Bootstrap.serviceManager.getDozStundenplanTableModel());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dozent", "Wolf", "Benker", "Sinz"}));
		comboBox.setBounds(20, 20, 124, 20);
		add(comboBox);
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.setBounds(167, 19, 112, 23);
		add(btnPdfErzeugen);
		DozentStundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(0).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(1).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		DozentStundenplanTable.getColumnModel().getColumn(1).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(2).setResizable(false);
		DozentStundenplanTable.getColumnModel().getColumn(2).setMinWidth(75);
		DozentStundenplanTable.getColumnModel().getColumn(2).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		DozentStundenplanTable.getColumnModel().getColumn(3).setMinWidth(80);
		DozentStundenplanTable.getColumnModel().getColumn(3).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		DozentStundenplanTable.getColumnModel().getColumn(4).setMinWidth(70);
		DozentStundenplanTable.getColumnModel().getColumn(4).setMaxWidth(200);
		DozentStundenplanTable.getColumnModel().getColumn(5).setMinWidth(70);
		DozentStundenplanTable.getColumnModel().getColumn(5).setMaxWidth(200);
		
	

	}
}

