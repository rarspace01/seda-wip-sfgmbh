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

import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.comlayer.core.model.CmbboxFilterKind;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.lecturer.controller.CourseDialogBtns;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Dialog to edit and create courses
 * 
 * @author hannes
 * @author christian
 *
 */
public class CourseDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblAcronym;
	private JLabel lblName;
	private JLabel lblKind;
	private JLabel lblSws;
	private JLabel lblAttendees;
	private JLabel lblLecturer;
	private JLabel lblDescription;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtAcronym;
	private JTextField txtName;
	private JComboBox<String> cmbboxKind;
	private JComboBox<String> cmbboxLecturer;
	private JTextField txtSws;
	private JTextField txtAttendees;
	private JScrollPane scrollPane;
	private JEditorPane editDescription;
	private String variant;
	private JCheckBox chckbxPublic;
	private Course course;

	/**
	 * Create the dialog.
	 */
	public CourseDialog() {
		setTitle("Lehrveranstaltung hinzufügen");
		this.variant = "default";
		this.course = new Course();
		this.init();
	}
	
	/**
	 * Create the dialog based on a variant
	 * @param variant
	 * @param course
	 */
	public CourseDialog(String variant, Course course) {
		this.variant = variant;
		this.course = course;
		this.init();
	}
	
	private void init() {
		if (this.variant.equals("edit")) {
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
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblAcronym());
		contentPanel.add(getLblName());
		contentPanel.add(getLblKind());
		contentPanel.add(getLblSws());
		contentPanel.add(getLblAttendees());
		contentPanel.add(getLblLecturer());
		contentPanel.add(getLblDescription());
		contentPanel.add(getBtnCancel());
		contentPanel.add(getBtnSave());
		contentPanel.add(getTxtAcronym());
		contentPanel.add(getTxtName());
		contentPanel.add(getCmbboxKind());
		contentPanel.add(getCmbboxLecturer());
		contentPanel.add(getTxtSws());
		contentPanel.add(getTxtAttendees());
		contentPanel.add(getScrollPane());
		contentPanel.add(getChckbxPublic());
		setLocationRelativeTo(null);
		
		// Customize if there is an old course and we are in edit mode
		if (this.variant.equals("edit")) {
			this.getTxtAcronym().setText(this.course.getCourseAcronym_());
			this.getTxtName().setText(this.course.getCourseName_());
			this.getCmbboxKind().setSelectedItem(this.course.getCourseKind_());
			this.getCmbboxLecturer().setSelectedItem(this.course.getLecturer_().getlName_());
			this.getTxtSws().setText(Float.toString(this.course.getSws_()));
			this.getChckbxPublic().setSelected(this.course.isLecturerEnabled_());
			this.getTxtAttendees().setText(String.valueOf(this.course.getExpectedAttendees_()));
			this.getEditDescription().setText(this.course.getCourseDescription_());
		}
	}
	/**
	 * 
	 * @return the lblAcronym
	 */
	public JLabel getLblAcronym() {
		if (lblAcronym == null) {
			lblAcronym = new JLabel("Kurzbezeichnung:");
			lblAcronym.setBounds(22, 22, 106, 14);
		}
		return lblAcronym;
	}
	/**
	 * 
	 * @return the lblName
	 */
	public JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Langer Name:");
			lblName.setBounds(22, 47, 84, 14);
		}
		return lblName;
	}
	/**
	 * 
	 * @return the lblKind
	 */
	public JLabel getLblKind() {
		if (lblKind == null) {
			lblKind = new JLabel("Art:");
			lblKind.setBounds(22, 72, 46, 14);
		}
		return lblKind;
	}
	/**
	 * 
	 * @return the lblSws
	 */
	public JLabel getLblSws() {
		if (lblSws == null) {
			lblSws = new JLabel("SWS:");
			lblSws.setBounds(22, 97, 46, 14);
		}
		return lblSws;
	}
	/**
	 * 
	 * @return the lblAttendees
	 */
	public JLabel getLblAttendees() {
		if (lblAttendees == null) {
			lblAttendees = new JLabel("Erw. Teilnehmer:");
			lblAttendees.setBounds(22, 122, 84, 14);
		}
		return lblAttendees;
	}
	/**
	 * 
	 * @return the lblLecturer
	 */
	public JLabel getLblLecturer() {
		if (lblLecturer == null) {
			lblLecturer = new JLabel("Dozent:");
			lblLecturer.setBounds(22, 147, 46, 14);
		}
		return lblLecturer;
	}
	/**
	 * 
	 * @return the lblDescription
	 */
	public JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Beschreibung:");
			lblDescription.setBounds(22, 172, 84, 14);
		}
		return lblDescription;
	}
	/**
	 * 
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Abbrechen");
			btnCancel.addActionListener(new CourseDialogBtns(this, "cancel"));
			btnCancel.setBounds(56, 284, 89, 23);
		}
		return btnCancel;
	}
	/**
	 * 
	 * @return the btnSave
	 */
	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Speichern");
			btnSave.addActionListener(new CourseDialogBtns(this, "save"));
			btnSave.setBounds(155, 284, 89, 23);
		}
		return btnSave;
	}
	/**
	 * 
	 * @return the txtAcronym
	 */
	public JTextField getTxtAcronym() {
		if (txtAcronym == null) {
			txtAcronym = new JTextField();
			txtAcronym.setToolTipText("geben Sie eine gewünschte Kurzbezeichnung ein");
			txtAcronym.setBounds(138, 19, 106, 20);
			txtAcronym.setColumns(10);
		}
		return txtAcronym;
	}
	/**
	 * 
	 * @return the txtName
	 */
	public JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setToolTipText("geben Sie eine gewünschte Bezeichnung ein");
			txtName.setBounds(138, 44, 106, 20);
			txtName.setColumns(10);
		}
		return txtName;
	}
	/**
	 * 
	 * @return the cmbboxKind
	 */
	public JComboBox<String> getCmbboxKind() {
		if (cmbboxKind == null) {
			cmbboxKind = new JComboBox<String>();
			cmbboxKind.setModel(new CmbboxFilterKind("select"));
			cmbboxKind.setBounds(138, 69, 106, 20);
		}
		return cmbboxKind;
	}
	/**
	 * 
	 * @return the cmbboxLecturer
	 */
	public JComboBox<String> getCmbboxLecturer() {
		if (cmbboxLecturer == null) {
			cmbboxLecturer = new JComboBox<String>();
			cmbboxLecturer.setToolTipText("wählen Sie den betreffenden Dozenten für die Lehrveranstaltung aus");
			cmbboxLecturer.setModel(new CmbboxFilterLecturer(cmbboxLecturer, "select"));
			cmbboxLecturer.setBounds(138, 144, 106, 20);
		}
		return cmbboxLecturer;
	}
	/**
	 * 
	 * @return the txtSws
	 */
	public JTextField getTxtSws() {
		if (txtSws == null) {
			txtSws = new JTextField();
			txtSws.setToolTipText("geben Sie die gesamten Semesterwochenstunden an");
			txtSws.setBounds(138, 94, 106, 20);
			txtSws.setColumns(10);
		}
		return txtSws;
	}
	/**
	 * 
	 * @return the txtAttendees
	 */
	public JTextField getTxtAttendees() {
		if (txtAttendees == null) {
			txtAttendees = new JTextField();
			txtAttendees.setToolTipText("Geben Sie die erwartete Gesamtanzahl der Teilnehmer ein");
			txtAttendees.setBounds(138, 119, 106, 20);
			txtAttendees.setColumns(10);
		}
		return txtAttendees;
	}
	/**
	 * 
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 197, 224, 50);
			scrollPane.setViewportView(getEditDescription());
		}
		return scrollPane;
	}
	/**
	 * 
	 * @return the editDescription
	 */
	public JEditorPane getEditDescription() {
		if (editDescription == null) {
			editDescription = new JEditorPane();
			editDescription.setToolTipText("geben Sie für die Studenten eine Beschreibung der Lehrveranstaltung ein");
		}
		return editDescription;
	}
	/**
	 * 
	 * @return the chckbxPublic
	 */
	public JCheckBox getChckbxPublic() {
		if (chckbxPublic == null) {
			chckbxPublic = new JCheckBox("öffentlich (falls von Verwaltung freigegeben)");
			chckbxPublic.setBounds(16, 254, 244, 23);
		}
		return chckbxPublic;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
}
