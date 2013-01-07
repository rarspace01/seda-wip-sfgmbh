package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabCmbbox;
import de.sfgmbh.init.Bootstrap;
import javax.swing.JLabel;

public class TimetableTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable lecturerTimetableTable;
	private JPanel panel;

	
	

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public TimetableTab() {

		createContents();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createContents() {
		setAutoscrolls(true);
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ProfessorshipTimetableTabCmbbox());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dozent", "Wolf", "Benker", "Sinz"}));
		comboBox.setBounds(20, 20, 124, 20);
		add(comboBox);
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn());
		btnPdfErzeugen.setBounds(167, 19, 112, 23);
		add(btnPdfErzeugen);
		add(getPanel());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(780, 0, 76, 67);
		add(panel_1);
		
		JLabel uniIconJLbl = new JLabel("");
		uniIconJLbl.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		uniIconJLbl.setMaximumSize(new Dimension(50,50));
		panel_1.add(uniIconJLbl);
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(20, 63, 854, 364);
			panel.setLayout(new MigLayout("", "[830px:n:830px,grow]", "[]"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0,grow");
			
			lecturerTimetableTable = new JTable();
			
			scrollPane.setViewportView(lecturerTimetableTable);
			
	
			lecturerTimetableTable.setBackground(Color.WHITE);
			lecturerTimetableTable.setModel(Bootstrap.serviceManager.getLecturerTimetableTabTable());
			lecturerTimetableTable.getColumnModel().getColumn(0).setResizable(false);
			lecturerTimetableTable.getColumnModel().getColumn(0).setPreferredWidth(50);
			lecturerTimetableTable.getColumnModel().getColumn(0).setMinWidth(50);
			lecturerTimetableTable.getColumnModel().getColumn(0).setMaxWidth(105);
			lecturerTimetableTable.getColumnModel().getColumn(1).setResizable(false);
			lecturerTimetableTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			lecturerTimetableTable.getColumnModel().getColumn(1).setMinWidth(50);
			lecturerTimetableTable.getColumnModel().getColumn(1).setMaxWidth(145);
			lecturerTimetableTable.getColumnModel().getColumn(2).setResizable(false);
			lecturerTimetableTable.getColumnModel().getColumn(2).setMinWidth(75);
			lecturerTimetableTable.getColumnModel().getColumn(2).setMaxWidth(145);
			lecturerTimetableTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			lecturerTimetableTable.getColumnModel().getColumn(3).setMinWidth(80);
			lecturerTimetableTable.getColumnModel().getColumn(3).setMaxWidth(145);
			lecturerTimetableTable.getColumnModel().getColumn(4).setPreferredWidth(70);
			lecturerTimetableTable.getColumnModel().getColumn(4).setMinWidth(70);
			lecturerTimetableTable.getColumnModel().getColumn(4).setMaxWidth(145);
			lecturerTimetableTable.getColumnModel().getColumn(5).setMinWidth(70);
			lecturerTimetableTable.getColumnModel().getColumn(5).setMaxWidth(145);
		}
		return panel;
	}
}

