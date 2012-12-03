package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.JButton;

import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;

import de.sfgmbh.applayer.core.controller.Bootstrap;

import java.awt.Font;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;

public class ProfessorshipTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable LehrstuhlStundenplanTable;
	private JPanel panel;


	/**
	 * Create the panel.
	 */
	public ProfessorshipTimetableTab() {

		createContents();
	}
	private void createContents() {
		setAutoscrolls(true);
		setLayout(null);
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn());
		btnPdfErzeugen.setBounds(389, 17, 112, 23);
		add(btnPdfErzeugen);
		
		JLabel lblWochenplanFrDen = new JLabel("<html><h3>Wochenplan f\u00FCr den Lehrstuhl SEDA</h3></html>");
		lblWochenplanFrDen.setFont(new Font("Arial", Font.PLAIN, 11));
		lblWochenplanFrDen.setBounds(20, 26, 359, 14);
		add(lblWochenplanFrDen);
		add(getPanel());
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(20, 51, 868, 404);
			panel.setLayout(new MigLayout("", "grow"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0,alignx center,aligny top");
			
			LehrstuhlStundenplanTable = new JTable();
			LehrstuhlStundenplanTable.setPreferredScrollableViewportSize(new Dimension(750, 400));
			LehrstuhlStundenplanTable.setBorder(null);
			
			scrollPane.setViewportView(LehrstuhlStundenplanTable);
			
	
			LehrstuhlStundenplanTable.setBackground(Color.WHITE);
			LehrstuhlStundenplanTable.setModel(Bootstrap.serviceManager.getLecturerTimetableTabTable());
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMaxWidth(200);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMaxWidth(200);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMinWidth(75);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMaxWidth(200);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMinWidth(80);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMaxWidth(200);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMinWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMaxWidth(200);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMinWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMaxWidth(200);
		}
		return panel;
	}
}
