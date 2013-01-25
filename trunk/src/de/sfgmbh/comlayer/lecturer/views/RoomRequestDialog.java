package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.core.model.CmbboxFilterDay;
import de.sfgmbh.comlayer.core.model.CmbboxFilterRoomnumber;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.model.CmbboxFilterTime;
import de.sfgmbh.comlayer.lecturer.controller.RoomRequestDialogBtns;

/**
 * Modal Dialog for room requests
 * 
 * @author anna
 * @author hannes
 * 
 */
public class RoomRequestDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_;
	private JTextArea txtMessageForCoutnerProposal_;
	private IntfRoomAllocation proposalAllocation_;
	private IntfCourse course_;
	private JLabel lblRoom_;
	private JLabel lblDay_;
	private JLabel lblTime_;
	private JComboBox<String> cmbboxRoom_;
	private JComboBox<String> cmbboxDay_;
	private JComboBox<String> cmbboxTime_;
	private JLabel lblStatus_;
	private JLabel lblSelectedStatus_;
	private IntfCtrlStartTab ctrlStartTab_ = new CtrlStartTab();
	private JTextField txtSeats_;
	private JTextField txtPcs_;
	private JTextField txtBeamer_;
	private JTextField txtVisual_;
	private JTextField txtOverhead_;
	private JTextField txtBoard_;
	private JTextField txtWhiteboard_;
	private JButton btnNew_;
	private JComboBox<String> cmbboxSemester_;
	private JLabel lblSem_;
	private JLabel lblSeats_;
	private JLabel lblPcs_;
	private JLabel lblBeamer_;
	private JLabel lblVisual_;
	private JLabel lblOverhead_;
	private JLabel lblBoards_;
	private JLabel lblWhiteboard_;
	private JLabel lblCountSeats_;
	private JLabel lblCountPcs_;
	private JLabel lblCountBeamer_;
	private JLabel lblCountVisual_;
	private JLabel lblCountOverheads_;
	private JLabel lblCountBoards_;
	private JLabel lblCountWhiteboards_;
	private JTextArea txtProposeWish_;

	/**
	 * Create the frame.
	 */
	public RoomRequestDialog(IntfCourse course) {
		setTitle("Raumanfrage erstellen");
		// Create the filter
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("seats", String.valueOf(course.getExpectedAttendees_()));

		// Create a first new room allocation object (default semester currently
		// hard coded - this should be changed in the following versions)
		IntfRoomAllocation roomAllocation = new RoomAllocation();
		roomAllocation.setCourse_(course);
		// Set an empty room to avoid null pointer exceptions when the
		// recommender system cannot find a suiting room
		roomAllocation.setRoom_(new Room());
		roomAllocation.setSemester_("SS 13");
		this.proposalAllocation_ = roomAllocation;

		// Continue with the dialog
		setModal(true);
		initialize();
		setLocationRelativeTo(null);

		// Set some initial values for some dialog elements
		this.getTxtSeats().setText(
				String.valueOf(course.getExpectedAttendees_()));
		contentPane_.add(getLblCountSeats());
		contentPane_.add(getLblCountPcs());
		contentPane_.add(getLblCountBeamer());
		contentPane_.add(getLblCountVisual());
		contentPane_.add(getLblCountOverheads());
		contentPane_.add(getLblCountBoards());
		contentPane_.add(getLblCountWhiteboards());
		contentPane_.add(getTxtrLegenSieWunschgren());
		this.setSuggestRoomAllocation(roomAllocation, filter);
	}

	/**
	 * Customize according to a room allocation
	 * 
	 * @param roomAllocation
	 */
	public void setRoomAllocation(IntfRoomAllocation roomAllocation) {
		this.proposalAllocation_ = roomAllocation;
		this.proposalAllocation_.setForceConflictingAllocations_();

		// Stop action listener on comboboxes, set them and start action
		// listener again
		getCmbboxDay().removeActionListener(
				getCmbboxDay().getActionListeners()[0]);
		getCmbboxTime().removeActionListener(
				getCmbboxTime().getActionListeners()[0]);
		getCmbboxRoom().removeActionListener(
				getCmbboxRoom().getActionListeners()[0]);
		getCmbboxSemester().removeActionListener(
				getCmbboxSemester().getActionListeners()[0]);
		getCmbboxDay().setSelectedIndex(this.proposalAllocation_.getDay_() - 1);
		getCmbboxTime().setSelectedIndex(
				this.proposalAllocation_.getTime_() - 1);
		getCmbboxRoom().setSelectedItem(
				this.proposalAllocation_.getRoom_().getRoomNumber_());
		getCmbboxSemester().setSelectedItem(
				this.proposalAllocation_.getSemester_());
		getCmbboxDay().addActionListener(
				new RoomRequestDialogBtns(this, "combo"));
		getCmbboxTime().addActionListener(
				new RoomRequestDialogBtns(this, "combo"));
		getCmbboxRoom().addActionListener(
				new RoomRequestDialogBtns(this, "combo"));
		getCmbboxSemester().addActionListener(
				new RoomRequestDialogBtns(this, "combo"));

		// Fill in the stats of the current room
		getLblCountSeats()
				.setText(
						String.valueOf(this.proposalAllocation_.getRoom_()
								.getSeats_()));
		getLblCountBeamer()
				.setText(
						String.valueOf(this.proposalAllocation_.getRoom_()
								.getBeamer_()));
		getLblCountBoards().setText(
				String.valueOf(this.proposalAllocation_.getRoom_()
						.getChalkboards_()));
		getLblCountOverheads().setText(
				String.valueOf(this.proposalAllocation_.getRoom_()
						.getOverheads_()));
		getLblCountPcs().setText(
				String.valueOf(this.proposalAllocation_.getRoom_()
						.getPcseats_()));
		getLblCountVisual().setText(
				String.valueOf(this.proposalAllocation_.getRoom_()
						.getVisualizer_()));
		getLblCountWhiteboards().setText(
				String.valueOf(this.proposalAllocation_.getRoom_()
						.getWhiteboards_()));

		// Check status
		if (this.proposalAllocation_.getConflictingAllocations_().isEmpty()) {
			getLblSelectedStatus().setText("frei");
		} else {
			getLblSelectedStatus().setText("belegt");
		}
	}

	/**
	 * Customize according to a suggested room allocation
	 * 
	 * @param roomAllocation
	 */
	public void setSuggestRoomAllocation(IntfRoomAllocation roomAllocation,
			HashMap<String, String> filter) {
		IntfRoomAllocation suggestRoomAllocation = ctrlStartTab_.suggest(
				roomAllocation, filter);
		if (suggestRoomAllocation == null) {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Scheinbar gibt es für diese Veranstaltung dieses Semester keinen freien Raum mehr, der groß genug wäre.<br />",
							"Information", "info");
			this.setRoomAllocation(roomAllocation);
		} else {
			this.setRoomAllocation(suggestRoomAllocation);
		}
	}

	private void initialize() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						RoomRequestDialog.class
								.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setBounds(100, 100, 373, 462);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				Color.WHITE, Color.LIGHT_GRAY));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JButton btnSenden = new JButton("Senden");
		btnSenden
				.setToolTipText("<html>Schicken Sie Ihre Raumanfrage mit dem gewünschten Raum an die Verwaltung</html>");
		btnSenden.setBounds(177, 385, 90, 23);
		btnSenden.addActionListener(new RoomRequestDialogBtns(this, "send"));
		contentPane_.add(btnSenden);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen
				.setToolTipText("<html>Geben Sie für die Studenten eine Beschreibung der Lehrveranstaltung ein</html>");
		btnAbbrechen
				.addActionListener(new RoomRequestDialogBtns(this, "cancel"));
		btnAbbrechen.setBounds(77, 385, 90, 23);
		contentPane_.add(btnAbbrechen);
		contentPane_.add(getTxtrUnterbreitenSieDem());
		contentPane_.add(getLblRoom());
		contentPane_.add(getLblDay());
		contentPane_.add(getLblTime());
		contentPane_.add(getCmbboxRoom());
		contentPane_.add(getCmbboxDay());
		contentPane_.add(getCmbboxTime());
		contentPane_.add(getLblStatus());
		contentPane_.add(getLblSelectedStatus());
		contentPane_.add(getTxtSeats());
		contentPane_.add(getTxtPcs());
		contentPane_.add(getTxtBeamer());
		contentPane_.add(getTxtVisual());
		contentPane_.add(getTxtOverhead());
		contentPane_.add(getTxtBoard());
		contentPane_.add(getTxtWhiteboard());
		contentPane_.add(getBtnNew());
		contentPane_.add(getCmbboxSemester());
		contentPane_.add(getLblSem());
		contentPane_.add(getLblSeats());
		contentPane_.add(getLblPcs());
		contentPane_.add(getLblBeamer());
		contentPane_.add(getLblVisual());
		contentPane_.add(getLblOverhead());
		contentPane_.add(getLblBoards());
		contentPane_.add(getLblWhiteboard());

	}

	public JTextArea getTxtrUnterbreitenSieDem() {
		if (txtMessageForCoutnerProposal_ == null) {
			txtMessageForCoutnerProposal_ = new JTextArea();
			txtMessageForCoutnerProposal_.setFont(new Font("Tahoma",
					Font.PLAIN, 11));
			txtMessageForCoutnerProposal_.setOpaque(false);
			txtMessageForCoutnerProposal_.setEditable(false);
			txtMessageForCoutnerProposal_.setWrapStyleWord(true);
			txtMessageForCoutnerProposal_.setLineWrap(true);
			txtMessageForCoutnerProposal_
					.setText("Suchen Sie einen passenden Raum und Termin für die Veranstaltung "
							+ this.proposalAllocation_.getCourse_()
									.getCourseAcronym_() + ".");
			txtMessageForCoutnerProposal_.setBounds(10, 11, 328, 35);
		}
		return txtMessageForCoutnerProposal_;
	}

	public JLabel getLblRoom() {
		if (lblRoom_ == null) {
			lblRoom_ = new JLabel("Raum:");
			lblRoom_.setBounds(83, 50, 46, 14);
		}
		return lblRoom_;
	}

	public JLabel getLblDay() {
		if (lblDay_ == null) {
			lblDay_ = new JLabel("Tag:");
			lblDay_.setBounds(83, 95, 46, 14);
		}
		return lblDay_;
	}

	public JLabel getLblTime() {
		if (lblTime_ == null) {
			lblTime_ = new JLabel("Zeit:");
			lblTime_.setBounds(83, 120, 46, 14);
		}
		return lblTime_;
	}

	public JComboBox<String> getCmbboxRoom() {
		if (cmbboxRoom_ == null) {
			cmbboxRoom_ = new JComboBox<String>();
			cmbboxRoom_
					.setToolTipText("<html>Wählen Sie einen Wunschraum</html>");
			cmbboxRoom_.setModel(new CmbboxFilterRoomnumber(cmbboxRoom_,
					"select"));
			cmbboxRoom_.setEditable(true);
			cmbboxRoom_.addActionListener(new RoomRequestDialogBtns(this,
					"combo"));
			cmbboxRoom_.setBounds(139, 47, 102, 20);
		}
		return cmbboxRoom_;
	}

	public JComboBox<String> getCmbboxDay() {
		if (cmbboxDay_ == null) {
			cmbboxDay_ = new JComboBox<String>();
			cmbboxDay_
					.setToolTipText("<html>Wählen Sie einen Wunschtag</html>");
			cmbboxDay_.setModel(new CmbboxFilterDay("select"));
			cmbboxDay_.addActionListener(new RoomRequestDialogBtns(this,
					"combo"));
			cmbboxDay_.setBounds(139, 95, 102, 20);
		}
		return cmbboxDay_;
	}

	public JComboBox<String> getCmbboxTime() {
		if (cmbboxTime_ == null) {
			cmbboxTime_ = new JComboBox<String>();
			cmbboxTime_
					.setToolTipText("<html>Wählen Sie einen Wunschzeit</html>");
			cmbboxTime_.setModel(new CmbboxFilterTime("select"));
			cmbboxTime_.addActionListener(new RoomRequestDialogBtns(this,
					"combo"));
			cmbboxTime_.setBounds(139, 120, 102, 20);
		}
		return cmbboxTime_;
	}

	public JLabel getLblStatus() {
		if (lblStatus_ == null) {
			lblStatus_ = new JLabel("Belegung zur gewählten Zeit:");
			lblStatus_.setBounds(60, 153, 146, 14);
		}
		return lblStatus_;
	}

	public JLabel getLblSelectedStatus() {
		if (lblSelectedStatus_ == null) {
			lblSelectedStatus_ = new JLabel("n/a");
			lblSelectedStatus_.setBounds(249, 153, 46, 14);
		}
		return lblSelectedStatus_;
	}

	/**
	 * @return the proposalAllocation
	 */
	public IntfRoomAllocation getProposalAllocation() {
		return proposalAllocation_;
	}

	/**
	 * @param proposalAllocation
	 *            the proposalAllocation to set
	 */
	public void setProposalAllocation(IntfRoomAllocation proposalAllocation) {
		this.proposalAllocation_ = proposalAllocation;
	}

	public JTextField getTxtSeats() {
		if (txtSeats_ == null) {
			txtSeats_ = new JTextField();
			txtSeats_
					.setToolTipText("Geben Sie die Anzahl der Sitzplätze für die Studenten an");
			txtSeats_.setBounds(91, 224, 46, 20);
			txtSeats_.setColumns(10);
		}
		return txtSeats_;
	}

	public JTextField getTxtPcs() {
		if (txtPcs_ == null) {
			txtPcs_ = new JTextField();
			txtPcs_.setToolTipText("Geben Sie die benötigte Anzahl von PC-Arbeitsplätzen an");
			txtPcs_.setText("0");
			txtPcs_.setColumns(10);
			txtPcs_.setBounds(91, 255, 46, 20);
		}
		return txtPcs_;
	}

	public JTextField getTxtBeamer() {
		if (txtBeamer_ == null) {
			txtBeamer_ = new JTextField();
			txtBeamer_
					.setToolTipText("Geben Sie die benötigte Anzahl von Beamern an");
			txtBeamer_.setText("0");
			txtBeamer_.setColumns(10);
			txtBeamer_.setBounds(91, 286, 46, 20);
		}
		return txtBeamer_;
	}

	public JTextField getTxtVisual() {
		if (txtVisual_ == null) {
			txtVisual_ = new JTextField();
			txtVisual_
					.setToolTipText("Geben Sie die benötigte Anzahl von Visualizer an");
			txtVisual_.setText("0");
			txtVisual_.setColumns(10);
			txtVisual_.setBounds(91, 316, 46, 20);
		}
		return txtVisual_;
	}

	public JTextField getTxtOverhead() {
		if (txtOverhead_ == null) {
			txtOverhead_ = new JTextField();
			txtOverhead_
					.setToolTipText("Geben Sie die benötigte Anzahl von Overheads an");
			txtOverhead_.setText("0");
			txtOverhead_.setColumns(10);
			txtOverhead_.setBounds(255, 224, 46, 20);
		}
		return txtOverhead_;
	}

	public JTextField getTxtBoard() {
		if (txtBoard_ == null) {
			txtBoard_ = new JTextField();
			txtBoard_
					.setToolTipText("Geben Sie die benötigte Anzahl von Tafeln an");
			txtBoard_.setText("0");
			txtBoard_.setColumns(10);
			txtBoard_.setBounds(255, 255, 46, 20);
		}
		return txtBoard_;
	}

	public JTextField getTxtWhiteboard() {
		if (txtWhiteboard_ == null) {
			txtWhiteboard_ = new JTextField();
			txtWhiteboard_
					.setToolTipText("Geben Sie die benötigte Anzahl von Whiteboards an");
			txtWhiteboard_.setText("0");
			txtWhiteboard_.setColumns(10);
			txtWhiteboard_.setBounds(255, 286, 46, 20);
		}
		return txtWhiteboard_;
	}

	public JButton getBtnNew() {
		if (btnNew_ == null) {
			btnNew_ = new JButton("Vorschlag");
			btnNew_.setToolTipText("<html>Geben Sie Ihre Wunschgrößen ein <br> und Klicken Sie hier, um sich einen passenden <br>Raum oben anzeigen zu lassen</html>");
			btnNew_.addActionListener(new RoomRequestDialogBtns(this,
					"newSuggestion"));
			btnNew_.setBounds(230, 316, 90, 23);
		}
		return btnNew_;
	}

	public JComboBox<String> getCmbboxSemester() {
		if (cmbboxSemester_ == null) {
			cmbboxSemester_ = new JComboBox<String>();
			cmbboxSemester_
					.setToolTipText("<html>Wählen Sie einen Wunschsemester</html>");
			cmbboxSemester_.setModel(new CmbboxFilterSemester("select"));
			cmbboxSemester_.addActionListener(new RoomRequestDialogBtns(this,
					"combo"));
			cmbboxSemester_.setBounds(139, 71, 102, 20);
		}
		return cmbboxSemester_;
	}

	public JLabel getLblSem() {
		if (lblSem_ == null) {
			lblSem_ = new JLabel("Semester:");
			lblSem_.setBounds(83, 74, 58, 14);
		}
		return lblSem_;
	}

	public JLabel getLblSeats() {
		if (lblSeats_ == null) {
			lblSeats_ = new JLabel("Sitze:");
			lblSeats_.setBounds(35, 227, 46, 14);
		}
		return lblSeats_;
	}

	public JLabel getLblPcs() {
		if (lblPcs_ == null) {
			lblPcs_ = new JLabel("PC-Plätze:");
			lblPcs_.setBounds(35, 258, 58, 14);
		}
		return lblPcs_;
	}

	public JLabel getLblBeamer() {
		if (lblBeamer_ == null) {
			lblBeamer_ = new JLabel("Beamer:");
			lblBeamer_.setBounds(34, 289, 46, 14);
		}
		return lblBeamer_;
	}

	public JLabel getLblVisual() {
		if (lblVisual_ == null) {
			lblVisual_ = new JLabel("Visualizer:");
			lblVisual_.setBounds(34, 319, 58, 14);
		}
		return lblVisual_;
	}

	public JLabel getLblOverhead() {
		if (lblOverhead_ == null) {
			lblOverhead_ = new JLabel("Overheads:");
			lblOverhead_.setBounds(188, 227, 58, 14);
		}
		return lblOverhead_;
	}

	public JLabel getLblBoards() {
		if (lblBoards_ == null) {
			lblBoards_ = new JLabel("Tafeln:");
			lblBoards_.setBounds(188, 258, 46, 14);
		}
		return lblBoards_;
	}

	public JLabel getLblWhiteboard() {
		if (lblWhiteboard_ == null) {
			lblWhiteboard_ = new JLabel("Whiteboards:");
			lblWhiteboard_.setBounds(187, 289, 65, 14);
		}
		return lblWhiteboard_;
	}

	public IntfCourse getCourse() {
		return course_;
	}

	public void setCourse(IntfCourse course) {
		this.course_ = course;
	}

	public JLabel getLblCountSeats() {
		if (lblCountSeats_ == null) {
			lblCountSeats_ = new JLabel("n/a");
			lblCountSeats_.setBounds(147, 227, 27, 14);
		}
		return lblCountSeats_;
	}

	public JLabel getLblCountPcs() {
		if (lblCountPcs_ == null) {
			lblCountPcs_ = new JLabel("n/a");
			lblCountPcs_.setBounds(147, 258, 27, 14);
		}
		return lblCountPcs_;
	}

	public JLabel getLblCountBeamer() {
		if (lblCountBeamer_ == null) {
			lblCountBeamer_ = new JLabel("n/a");
			lblCountBeamer_.setBounds(147, 289, 27, 14);
		}
		return lblCountBeamer_;
	}

	public JLabel getLblCountVisual() {
		if (lblCountVisual_ == null) {
			lblCountVisual_ = new JLabel("n/a");
			lblCountVisual_.setBounds(147, 319, 27, 14);
		}
		return lblCountVisual_;
	}

	public JLabel getLblCountOverheads() {
		if (lblCountOverheads_ == null) {
			lblCountOverheads_ = new JLabel("n/a");
			lblCountOverheads_.setBounds(311, 227, 27, 14);
		}
		return lblCountOverheads_;
	}

	public JLabel getLblCountBoards() {
		if (lblCountBoards_ == null) {
			lblCountBoards_ = new JLabel("n/a");
			lblCountBoards_.setBounds(311, 258, 27, 14);
		}
		return lblCountBoards_;
	}

	public JLabel getLblCountWhiteboards() {
		if (lblCountWhiteboards_ == null) {
			lblCountWhiteboards_ = new JLabel("n/a");
			lblCountWhiteboards_.setBounds(311, 289, 27, 14);
		}
		return lblCountWhiteboards_;
	}

	public JTextArea getTxtrLegenSieWunschgren() {
		if (txtProposeWish_ == null) {
			txtProposeWish_ = new JTextArea();
			txtProposeWish_.setOpaque(false);
			txtProposeWish_.setFont(new Font("Tahoma", Font.ITALIC, 11));
			txtProposeWish_.setLineWrap(true);
			txtProposeWish_
					.setText("Legen sie Wunschgrößen fest und lassen Sie sich einen freien Raum vorschlagen (hinter Ihren Wunschgrößen sehen Sie die Größen des aktuellen Raumes):");
			txtProposeWish_.setBounds(10, 174, 328, 46);
		}
		return txtProposeWish_;
	}
}
