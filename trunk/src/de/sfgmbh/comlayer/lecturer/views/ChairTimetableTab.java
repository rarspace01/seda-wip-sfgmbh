package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;

public class ChairTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable LehrstuhlStundenplanTable;
	private JPanel panel;


	/**
	 * Create the panel.
	 */
	public ChairTimetableTab() {

		createContents();
	}
	private void createContents() {
		setAutoscrolls(true);
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn());
		setLayout(new MigLayout("", "[359px][10px][112px][286px][101px][right]", "[61px][383px]"));
		add(btnPdfErzeugen, "cell 2 0,growx,aligny top");
		
		JLabel lblWochenplanFrDen = new JLabel("<html><h3>Wochenplan f\u00FCr den Lehrstuhl SEDA</h3></html>");
		lblWochenplanFrDen.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblWochenplanFrDen, "cell 0 0,growx,aligny center");
		
		JLabel lblUniIcon = new JLabel("");
		add(lblUniIcon, "cell 5 0,alignx right,aligny top");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		add(getPanel(), "cell 0 1 5 1,grow");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 4 0,alignx left,aligny top");
	}
	
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new MigLayout("", "[830px:n:830px,grow]", "[]"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0,growx,aligny top");
			
			LehrstuhlStundenplanTable = new JTable();
			LehrstuhlStundenplanTable.setPreferredScrollableViewportSize(new Dimension(750, 400));
			LehrstuhlStundenplanTable.setBorder(null);
			
			scrollPane.setViewportView(LehrstuhlStundenplanTable);
			
	
			LehrstuhlStundenplanTable.setBackground(Color.WHITE);
			LehrstuhlStundenplanTable.setModel(ViewManager.getInstance().getLecturerTimetableTabTable());
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMaxWidth(105);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMaxWidth(145);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setResizable(false);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMinWidth(75);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMaxWidth(145);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMinWidth(80);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMaxWidth(145);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMinWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMaxWidth(145);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMinWidth(70);
			LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMaxWidth(145);
		}
		return panel;
	}
}
