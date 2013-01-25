package de.sfgmbh.comlayer.lecturer.views;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.comlayer.core.model.CmbboxFilterKind;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.lecturer.controller.CourseDialogBtns;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Dialog to edit and create courses
 * 
 * @author christian
 *
 */
public class CourseDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel_ = new JPanel();
	private JLabel lblAcronym_;
	private JLabel lblName_;
	private JLabel lblKind_;
	private JLabel lblSws_;
	private JLabel lblAttendees_;
	private JLabel lblLecturer_;
	private JLabel lblDescription_;
	private JButton btnCancel_;
	private JButton btnSave_;
	private JTextField txtAcronym_;
	private JTextField txtName_;
	private JComboBox<String> cmbboxKind_;
	private JComboBox<String> cmbboxLecturer_;
	private JTextField txtSws_;
	private JTextField txtAttendees_;
	private JScrollPane scrollPane_;
	private JEditorPane editDescription_;
	private String variant_;
	private JCheckBox chckbxPublic_;
	private IntfCourse course_;

	/**
	 * Create the dialog.
	 */
	public CourseDialog() {
		setTitle("Lehrveranstaltung hinzufügen");
		this.variant_ = "default";
		this.course_ = new Course();
		this.init();
	}
	
	/**
	 * Create the dialog based on a variant
	 * @param variant
	 * @param course
	 */
	public CourseDialog(String variant, IntfCourse course) {
		this.variant_ = variant;
		this.course_ = course;
		this.init();
	}
	
	private void init() {
		if (this.variant_.equals("edit")) {
			setTitle("Veranstaltung bearbeiten");
		} else {
			setTitle("Veranstaltung hinzufügen");
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(CounterproposalDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 283, 362);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel_.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel_, BorderLayout.CENTER);
		contentPanel_.setLayout(null);
		contentPanel_.add(getLblAcronym());
		contentPanel_.add(getLblName());
		contentPanel_.add(getLblKind());
		contentPanel_.add(getLblSws());
		contentPanel_.add(getLblAttendees());
		contentPanel_.add(getLblLecturer());
		contentPanel_.add(getLblDescription());
		contentPanel_.add(getBtnCancel());
		contentPanel_.add(getBtnSave());
		contentPanel_.add(getTxtAcronym());
		contentPanel_.add(getTxtName());
		contentPanel_.add(getCmbboxKind());
		contentPanel_.add(getCmbboxLecturer());
		contentPanel_.add(getTxtSws());
		contentPanel_.add(getTxtAttendees());
		contentPanel_.add(getScrollPane());
		contentPanel_.add(getChckbxPublic());
		setLocationRelativeTo(null);
		
		// Customize if there is an old course and we are in edit mode
		if (this.variant_.equals("edit")) {
			this.getTxtAcronym().setText(this.course_.getCourseAcronym_());
			this.getTxtName().setText(this.course_.getCourseName_());
			this.getCmbboxKind().setSelectedItem(this.course_.getCourseKind_());
			this.getCmbboxLecturer().setSelectedItem(this.course_.getLecturer_().getlName_());
			this.getTxtSws().setText(Float.toString(this.course_.getSws_()));
			this.getChckbxPublic().setSelected(this.course_.isLecturerEnabled_());
			this.getTxtAttendees().setText(String.valueOf(this.course_.getExpectedAttendees_()));
			this.getEditDescription().setText(this.course_.getCourseDescription_());
		}
	}
	/**
	 * 
	 * @return the lblAcronym
	 */
	public JLabel getLblAcronym() {
		if (lblAcronym_ == null) {
			lblAcronym_ = new JLabel("Kurzbezeichnung:");
			lblAcronym_.setBounds(22, 22, 106, 14);
		}
		return lblAcronym_;
	}
	/**
	 * 
	 * @return the lblName
	 */
	public JLabel getLblName() {
		if (lblName_ == null) {
			lblName_ = new JLabel("Langer Name:");
			lblName_.setBounds(22, 47, 84, 14);
		}
		return lblName_;
	}
	/**
	 * 
	 * @return the lblKind
	 */
	public JLabel getLblKind() {
		if (lblKind_ == null) {
			lblKind_ = new JLabel("Art:");
			lblKind_.setBounds(22, 72, 46, 14);
		}
		return lblKind_;
	}
	/**
	 * 
	 * @return the lblSws
	 */
	public JLabel getLblSws() {
		if (lblSws_ == null) {
			lblSws_ = new JLabel("SWS:");
			lblSws_.setBounds(22, 97, 46, 14);
		}
		return lblSws_;
	}
	/**
	 * 
	 * @return the lblAttendees
	 */
	public JLabel getLblAttendees() {
		if (lblAttendees_ == null) {
			lblAttendees_ = new JLabel("Erw. Teilnehmer:");
			lblAttendees_.setBounds(22, 122, 84, 14);
		}
		return lblAttendees_;
	}
	/**
	 * 
	 * @return the lblLecturer
	 */
	public JLabel getLblLecturer() {
		if (lblLecturer_ == null) {
			lblLecturer_ = new JLabel("Dozent:");
			lblLecturer_.setBounds(22, 147, 46, 14);
		}
		return lblLecturer_;
	}
	/**
	 * 
	 * @return the lblDescription
	 */
	public JLabel getLblDescription() {
		if (lblDescription_ == null) {
			lblDescription_ = new JLabel("Beschreibung:");
			lblDescription_.setBounds(22, 172, 84, 14);
		}
		return lblDescription_;
	}
	/**
	 * 
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		if (btnCancel_ == null) {
			btnCancel_ = new JButton("Abbrechen");
			btnCancel_.setToolTipText("<html>Wenn Sie den Vorgang abbrechen wollen</html>");
			btnCancel_.addActionListener(new CourseDialogBtns(this, "cancel"));
			btnCancel_.setBounds(56, 284, 89, 23);
		}
		return btnCancel_;
	}
	/**
	 * 
	 * @return the btnSave
	 */
	public JButton getBtnSave() {
		if (btnSave_ == null) {
			btnSave_ = new JButton("Speichern");
			btnSave_.setToolTipText("<html>Klicken Sie hier, um<br> Ihre Eingaben zu speichern</html>");
			btnSave_.addActionListener(new CourseDialogBtns(this, "save"));
			btnSave_.setBounds(155, 284, 89, 23);
		}
		return btnSave_;
	}
	/**
	 * 
	 * @return the txtAcronym
	 */
	public JTextField getTxtAcronym() {
		if (txtAcronym_ == null) {
			txtAcronym_ = new JTextField();
			txtAcronym_.setToolTipText("<html>Bitte geben Sie eine <br>Kurzbezeichnung gemäß des<br> Lehrstuhlacronyms, <br>des Kürzels der Lehrveranstaltung sowie <br> des Abschlusses in folgender Form: ABCD-XYZ-M oder ABCD-XYZ-B</html>");
			txtAcronym_.setBounds(138, 19, 106, 20);
			txtAcronym_.setColumns(10);
		}
		return txtAcronym_;
	}
	/**
	 * 
	 * @return the txtName
	 */
	public JTextField getTxtName() {
		if (txtName_ == null) {
			txtName_ = new JTextField();
			txtName_.setToolTipText("<html>Geben sie den Veranstaltungsnamen ausgeschrieben an</hmtl>");
			txtName_.setBounds(138, 44, 106, 20);
			txtName_.setColumns(10);
		}
		return txtName_;
	}
	/**
	 * 
	 * @return the cmbboxKind
	 */
	public JComboBox<String> getCmbboxKind() {
		if (cmbboxKind_ == null) {
			cmbboxKind_ = new JComboBox<String>();
			cmbboxKind_.setModel(new CmbboxFilterKind("select"));
			cmbboxKind_.setBounds(138, 69, 106, 20);
		}
		return cmbboxKind_;
	}
	/**
	 * 
	 * @return the cmbboxLecturer
	 */
	public JComboBox<String> getCmbboxLecturer() {
		if (cmbboxLecturer_ == null) {
			cmbboxLecturer_ = new JComboBox<String>();
			cmbboxLecturer_.setToolTipText("<html>Wählen Sie den betreffenden <br>Dozenten für die Lehrveranstaltung aus</html>");
			cmbboxLecturer_.setModel(new CmbboxFilterLecturer(cmbboxLecturer_, "select", true));
			cmbboxLecturer_.setBounds(138, 144, 106, 20);
		}
		return cmbboxLecturer_;
	}
	/**
	 * 
	 * @return the txtSws
	 */
	public JTextField getTxtSws() {
		if (txtSws_ == null) {
			txtSws_ = new JTextField();
			txtSws_.setToolTipText("<html>Geben Sie die gesamten<br> Umfang der Lehrveranstaltung in <br>Semesterwochenstunden an</html>");
			txtSws_.setBounds(138, 94, 106, 20);
			txtSws_.setColumns(10);
		}
		return txtSws_;
	}
	/**
	 * 
	 * @return the txtAttendees
	 */
	public JTextField getTxtAttendees() {
		if (txtAttendees_ == null) {
			txtAttendees_ = new JTextField();
			txtAttendees_.setToolTipText("Geben Sie die erwartete Gesamtanzahl der Teilnehmer ein");
			txtAttendees_.setBounds(138, 119, 106, 20);
			txtAttendees_.setColumns(10);
		}
		return txtAttendees_;
	}
	/**
	 * 
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		if (scrollPane_ == null) {
			scrollPane_ = new JScrollPane();
			scrollPane_.setBounds(22, 197, 224, 50);
			scrollPane_.setViewportView(getEditDescription());
		}
		return scrollPane_;
	}
	/**
	 * 
	 * @return the editDescription
	 */
	public JEditorPane getEditDescription() {
		if (editDescription_ == null) {
			editDescription_ = new JEditorPane();
			editDescription_.setToolTipText("<html>Geben Sie für die Studenten eine Beschreibung der Lehrveranstaltung ein</html>");
		}
		return editDescription_;
	}
	/**
	 * 
	 * @return the chckbxPublic
	 */
	public JCheckBox getChckbxPublic() {
		if (chckbxPublic_ == null) {
			chckbxPublic_ = new JCheckBox("öffentlich (falls von Verwaltung freigegeben)");
			chckbxPublic_.setToolTipText("<html>Wählen Sie hier, <br>wenn Sie im Anschluss an <br> eine erfolgreiche Raumanfrage <br> die Lehrveranstaltung automatisch<br>veröffentlichen wollen</html>");
			chckbxPublic_.setBounds(16, 254, 244, 23);
		}
		return chckbxPublic_;
	}

	/**
	 * @return the course
	 */
	public IntfCourse getCourse() {
		return course_;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(IntfCourse course) {
		this.course_ = course;
	}
}
