package de.sfgmbh.comlayer.organisation.views;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.organisation.controller.ChairCreateDialogBtns;

/**
 * Dialog to edit and create chairs
 * 
 * @author hannes
 * @author anna
 *
 */
public class ChairCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblAcronym;
	private JLabel lblName;
	private JLabel lblFaculty;
	private JLabel lblLevel;
	private JLabel lblLecturer;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtAcronym;
	private JTextField txtName;
	private JComboBox<String> cmbboxLecturer;
	private JTextField txtFaculity;
	private JTextField txtLevel;
	private String variant;
	private Chair chair;

	/**
	 * Create the dialog.
	 */
	public ChairCreateDialog() {
		this.variant = "default";
		this.chair = new Chair();
		this.init();
	}
	
	/**
	 * Create the dialog based on a variant
	 * @param variant
	 * @param chair
	 */
	public ChairCreateDialog(String variant, Chair chair) {
		this.variant = variant;
		this.chair = chair;
		this.init();
	}
	
	private void init() {
		if (this.variant.equals("edit")) {
			setTitle("Lehrstuhl bearbeiten");
		} else {
			setTitle("Lehrstuhl anlegen");
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(CounterproposalDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 283, 246);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblAcronym());
		contentPanel.add(getLblName());
		contentPanel.add(getLblFaculty());
		contentPanel.add(getLblLevel());
		contentPanel.add(getLblLecturer());
		contentPanel.add(getBtnCancel());
		contentPanel.add(getBtnSave());
		contentPanel.add(getTxtAcronym());
		contentPanel.add(getTxtName());
		contentPanel.add(getCmbboxLecturer());
		contentPanel.add(getTxtFaculity());
		contentPanel.add(getTxtLevel());
		setLocationRelativeTo(null);
		
		// Customize if there is an old course and we are in edit mode
		if (this.variant.equals("edit")) {
			this.getTxtAcronym().setText(this.chair.getAcronym_());
			this.getTxtName().setText(this.chair.getChairName_());
			if (this.chair.getChairOwner_() != null) {
				this.getCmbboxLecturer().setSelectedItem(this.chair.getChairOwner_().getlName_());
			}
			this.getTxtFaculity().setText(this.chair.getFaculty_());
			this.getTxtLevel().setText(this.chair.getChairLevel_());
		}
	}
	
	public JLabel getLblAcronym() {
		if (lblAcronym == null) {
			lblAcronym = new JLabel("Kurzbezeichnung:");
			lblAcronym.setBounds(22, 22, 106, 14);
		}
		return lblAcronym;
	}
	public JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Langer Name:");
			lblName.setBounds(22, 47, 84, 14);
		}
		return lblName;
	}
	public JLabel getLblFaculty() {
		if (lblFaculty == null) {
			lblFaculty = new JLabel("Fakultät:");
			lblFaculty.setBounds(22, 73, 46, 14);
		}
		return lblFaculty;
	}
	public JLabel getLblLevel() {
		if (lblLevel == null) {
			lblLevel = new JLabel("Haupt-Stockwerk:");
			lblLevel.setBounds(22, 98, 106, 14);
		}
		return lblLevel;
	}
	public JLabel getLblLecturer() {
		if (lblLecturer == null) {
			lblLecturer = new JLabel("Inhaber:");
			lblLecturer.setBounds(22, 123, 46, 14);
		}
		return lblLecturer;
	}
	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Abbrechen");
			btnCancel.addActionListener(new ChairCreateDialogBtns(this, "cancel"));
			btnCancel.setBounds(56, 162, 89, 23);
		}
		return btnCancel;
	}
	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Speichern");
			btnSave.addActionListener(new ChairCreateDialogBtns(this, "save"));
			btnSave.setBounds(155, 162, 89, 23);
		}
		return btnSave;
	}
	public JTextField getTxtAcronym() {
		if (txtAcronym == null) {
			txtAcronym = new JTextField();
			txtAcronym.setBounds(138, 19, 106, 20);
			txtAcronym.setColumns(10);
		}
		return txtAcronym;
	}
	public JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(138, 44, 106, 20);
			txtName.setColumns(10);
		}
		return txtName;
	}
	public JComboBox<String> getCmbboxLecturer() {
		if (cmbboxLecturer == null) {
			cmbboxLecturer = new JComboBox<String>();
			cmbboxLecturer.setModel(new CmbboxFilterLecturer(cmbboxLecturer, "create"));
			cmbboxLecturer.setBounds(138, 120, 106, 20);
		}
		return cmbboxLecturer;
	}
	public JTextField getTxtFaculity() {
		if (txtFaculity == null) {
			txtFaculity = new JTextField();
			txtFaculity.setText("WI");
			txtFaculity.setEnabled(false);
			txtFaculity.setBounds(138, 70, 106, 20);
			txtFaculity.setColumns(10);
		}
		return txtFaculity;
	}
	public JTextField getTxtLevel() {
		if (txtLevel == null) {
			txtLevel = new JTextField();
			txtLevel.setBounds(138, 95, 106, 20);
			txtLevel.setColumns(10);
		}
		return txtLevel;
	}

	/**
	 * @return the chair
	 */
	public Chair getChair() {
		return chair;
	}

	/**
	 * @param course the chair to set
	 */
	public void setChair(Chair chair) {
		this.chair = chair;
	}
}
