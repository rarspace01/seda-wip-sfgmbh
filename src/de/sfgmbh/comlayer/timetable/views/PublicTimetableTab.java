package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.timetable.controller.BtnPdf;

public class PublicTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable StundenplanTable;
	public PublicTimetableTab() {

		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 59, 830, 402);
		add(scrollPane);
		
		StundenplanTable = new JTable();
		
		scrollPane.setViewportView(StundenplanTable);
		
	
		StundenplanTable.setBackground(Color.WHITE);
		StundenplanTable.setModel(ServiceManager.getInstance().getLecturerTimetableTabTable());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new BtnPdf("pdfCreate"));
		btnPdfErzeugen.setBounds(21, 17, 131, 23);
		add(btnPdfErzeugen);
		StundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		StundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(0).setMaxWidth(105);
		StundenplanTable.getColumnModel().getColumn(1).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(2).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(2).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(2).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(5).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(5).setMaxWidth(145);
		
		JPanel panel = new JPanel();
		panel.setBounds(774, 0, 76, 61);
		add(panel);
		
		JLabel uniIconJLbl = new JLabel("");
		panel.add(uniIconJLbl);
		uniIconJLbl.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		uniIconJLbl.setMaximumSize(new Dimension(50,50));
	}

}
