package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChairAcronym;
import de.sfgmbh.comlayer.core.model.CmbboxFilterUserClass;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogBtns;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogWin;
import javax.swing.JCheckBox;

/**
 * Dialog to create (and also edit) users
 * 
 * @author anna
 * @author hannes
 *
 */
public class UserCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNutzerkennung;
	private JTextField txtPasswort;
	private JTextField txtEmail;
	private JComboBox<String> comboBoxNutzerklasse;
	private JComboBox<String> comboBoxLehrstuhl;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblLastName;
	private JLabel lblLehrstuhl;
	private JLabel lblNutzerklasse;
	private JLabel lblPasswort;
	private JCheckBox chckbxUserDisabled;
	private User editUser;

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
	 * @param userToEdit
	 */
	public UserCreateDialog(User userToEdit) {
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
		this.comboBoxLehrstuhl.setBounds(115, 150, 116, 20);
		this.lblLehrstuhl.setBounds(12, 152, 120, 14);
		this.comboBoxNutzerklasse.setVisible(false);
		this.lblNutzerklasse.setVisible(false);
		this.lblPasswort.setText("Neues Passwort:");
		this.getChckbxUserDisabled().setVisible(true);
		this.setEditUser(userToEdit);
		this.getTxtLogin().setText(userToEdit.getLogin_());
		this.getTxtFirstName().setText(userToEdit.getfName_());
		this.getTxtLastName().setText(userToEdit.getlName_());
		this.getTxtEmail().setText(userToEdit.getMail_());
		if (userToEdit.getChair_() != null) {
			this.getComboBoxLehrstuhl().setSelectedItem(userToEdit.getChair_().getAcronym_());
		}
		this.getChckbxUserDisabled().setSelected(userToEdit.isDisabled_());
		
	}
	
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserCreateDialog.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Nutzer hinzufügen");
		setBounds(100, 100, 266, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Nutzerkennung:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 14, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane.add(lblEmail);
		
		lblPasswort = new JLabel("Passwort:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 123, 120, 14);
		contentPane.add(lblPasswort);
		
		lblNutzerklasse = new JLabel("Nutzerklasse:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 152, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(12, 181, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		txtNutzerkennung = new JTextField();
		txtNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNutzerkennung.setBounds(115, 11, 116, 20);
		contentPane.add(txtNutzerkennung);
		txtNutzerkennung.setColumns(10);
		
		txtPasswort = new JTextField();
		txtPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPasswort.setColumns(10);
		txtPasswort.setBounds(115, 121, 116, 20);
		contentPane.add(txtPasswort);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(115, 40, 116, 20);
		contentPane.add(txtEmail);
		
		comboBoxNutzerklasse = new JComboBox<String>();
		comboBoxNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxNutzerklasse.setModel(new CmbboxFilterUserClass("blank"));
		comboBoxNutzerklasse.setBounds(115, 150, 116, 20);
		contentPane.add(comboBoxNutzerklasse);
		
		comboBoxLehrstuhl = new JComboBox<String>();
		comboBoxLehrstuhl.setEditable(true);
		comboBoxLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxLehrstuhl.setModel(new CmbboxFilterChairAcronym(comboBoxLehrstuhl, "blank"));
		comboBoxLehrstuhl.setBounds(115, 179, 116, 20);
		contentPane.add(comboBoxLehrstuhl);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(141, 233, 90, 28);
		btnSpeichern.addActionListener(new UserCreateDialogBtns(this, "save"));
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(42, 233, 90, 28);
		btnAbbrechen.addActionListener(new UserCreateDialogBtns(this, "cancle"));
		contentPane.add(btnAbbrechen);
		contentPane.add(getLblFirstName());
		contentPane.add(getTxtFirstName());
		contentPane.add(getTxtLastName());
		contentPane.add(getLblLastName());
		contentPane.add(getChckbxUserDisabled());
		
		addWindowListener(new UserCreateDialogWin(this));
	}
	/**
	 * @return the txtNutzerkennung
	 */
	public JTextField getTxtLogin() {
		return txtNutzerkennung;
	}
	/**
	 * @return the txtPasswort
	 */
	public JTextField getTxtPasswort() {
		return txtPasswort;
	}
	/**
	 * @return the txtEmail
	 */
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	/**
	 * @return the comboBoxNutzerklasse
	 */
	public JComboBox<String> getComboBoxUserClass() {
		return comboBoxNutzerklasse;
	}
	/**
	 * @return the comboBoxLehrstuhl
	 */
	public JComboBox<String> getComboBoxLehrstuhl() {
		return comboBoxLehrstuhl;
	}
	public JLabel getLblFirstName() {
		if (lblFirstName == null) {
			lblFirstName = new JLabel("Vorname:");
			lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblFirstName.setBounds(12, 69, 120, 14);
		}
		return lblFirstName;
	}
	public JTextField getTxtFirstName() {
		if (txtFirstName == null) {
			txtFirstName = new JTextField();
			txtFirstName.setFont(new Font("SansSerif", Font.PLAIN, 12));
			txtFirstName.setColumns(10);
			txtFirstName.setBounds(115, 67, 116, 20);
		}
		return txtFirstName;
	}
	public JTextField getTxtLastName() {
		if (txtLastName == null) {
			txtLastName = new JTextField();
			txtLastName.setFont(new Font("SansSerif", Font.PLAIN, 12));
			txtLastName.setColumns(10);
			txtLastName.setBounds(115, 94, 116, 20);
		}
		return txtLastName;
	}
	public JLabel getLblLastName() {
		if (lblLastName == null) {
			lblLastName = new JLabel("Nachname:");
			lblLastName.setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblLastName.setBounds(12, 96, 120, 14);
		}
		return lblLastName;
	}
	public JCheckBox getChckbxUserDisabled() {
		if (chckbxUserDisabled == null) {
			chckbxUserDisabled = new JCheckBox("Benutzer deaktiviert");
			chckbxUserDisabled.setBounds(61, 178, 151, 23);
			chckbxUserDisabled.setVisible(false);
		}
		return chckbxUserDisabled;
	}

	/**
	 * @return the editUser
	 */
	public User getEditUser() {
		return editUser;
	}

	/**
	 * @param editUser the editUser to set
	 */
	public void setEditUser(User editUser) {
		this.editUser = editUser;
	}
}
