package modules.dozenten.views;

import javax.swing.JPanel;

import modules.dozenten.controller.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import services.*;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class DozentenStundenplanTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable DozentStundenplanTable;
	private JPanel panel;

	
	

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public DozentenStundenplanTab() {

		createContents();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createContents() {
		setAutoscrolls(true);
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new LehrstuhlplanTabCmbboxDoz());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dozent", "Wolf", "Benker", "Sinz"}));
		comboBox.setBounds(20, 20, 124, 20);
		add(comboBox);
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new LehrstuhlplanTabBtnPdf());
		btnPdfErzeugen.setBounds(167, 19, 112, 23);
		add(btnPdfErzeugen);
		add(getPanel());
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(20, 51, 854, 376);
			panel.setLayout(new MigLayout("", "grow"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0,grow");
			
			DozentStundenplanTable = new JTable();
			
			scrollPane.setViewportView(DozentStundenplanTable);
			
	
			DozentStundenplanTable.setBackground(Color.WHITE);
			DozentStundenplanTable.setModel(Bootstrap.serviceManager.getDozStundenplanTableModel());
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
		return panel;
	}
}

