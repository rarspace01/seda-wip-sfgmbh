package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChairAcronym;
import de.sfgmbh.comlayer.core.model.CmbboxFilterUserClass;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.UserTabBtnsControl;
import de.sfgmbh.comlayer.organisation.controller.UserTabCmbboxFilter;

/**
 * Tab for the user management
 * 
 * @author anna
 * @author hannes
 *
 */
public class UserTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable userOrgaTable;
	private JTextField textFieldUserLogin;
	private JTextField textFieldMail;
	private JLabel lblUserclass;
	private JLabel lblLecturer;
	private JLabel lblAuthentification;
	private JLabel lblEmail;
	private JComboBox<String> comboBoxUserclass;
	private JComboBox<String> comboBoxChair;
	private JScrollPane userTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnEdit;
	private JButton btnDelete;
	private JPanel uniIconPanel;
	private JLabel lblUniIcon;
	private TableRowSorter<TableModel> rowSorter;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public UserTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow][grow][grow][100px:100px:100px]", "[][][grow][]"));
		
		JLabel lblNutzerverwaltung = new JLabel("Nutzerverwaltung");
		lblNutzerverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblNutzerverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblUserclass = new JLabel("Benutzerklasse:");
		add(lblUserclass, "cell 2 0,aligny bottom");
		
		lblLecturer = new JLabel("Lehrstuhl (falls Dozent):");
		add(lblLecturer, "cell 3 0,aligny bottom");
		
		lblAuthentification = new JLabel("Benutzerkennung:");
		add(lblAuthentification, "cell 4 0,aligny bottom");
		
		lblEmail = new JLabel("E-Mail:");
		add(lblEmail, "cell 5 0,aligny bottom");
		
		comboBoxUserclass = new JComboBox<String>();
		comboBoxUserclass.setModel(new CmbboxFilterUserClass());
		comboBoxUserclass.addActionListener(new UserTabCmbboxFilter());
		
		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx right,aligny top");
		lblUniIcon = new JLabel("");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		comboBoxUserclass.setEditable(true);
		comboBoxUserclass.setAutoscrolls(true);
		add(comboBoxUserclass, "cell 2 1,growx");
		
		comboBoxChair = new JComboBox<String>();
		comboBoxChair.setModel(new CmbboxFilterChairAcronym(comboBoxChair));
		comboBoxChair.addActionListener(new UserTabCmbboxFilter());
		comboBoxChair.setEditable(true);
		comboBoxChair.setAutoscrolls(true);
		add(comboBoxChair, "cell 3 1,growx");
		
		textFieldUserLogin = new JTextField();
		textFieldUserLogin.addActionListener(new UserTabCmbboxFilter());
		add(textFieldUserLogin, "cell 4 1,growx");
		textFieldUserLogin.setColumns(10);
		
		textFieldMail = new JTextField();
		textFieldMail.addActionListener(new UserTabCmbboxFilter());
		textFieldMail.setColumns(10);
		add(textFieldMail, "cell 5 1,growx");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow, aligny top");
		
		userTableScrollPane = new JScrollPane();
		add(userTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		userOrgaTable = new JTable();
		userOrgaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userOrgaTable.setModel(ViewManager.getInstance().getOrgaUserTableModel());
		userOrgaTable.setShowVerticalLines(false);
		userOrgaTable.setBackground(SystemColor.activeCaption);
		userOrgaTable.getColumnModel().removeColumn(userOrgaTable.getColumn("Hidden"));
		userTableScrollPane.setViewportView(userOrgaTable);
		
		// Enable table sorting for the model
		rowSorter = new TableRowSorter<TableModel>();
		userOrgaTable.setRowSorter(rowSorter);
		rowSorter.setModel(ViewManager.getInstance().getOrgaUserTableModel());
		rowSorter.sort();
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnEdit = new JButton("hinzuf\u00FCgen");
		btnEdit.addActionListener(new UserTabBtnsControl("add"));
		btnEdit.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnEdit);
		
		JButton btnEdit = new JButton("bearbeiten");
		btnEdit.addActionListener(new UserTabBtnsControl("edit"));
		btnEdit.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnEdit);
		
		btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new UserTabBtnsControl("delete"));
		btnDelete.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnDelete);
		
	}
	
	/**
	 * @return the roomOrgaTable
	 */
	public JTable getRoomOrgaTable() {
		return userOrgaTable;
	}
	/**
	 * @return the textFieldUserLogin
	 */
	public JTextField getTextFieldUserLogin() {
		return textFieldUserLogin;
	}
	/**
	 * @return the textFieldMail
	 */
	public JTextField getTextFieldMail() {
		return textFieldMail;
	}
	/**
	 * @return the comboBoxUserclass
	 */
	public JComboBox<String> getComboBoxUserclass() {
		return comboBoxUserclass;
	}
	/**
	 * @return the comboBoxChair
	 */
	public JComboBox<String> getComboBoxChair() {
		return comboBoxChair;
	}
	/**
	 * @return the userOrgaTable
	 */
	public JTable getUserOrgaTable() {
		return userOrgaTable;
	}
	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter;
	}
	/**
	 * @param rowSorter the rowSorter to set
	 */
	public void setRowSorter(TableRowSorter<TableModel> rowSorter) {
		this.rowSorter = rowSorter;
	}
	
	
}