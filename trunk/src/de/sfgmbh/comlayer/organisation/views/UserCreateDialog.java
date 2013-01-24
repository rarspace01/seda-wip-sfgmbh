package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChairAcronym;
import de.sfgmbh.comlayer.core.model.CmbboxFilterUserClass;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogBtns;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogWin;

/**
 * Dialog to create (and also edit) users
 * 
 * @author anna
 * @author hannes
 *
 */
public class UserCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_;
	private JTextField txtNutzerkennung_;
	private JTextField txtPasswort_;
	private JTextField txtEmail_;
	private JComboBox<String> comboBoxNutzerklasse_;
	private JComboBox<String> comboBoxLehrstuhl_;
	private JLabel lblFirstName_;
	private JTextField txtFirstName_;
	private JTextField txtLastName_;
	private JLabel lblLastName_;
	private JLabel lblLehrstuhl_;
	private JLabel lblNutzerklasse_;
	private JLabel lblPasswort_;
	private JCheckBox chckbxUserDisabled_;
	private IntfUser editUser_;

	/**
	 * Create the frame.
	 */
	public UserCreateDialog() {
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
		this.setEditUser(null);
	}
	
	/**
	 * Create the frame in a modified version with a user that shall be edited
	 * @param userToEdits
	 */
	public UserCreateDialog(IntfUser userToEdit) {
		setModal(true);
		initialize();
		setTitle("Benutzerinfo bearbeiten");
		setLocationRelativeTo(null);
		this.comboBoxLehrstuhl_.setBounds(115, 150, 116, 20);
		this.lblLehrstuhl_.setBounds(12, 152, 120, 14);
		this.comboBoxNutzerklasse_.setVisible(false);
		this.lblNutzerklasse_.setVisible(false);
		this.lblPasswort_.setText("Neues Passwort:");
		this.getChckbxUserDisabled().setVisible(true);
		this.setEditUser(userToEdit);
		this.getTxtLogin().setText(userToEdit.getLogin_());
		this.getTxtFirstName().setText(userToEdit.getFirstName_());
		this.getTxtLastName().setText(userToEdit.getlName_());
		this.getTxtEmail().setText(userToEdit.getMail_());
		if (userToEdit.getChair_() != null) {
			this.getComboBoxLehrstuhl().setSelectedItem(userToEdit.getChair_().getAcronym());
		} 
		if (!userToEdit.getClass_().equals("lecturer")) {
			this.getComboBoxLehrstuhl().setVisible(false);
			this.lblLehrstuhl_.setVisible(false);
		}
		this.getChckbxUserDisabled().setSelected(userToEdit.isDisabled_());
		
	}
	
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserCreateDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setTitle("Neuen Nutzer anlegen");
		setBounds(100, 100, 266, 309);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Nutzerkennung:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 14, 120, 14);
		contentPane_.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane_.add(lblEmail);
		
		lblPasswort_ = new JLabel("Passwort:");
		lblPasswort_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort_.setBounds(12, 123, 120, 14);
		contentPane_.add(lblPasswort_);
		
		lblNutzerklasse_ = new JLabel("Nutzerklasse:");
		lblNutzerklasse_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse_.setBounds(12, 152, 120, 14);
		contentPane_.add(lblNutzerklasse_);
		
		lblLehrstuhl_ = new JLabel("Lehrstuhl:");
		lblLehrstuhl_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl_.setBounds(12, 181, 120, 14);
		contentPane_.add(lblLehrstuhl_);
		
		txtNutzerkennung_ = new JTextField();
		txtNutzerkennung_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNutzerkennung_.setBounds(115, 11, 116, 20);
		contentPane_.add(txtNutzerkennung_);
		txtNutzerkennung_.setColumns(10);
		
		txtPasswort_ = new JTextField();
		txtPasswort_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPasswort_.setColumns(10);
		txtPasswort_.setBounds(115, 121, 116, 20);
		contentPane_.add(txtPasswort_);
		
		txtEmail_ = new JTextField();
		txtEmail_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmail_.setColumns(10);
		txtEmail_.setBounds(115, 40, 116, 20);
		contentPane_.add(txtEmail_);
		
		comboBoxNutzerklasse_ = new JComboBox<String>();
		comboBoxNutzerklasse_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxNutzerklasse_.setModel(new CmbboxFilterUserClass("blank"));
		comboBoxNutzerklasse_.setBounds(115, 150, 116, 20);
		contentPane_.add(comboBoxNutzerklasse_);
		
		comboBoxLehrstuhl_ = new JComboBox<String>();
		comboBoxLehrstuhl_.setEditable(true);
		comboBoxLehrstuhl_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxLehrstuhl_.setModel(new CmbboxFilterChairAcronym(comboBoxLehrstuhl_, "blank"));
		comboBoxLehrstuhl_.setBounds(115, 179, 116, 20);
		contentPane_.add(comboBoxLehrstuhl_);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(141, 233, 90, 28);
		btnSpeichern.addActionListener(new UserCreateDialogBtns(this, "save"));
		contentPane_.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(42, 233, 90, 28);
		btnAbbrechen.addActionListener(new UserCreateDialogBtns(this, "cancle"));
		contentPane_.add(btnAbbrechen);
		contentPane_.add(getLblFirstName());
		contentPane_.add(getTxtFirstName());
		contentPane_.add(getTxtLastName());
		contentPane_.add(getLblLastName());
		contentPane_.add(getChckbxUserDisabled());
		
		addWindowListener(new UserCreateDialogWin(this));
	}
	/**
	 * @return the txtNutzerkennung
	 */
	public JTextField getTxtLogin() {
		return txtNutzerkennung_;
	}
	/**
	 * @return the txtPasswort
	 */
	public JTextField getTxtPasswort() {
		return txtPasswort_;
	}
	/**
	 * @return the txtEmail
	 */
	public JTextField getTxtEmail() {
		return txtEmail_;
	}
	/**
	 * @return the comboBoxNutzerklasse
	 */
	public JComboBox<String> getComboBoxUserClass() {
		return comboBoxNutzerklasse_;
	}
	/**
	 * @return the comboBoxLehrstuhl
	 */
	public JComboBox<String> getComboBoxLehrstuhl() {
		return comboBoxLehrstuhl_;
	}
	/**
	 * 
	 * @return the lblFirstName
	 */
	public JLabel getLblFirstName() {
		if (lblFirstName_ == null) {
			lblFirstName_ = new JLabel("Vorname:");
			lblFirstName_.setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblFirstName_.setBounds(12, 69, 120, 14);
		}
		return lblFirstName_;
	}
	/**
	 * 
	 * @return he txtFirstName
	 */
	public JTextField getTxtFirstName() {
		if (txtFirstName_ == null) {
			txtFirstName_ = new JTextField();
			txtFirstName_.setFont(new Font("SansSerif", Font.PLAIN, 12));
			txtFirstName_.setColumns(10);
			txtFirstName_.setBounds(115, 67, 116, 20);
		}
		return txtFirstName_;
	}
	/**
	 * 
	 * @return the txtLastName
	 */
	public JTextField getTxtLastName() {
		if (txtLastName_ == null) {
			txtLastName_ = new JTextField();
			txtLastName_.setFont(new Font("SansSerif", Font.PLAIN, 12));
			txtLastName_.setColumns(10);
			txtLastName_.setBounds(115, 94, 116, 20);
		}
		return txtLastName_;
	}
	/**
	 * 
	 * @return the lblLastName
	 */
	public JLabel getLblLastName() {
		if (lblLastName_ == null) {
			lblLastName_ = new JLabel("Nachname:");
			lblLastName_.setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblLastName_.setBounds(12, 96, 120, 14);
		}
		return lblLastName_;
	}
	/**
	 * 
	 * @return the chckbxUserDisabled
	 */
	public JCheckBox getChckbxUserDisabled() {
		if (chckbxUserDisabled_ == null) {
			chckbxUserDisabled_ = new JCheckBox("Benutzer deaktiviert");
			chckbxUserDisabled_.setBounds(61, 178, 151, 23);
			chckbxUserDisabled_.setVisible(false);
		}
		return chckbxUserDisabled_;
	}

	/**
	 * @return the editUser
	 */
	public IntfUser getEditUser() {
		return editUser_;
	}

	/**
	 * @param editUser the editUser to set
	 */
	public void setEditUser(IntfUser editUser) {
		this.editUser_ = editUser;
	}
}
