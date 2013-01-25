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

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.organisation.controller.ChairCreateDialogBtns;

/**
 * Dialog to edit and create chairs
 * 
 * @author anna
 * 
 */
public class ChairCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel_ = new JPanel();
	private JLabel lblAcronym_;
	private JLabel lblName_;
	private JLabel lblFaculty_;
	private JLabel lblLevel_;
	private JLabel lblLecturer_;
	private JButton btnCancel_;
	private JButton btnSave_;
	private JTextField txtAcronym_;
	private JTextField txtName_;
	private JComboBox<String> cmbboxLecturer_;
	private JTextField txtFaculity_;
	private JTextField txtLevel_;
	private String variant_;
	private IntfChair chair_;

	/**
	 * Create the dialog
	 */
	public ChairCreateDialog() {
		this.variant_ = "default";
		this.chair_ = new Chair();
		this.init();
	}

	/**
	 * Create the dialog based on a variant with no chair (create)
	 */
	public ChairCreateDialog(String variant) {
		this.variant_ = variant;
		this.chair_ = new Chair();
		this.init();
	}

	/**
	 * Create the dialog based on a variant
	 * 
	 * @param variant
	 * @param chair
	 */
	public ChairCreateDialog(String variant, IntfChair chair) {
		this.variant_ = variant;
		this.chair_ = chair;
		this.init();
	}

	private void init() {
		if (this.variant_.equals("edit")) {
			setTitle("Lehrstuhlinfo bearbeiten");
		} else {
			setTitle("Neuen Lehrstuhl anlegen");
		}
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						CounterproposalDialog.class
								.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 283, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel_.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel_, BorderLayout.CENTER);
		contentPanel_.setLayout(null);
		contentPanel_.add(getLblAcronym());
		contentPanel_.add(getLblName());
		contentPanel_.add(getLblFaculty());
		contentPanel_.add(getLblLevel());
		contentPanel_.add(getLblLecturer());
		contentPanel_.add(getBtnCancel());
		contentPanel_.add(getBtnSave());
		contentPanel_.add(getTxtAcronym());
		contentPanel_.add(getTxtName());
		contentPanel_.add(getCmbboxLecturer());
		contentPanel_.add(getTxtFaculity());
		contentPanel_.add(getTxtLevel());

		// Customize the create frame
		if (this.variant_.equals("create")) {
			this.getLblLecturer().setVisible(false);
			this.getCmbboxLecturer().setVisible(false);
		}

		setLocationRelativeTo(null);

		// Customize if there is an old course and we are in edit mode
		if (this.variant_.equals("edit")) {
			this.getTxtAcronym().setText(this.chair_.getAcronym());
			this.getTxtName().setText(this.chair_.getChairName());
			if (this.chair_.getChairOwner() != null) {
				this.getCmbboxLecturer().setSelectedItem(
						this.chair_.getChairOwner().getlName_());
			}
			this.getTxtFaculity().setText(this.chair_.getFaculty());
			this.getTxtLevel().setText(this.chair_.getChairLevel());
		}
	}

	public JLabel getLblAcronym() {
		if (lblAcronym_ == null) {
			lblAcronym_ = new JLabel("Kurzbezeichnung:");
			lblAcronym_.setBounds(22, 22, 106, 14);
		}
		return lblAcronym_;
	}

	public JLabel getLblName() {
		if (lblName_ == null) {
			lblName_ = new JLabel("Langer Name:");
			lblName_.setBounds(22, 47, 84, 14);
		}
		return lblName_;
	}

	public JLabel getLblFaculty() {
		if (lblFaculty_ == null) {
			lblFaculty_ = new JLabel("Fakultät:");
			lblFaculty_.setBounds(22, 73, 46, 14);
		}
		return lblFaculty_;
	}

	public JLabel getLblLevel() {
		if (lblLevel_ == null) {
			lblLevel_ = new JLabel("Haupt-Stockwerk:");
			lblLevel_.setBounds(22, 98, 106, 14);
		}
		return lblLevel_;
	}

	public JLabel getLblLecturer() {
		if (lblLecturer_ == null) {
			lblLecturer_ = new JLabel("Inhaber:");
			lblLecturer_.setBounds(22, 123, 46, 14);
		}
		return lblLecturer_;
	}

	public JButton getBtnCancel() {
		if (btnCancel_ == null) {
			btnCancel_ = new JButton("Abbrechen");
			btnCancel_
					.setToolTipText("<html>Wenn Sie den Vorgang abbrechen wollen</html>");
			btnCancel_.addActionListener(new ChairCreateDialogBtns(this,
					"cancel"));
			btnCancel_.setBounds(56, 162, 89, 23);
		}
		return btnCancel_;
	}

	public JButton getBtnSave() {
		if (btnSave_ == null) {
			btnSave_ = new JButton("Speichern");
			btnSave_.setToolTipText("<html>Klicken Sie hier, um<br> Ihre Eingaben zu speichern</html>");
			btnSave_.addActionListener(new ChairCreateDialogBtns(this, "save"));
			btnSave_.setBounds(155, 162, 89, 23);
		}
		return btnSave_;
	}

	public JTextField getTxtAcronym() {
		if (txtAcronym_ == null) {
			txtAcronym_ = new JTextField();
			txtAcronym_
					.setToolTipText("<html>Bitte geben Sie eine <br>Kurzbezeichnung des Lehrstuhls an</html>");
			txtAcronym_.setBounds(138, 19, 106, 20);
			txtAcronym_.setColumns(10);
		}
		return txtAcronym_;
	}

	public JTextField getTxtName() {
		if (txtName_ == null) {
			txtName_ = new JTextField();
			txtName_.setToolTipText("<html>Geben Sie den Lehrstuhlnamen ausgeschrieben an</hmtl>");
			txtName_.setBounds(138, 44, 106, 20);
			txtName_.setColumns(10);
		}
		return txtName_;
	}

	public JComboBox<String> getCmbboxLecturer() {
		if (cmbboxLecturer_ == null) {
			cmbboxLecturer_ = new JComboBox<String>();
			cmbboxLecturer_
					.setToolTipText("<html>Bitte ordnen Sie dem Lehrstuhl<br> einen Nutzer als Inhaber zu</html>");
			cmbboxLecturer_.setModel(new CmbboxFilterLecturer(cmbboxLecturer_,
					"create"));
			cmbboxLecturer_.setBounds(138, 120, 106, 20);
		}
		return cmbboxLecturer_;
	}

	public JTextField getTxtFaculity() {
		if (txtFaculity_ == null) {
			txtFaculity_ = new JTextField();
			txtFaculity_.setText("WI");
			txtFaculity_.setEnabled(false);
			txtFaculity_.setBounds(138, 70, 106, 20);
			txtFaculity_.setColumns(10);
		}
		return txtFaculity_;
	}

	public JTextField getTxtLevel() {
		if (txtLevel_ == null) {
			txtLevel_ = new JTextField();
			txtLevel_
					.setToolTipText("<html>Geben Sie das Hauptstockwerk des Lehrstuhls an</html>");
			txtLevel_.setBounds(138, 95, 106, 20);
			txtLevel_.setColumns(10);
		}
		return txtLevel_;
	}

	/**
	 * @return the chair
	 */
	public IntfChair getChair() {
		return chair_;
	}

	/**
	 * @param chair
	 *            - the chair to set
	 */
	public void setChair(IntfChair chair) {
		this.chair_ = chair;
	}
}
