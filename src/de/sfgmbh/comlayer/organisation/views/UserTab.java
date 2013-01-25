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
	private JTable userOrgaTable_;
	private JTextField textFieldUserLogin_;
	private JTextField textFieldMail_;
	private JLabel lblUserclass_;
	private JLabel lblLecturer_;
	private JLabel lblAuthentification_;
	private JLabel lblEmail_;
	private JComboBox<String> comboBoxUserclass_;
	private JComboBox<String> comboBoxChair_;
	private JScrollPane userTableScrollPane_;
	private JPanel buttonPanel_;
	private JButton btnEdit_;
	private JButton btnDelete_;
	private JLabel lblUniIcon_;
	private TableRowSorter<TableModel> rowSorter_;

	/**
	 * Create the frame.
	 */
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
		
		lblUserclass_ = new JLabel("Benutzerklasse:");
		add(lblUserclass_, "cell 2 0,aligny bottom");
		
		lblLecturer_ = new JLabel("Lehrstuhl (falls Dozent):");
		add(lblLecturer_, "cell 3 0,aligny bottom");
		
		lblAuthentification_ = new JLabel("Benutzerkennung:");
		add(lblAuthentification_, "cell 4 0,aligny bottom");
		
		lblEmail_ = new JLabel("E-Mail:");
		add(lblEmail_, "cell 5 0,aligny bottom");
		
		lblUniIcon_ = new JLabel("");
		lblUniIcon_.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon_.setMaximumSize(new Dimension(50,50));
		add(lblUniIcon_, "cell 6 0,alignx right,aligny top");
		
		comboBoxUserclass_ = new JComboBox<String>();
		comboBoxUserclass_.setToolTipText("<html>Sie können die Liste der <br> Benutzer über <br> diese Filter begrenzen</html>");
		comboBoxUserclass_.setModel(new CmbboxFilterUserClass());
		comboBoxUserclass_.addActionListener(new UserTabCmbboxFilter());
		
		comboBoxUserclass_.setEditable(true);
		comboBoxUserclass_.setAutoscrolls(true);
		add(comboBoxUserclass_, "cell 2 1,growx");
		
		comboBoxChair_ = new JComboBox<String>();
		comboBoxChair_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
		comboBoxChair_.setModel(new CmbboxFilterChairAcronym(comboBoxChair_));
		comboBoxChair_.addActionListener(new UserTabCmbboxFilter());
		comboBoxChair_.setEditable(true);
		comboBoxChair_.setAutoscrolls(true);
		add(comboBoxChair_, "cell 3 1,growx");
		
		textFieldUserLogin_ = new JTextField();
		textFieldUserLogin_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
		textFieldUserLogin_.addActionListener(new UserTabCmbboxFilter());
		add(textFieldUserLogin_, "cell 4 1,growx");
		textFieldUserLogin_.setColumns(10);
		
		textFieldMail_ = new JTextField();
		textFieldMail_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
		textFieldMail_.addActionListener(new UserTabCmbboxFilter());
		textFieldMail_.setColumns(10);
		add(textFieldMail_, "cell 5 1,growx");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow, aligny top");
		
		userTableScrollPane_ = new JScrollPane();
		add(userTableScrollPane_, "flowx,cell 2 2 4 1,grow");
		
		userOrgaTable_ = new JTable();
		userOrgaTable_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userOrgaTable_.setModel(ViewManager.getInstance().getOrgaUserTableModel());
		userOrgaTable_.setShowVerticalLines(false);
		userOrgaTable_.setBackground(SystemColor.activeCaption);
		userOrgaTable_.getColumnModel().removeColumn(userOrgaTable_.getColumn("Hidden"));
		userTableScrollPane_.setViewportView(userOrgaTable_);
		
		// Enable table sorting for the model
		rowSorter_ = new TableRowSorter<TableModel>();
		userOrgaTable_.setRowSorter(rowSorter_);
		rowSorter_.setModel(ViewManager.getInstance().getOrgaUserTableModel());
		rowSorter_.sort();
		
		buttonPanel_ = new JPanel();
		buttonPanel_.setLayout(null);
		buttonPanel_.setMinimumSize(new Dimension(80, 10));
		buttonPanel_.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel_, "cell 6 2,grow");
		
		btnEdit_ = new JButton("hinzuf\u00FCgen");
		btnEdit_.setToolTipText("<html>Klicken Sie hier <br> um einen neuen Nutzer anzulegen</hml>");
		btnEdit_.addActionListener(new UserTabBtnsControl("add"));
		btnEdit_.setBounds(6, 11, 88, 23);
		buttonPanel_.add(btnEdit_);
		
		JButton btnEdit = new JButton("bearbeiten");
		btnEdit.setToolTipText("<html>Selektieren Sie einen Nutzer<br> und klicken Sie hier um dessen Eigenschaften zu ändern</html>");
		btnEdit.addActionListener(new UserTabBtnsControl("edit"));
		btnEdit.setBounds(6, 45, 88, 23);
		buttonPanel_.add(btnEdit);
		
		btnDelete_ = new JButton("l\u00F6schen");
		btnDelete_.setToolTipText("<html>Selektieren Sie einen Nutzer<br> und klicken Sie hier um diesen zu löschen</html>");
		btnDelete_.addActionListener(new UserTabBtnsControl("delete"));
		btnDelete_.setBounds(6, 79, 88, 23);
		buttonPanel_.add(btnDelete_);
		
	}
	
	/**
	 * @return the roomOrgaTable
	 */
	public JTable getRoomOrgaTable() {
		return userOrgaTable_;
	}
	/**
	 * @return the textFieldUserLogin
	 */
	public JTextField getTextFieldUserLogin() {
		return textFieldUserLogin_;
	}
	/**
	 * @return the textFieldMail
	 */
	public JTextField getTextFieldMail() {
		return textFieldMail_;
	}
	/**
	 * @return the comboBoxUserclass
	 */
	public JComboBox<String> getComboBoxUserclass() {
		return comboBoxUserclass_;
	}
	/**
	 * @return the comboBoxChair
	 */
	public JComboBox<String> getComboBoxChair() {
		return comboBoxChair_;
	}
	/**
	 * @return the userOrgaTable
	 */
	public JTable getUserOrgaTable() {
		return userOrgaTable_;
	}
	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter_;
	}
	/**
	 * @param rowSorter the rowSorter to set
	 */
	public void setRowSorter(TableRowSorter<TableModel> rowSorter) {
		this.rowSorter_ = rowSorter;
	}
	
	
}