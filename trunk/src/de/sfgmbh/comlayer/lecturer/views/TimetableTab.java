package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabCmbbox;

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ProfessorshipTimetableTabCmbbox());
		setLayout(new MigLayout("", "[124px][23px][112px][501px][94px][right]", "[42px][21px][364px]"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dozent", "Wolf", "Benker", "Sinz"}));
		add(comboBox, "cell 0 0,growx,aligny bottom");
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn());
		add(btnPdfErzeugen, "cell 2 0,growx,aligny bottom");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 5 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel("");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		add(getPanel(), "cell 0 2 5 1,grow");
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new MigLayout("", "[830px:n:830px,grow]", "[]"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0,grow");
			
			lecturerTimetableTable = new JTable();
			
			scrollPane.setViewportView(lecturerTimetableTable);
			
	
			lecturerTimetableTable.setBackground(Color.WHITE);
			lecturerTimetableTable.setModel(ViewManager.getInstance().getLecturerTimetableTabTable());
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

