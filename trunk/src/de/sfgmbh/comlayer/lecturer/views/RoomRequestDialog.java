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
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogWin;

/**
 * Modal Dialog for room requests
 * 
 * @author anna
 * @author hannes
 *
 */
public class RoomRequestDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtrUnterbreitenSieDem;
	private IntfRoomAllocation proposalAllocation;
	private IntfCourse course;
	private JLabel lblRoom;
	private JLabel lblDay;
	private JLabel lblTime;
	private JComboBox<String> cmbboxRoom;
	private JComboBox<String> cmbboxDay;
	private JComboBox<String> cmbboxTime;
	private JLabel lblStatus;
	private JLabel lblSelectedStatus;
	private IntfCtrlStartTab ctrlStartTab = new CtrlStartTab();
	private JTextField txtSeats;
	private JTextField txtPcs;
	private JTextField txtBeamer;
	private JTextField txtVisual;
	private JTextField txtOverhead;
	private JTextField txtBoard;
	private JTextField txtWhiteboard;
	private JButton btnNew;
	private JComboBox<String> cmbboxSemester;
	private JLabel lblSem;
	private JLabel lblSeats;
	private JLabel lblPcs;
	private JLabel lblBeamer;
	private JLabel lblVisual;
	private JLabel lblOverhead;
	private JLabel lblBoards;
	private JLabel lblWhiteboard;
	private JLabel lblCountSeats;
	private JLabel lblCountPcs;
	private JLabel lblCountBeamer;
	private JLabel lblCountVisual;
	private JLabel lblCountOverheads;
	private JLabel lblCountBoards;
	private JLabel lblCountWhiteboards;
	private JTextArea txtrLegenSieWunschgren;

	/**
	 * Create the frame.
	 */
	public RoomRequestDialog(IntfCourse course) {
		setTitle("Raumanfrage erstellen");
		// Create the filter
		HashMap<String,String> filter = new HashMap<String,String>();
		filter.put("seats", String.valueOf(course.getExpectedAttendees_()));
		
		// Create a first new room allocation object (default semester currently hard coded - this should be changed in the following versions)
		IntfRoomAllocation roomAllocation = new RoomAllocation();
		roomAllocation.setCourse_(course);
		// Set an empty room to avoid null pointer exceptions when the recommender system cannot find a suiting room
		roomAllocation.setRoom_(new Room());
		roomAllocation.setSemester_("SS 13");
		this.proposalAllocation = roomAllocation;
		
		// Continue with the dialog
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
		
		// Set some initial values for some dialog elements
		this.getTxtSeats().setText(String.valueOf(course.getExpectedAttendees_()));
		contentPane.add(getLblCountSeats());
		contentPane.add(getLblCountPcs());
		contentPane.add(getLblCountBeamer());
		contentPane.add(getLblCountVisual());
		contentPane.add(getLblCountOverheads());
		contentPane.add(getLblCountBoards());
		contentPane.add(getLblCountWhiteboards());
		contentPane.add(getTxtrLegenSieWunschgren());
		this.setSuggestRoomAllocation(roomAllocation, filter);
	}
	
	/**
	 * Customize according to a room allocation
	 * @param roomAllocation
	 */
	public void setRoomAllocation(IntfRoomAllocation roomAllocation) {
		this.proposalAllocation = roomAllocation;
		this.proposalAllocation.setForceConflictingAllocations_();
		
		// Stop action listener on comboboxes, set them and start action listener again
		getCmbboxDay().removeActionListener(getCmbboxDay().getActionListeners()[0]);
		getCmbboxTime().removeActionListener(getCmbboxTime().getActionListeners()[0]);
		getCmbboxRoom().removeActionListener(getCmbboxRoom().getActionListeners()[0]);
		getCmbboxSemester().removeActionListener(getCmbboxSemester().getActionListeners()[0]);
		getCmbboxDay().setSelectedIndex(this.proposalAllocation.getDay_() - 1);
		getCmbboxTime().setSelectedIndex(this.proposalAllocation.getTime_() - 1);
		getCmbboxRoom().setSelectedItem(this.proposalAllocation.getRoom_().getRoomNumber_());
		getCmbboxSemester().setSelectedItem(this.proposalAllocation.getSemester_());
		getCmbboxDay().addActionListener(new RoomRequestDialogBtns(this, "combo"));
		getCmbboxTime().addActionListener(new RoomRequestDialogBtns(this, "combo"));
		getCmbboxRoom().addActionListener(new RoomRequestDialogBtns(this, "combo"));
		getCmbboxSemester().addActionListener(new RoomRequestDialogBtns(this, "combo"));
		
		// Fill in the stats of the current room
		getLblCountSeats().setText(String.valueOf(this.proposalAllocation.getRoom_().getSeats_()));
		getLblCountBeamer().setText(String.valueOf(this.proposalAllocation.getRoom_().getBeamer_()));
		getLblCountBoards().setText(String.valueOf(this.proposalAllocation.getRoom_().getChalkboards_()));
		getLblCountOverheads().setText(String.valueOf(this.proposalAllocation.getRoom_().getOverheads_()));
		getLblCountPcs().setText(String.valueOf(this.proposalAllocation.getRoom_().getPcseats_()));
		getLblCountVisual().setText(String.valueOf(this.proposalAllocation.getRoom_().getVisualizer_()));
		getLblCountWhiteboards().setText(String.valueOf(this.proposalAllocation.getRoom_().getWhiteboards_()));
		
		// Check status
		if (this.proposalAllocation.getConflictingAllocations_().isEmpty()) {
			getLblSelectedStatus().setText("frei");
		} else {
			getLblSelectedStatus().setText("belegt");
		}
	}
	
	/**
	 * Customize according to a suggested room allocation
	 * @param roomAllocation
	 */
	public void setSuggestRoomAllocation(IntfRoomAllocation roomAllocation, HashMap<String,String> filter) {
		IntfRoomAllocation suggestRoomAllocation = ctrlStartTab.suggest(roomAllocation, filter);
		if (suggestRoomAllocation == null) {
			AppModel.getInstance().getExceptionHandler().setNewException("Scheinbar gibt es für diese Veranstaltung dieses Semester keinen freien Raum mehr, der groß genug wäre.<br />Sie können allerdings noch versuchen einen Raum außerhalb der regulären Vorelsungszeiten zu belegen, da diese vom Vorschlagsystem nicht berücksichtigt wurden (ab 20 Uhr sowie Sa. und So.).", "Information", "info");
			this.setRoomAllocation(roomAllocation);
		} else {
			this.setRoomAllocation(suggestRoomAllocation);
		}
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoomRequestDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setBounds(100, 100, 373, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.setToolTipText("<html>Schicken Sie Ihre Raumanfrage mit dem gewünschten Raum an die Verwaltung</html>");
		btnSenden.setBounds(177, 385, 90, 28);
		btnSenden.addActionListener(new RoomRequestDialogBtns(this, "send"));
		contentPane.add(btnSenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new RoomRequestDialogBtns(this, "cancel"));
		btnAbbrechen.setBounds(77, 385, 90, 28);
		contentPane.add(btnAbbrechen);
		contentPane.add(getTxtrUnterbreitenSieDem());
		contentPane.add(getLblRoom());
		contentPane.add(getLblDay());
		contentPane.add(getLblTime());
		contentPane.add(getCmbboxRoom());
		contentPane.add(getCmbboxDay());
		contentPane.add(getCmbboxTime());
		contentPane.add(getLblStatus());
		contentPane.add(getLblSelectedStatus());
		contentPane.add(getTxtSeats());
		contentPane.add(getTxtPcs());
		contentPane.add(getTxtBeamer());
		contentPane.add(getTxtVisual());
		contentPane.add(getTxtOverhead());
		contentPane.add(getTxtBoard());
		contentPane.add(getTxtWhiteboard());
		contentPane.add(getBtnNew());
		contentPane.add(getCmbboxSemester());
		contentPane.add(getLblSem());
		contentPane.add(getLblSeats());
		contentPane.add(getLblPcs());
		contentPane.add(getLblBeamer());
		contentPane.add(getLblVisual());
		contentPane.add(getLblOverhead());
		contentPane.add(getLblBoards());
		contentPane.add(getLblWhiteboard());
		
		addWindowListener(new UserCreateDialogWin(this));
	}
	public JTextArea getTxtrUnterbreitenSieDem() {
		if (txtrUnterbreitenSieDem == null) {
			txtrUnterbreitenSieDem = new JTextArea();
			txtrUnterbreitenSieDem.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtrUnterbreitenSieDem.setOpaque(false);
			txtrUnterbreitenSieDem.setEditable(false);
			txtrUnterbreitenSieDem.setWrapStyleWord(true);
			txtrUnterbreitenSieDem.setLineWrap(true);
			txtrUnterbreitenSieDem.setText("Suchen Sie einen passenden Raum und Termin für die Veranstaltung " +
					this.proposalAllocation.getCourse_().getCourseAcronym_() +".");
			txtrUnterbreitenSieDem.setBounds(10, 11, 328, 35);
		}
		return txtrUnterbreitenSieDem;
	}
	public JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Raum:");
			lblRoom.setBounds(83, 50, 46, 14);
		}
		return lblRoom;
	}
	public JLabel getLblDay() {
		if (lblDay == null) {
			lblDay = new JLabel("Tag:");
			lblDay.setBounds(83, 95, 46, 14);
		}
		return lblDay;
	}
	public JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("Zeit:");
			lblTime.setBounds(83, 120, 46, 14);
		}
		return lblTime;
	}
	public JComboBox<String> getCmbboxRoom() {
		if (cmbboxRoom == null) {
			cmbboxRoom = new JComboBox<String>();
			cmbboxRoom.setToolTipText("Geben Sie einen gewünschten Raum direkt ein");
			cmbboxRoom.setModel(new CmbboxFilterRoomnumber(cmbboxRoom, "select"));
			cmbboxRoom.setEditable(true);
			cmbboxRoom.addActionListener(new RoomRequestDialogBtns(this, "combo"));
			cmbboxRoom.setBounds(139, 47, 102, 20);
		}
		return cmbboxRoom;
	}
	public JComboBox<String> getCmbboxDay() {
		if (cmbboxDay == null) {
			cmbboxDay = new JComboBox<String>();
			cmbboxDay.setToolTipText("Geben Sie den Tag der Lehrveranstaltung ein");
			cmbboxDay.setModel(new CmbboxFilterDay("select"));
			cmbboxDay.addActionListener(new RoomRequestDialogBtns(this, "combo"));
			cmbboxDay.setBounds(139, 95, 102, 20);
		}
		return cmbboxDay;
	}
	public JComboBox<String> getCmbboxTime() {
		if (cmbboxTime == null) {
			cmbboxTime = new JComboBox<String>();
			cmbboxTime.setToolTipText("Geben Sie die Uhrzeit der Lehrveranstaltung an");
			cmbboxTime.setModel(new CmbboxFilterTime("select"));
			cmbboxTime.addActionListener(new RoomRequestDialogBtns(this, "combo"));
			cmbboxTime.setBounds(139, 120, 102, 20);
		}
		return cmbboxTime;
	}
	public JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Belegung zur gewählten Zeit:");
			lblStatus.setBounds(60, 153, 146, 14);
		}
		return lblStatus;
	}
	public JLabel getLblSelectedStatus() {
		if (lblSelectedStatus == null) {
			lblSelectedStatus = new JLabel("n/a");
			lblSelectedStatus.setBounds(249, 153, 46, 14);
		}
		return lblSelectedStatus;
	}

	/**
	 * @return the proposalAllocation
	 */
	public IntfRoomAllocation getProposalAllocation() {
		return proposalAllocation;
	}

	/**
	 * @param proposalAllocation the proposalAllocation to set
	 */
	public void setProposalAllocation(IntfRoomAllocation proposalAllocation) {
		this.proposalAllocation = proposalAllocation;
	}
	public JTextField getTxtSeats() {
		if (txtSeats == null) {
			txtSeats = new JTextField();
			txtSeats.setToolTipText("Geben Sie die Anzahl der Sitzplätze für die Studenten an");
			txtSeats.setBounds(91, 224, 46, 20);
			txtSeats.setColumns(10);
		}
		return txtSeats;
	}
	public JTextField getTxtPcs() {
		if (txtPcs == null) {
			txtPcs = new JTextField();
			txtPcs.setToolTipText("Geben Sie die benötigte Anzahl von PC-Arbeitsplätzen an");
			txtPcs.setText("0");
			txtPcs.setColumns(10);
			txtPcs.setBounds(91, 255, 46, 20);
		}
		return txtPcs;
	}
	public JTextField getTxtBeamer() {
		if (txtBeamer == null) {
			txtBeamer = new JTextField();
			txtBeamer.setToolTipText("Geben Sie die benötigte Anzahl von Beamern an");
			txtBeamer.setText("0");
			txtBeamer.setColumns(10);
			txtBeamer.setBounds(91, 286, 46, 20);
		}
		return txtBeamer;
	}
	public JTextField getTxtVisual() {
		if (txtVisual == null) {
			txtVisual = new JTextField();
			txtVisual.setToolTipText("Geben Sie die benötigte Anzahl von Visualizer an");
			txtVisual.setText("0");
			txtVisual.setColumns(10);
			txtVisual.setBounds(91, 316, 46, 20);
		}
		return txtVisual;
	}
	public JTextField getTxtOverhead() {
		if (txtOverhead == null) {
			txtOverhead = new JTextField();
			txtOverhead.setToolTipText("Geben Sie die benötigte Anzahl von Overheads an");
			txtOverhead.setText("0");
			txtOverhead.setColumns(10);
			txtOverhead.setBounds(255, 224, 46, 20);
		}
		return txtOverhead;
	}
	public JTextField getTxtBoard() {
		if (txtBoard == null) {
			txtBoard = new JTextField();
			txtBoard.setToolTipText("Geben Sie die benötigte Anzahl von Tafeln an");
			txtBoard.setText("0");
			txtBoard.setColumns(10);
			txtBoard.setBounds(255, 255, 46, 20);
		}
		return txtBoard;
	}
	public JTextField getTxtWhiteboard() {
		if (txtWhiteboard == null) {
			txtWhiteboard = new JTextField();
			txtWhiteboard.setToolTipText("Geben Sie die benötigte Anzahl von Whiteboards an");
			txtWhiteboard.setText("0");
			txtWhiteboard.setColumns(10);
			txtWhiteboard.setBounds(255, 286, 46, 20);
		}
		return txtWhiteboard;
	}
	public JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("Vorschlag");
			btnNew.setToolTipText("<html>Geben Sie Ihre Wunschgrößen ein <br> und Klicken Sie hier, um sich einen passenden <br>Raum oben anzeigen zu lassen</html>");
			btnNew.addActionListener(new RoomRequestDialogBtns(this, "newSuggestion"));
			btnNew.setBounds(230, 316, 90, 28);
		}
		return btnNew;
	}
	public JComboBox<String> getCmbboxSemester() {
		if (cmbboxSemester == null) {
			cmbboxSemester = new JComboBox<String>();
			cmbboxSemester.setToolTipText("Geben Sie das Semester der Lehrveranstaltung ein");
			cmbboxSemester.setModel(new CmbboxFilterSemester("select"));
			cmbboxSemester.addActionListener(new RoomRequestDialogBtns(this, "combo"));
			cmbboxSemester.setBounds(139, 71, 102, 20);
		}
		return cmbboxSemester;
	}
	public JLabel getLblSem() {
		if (lblSem == null) {
			lblSem = new JLabel("Semester:");
			lblSem.setBounds(83, 74, 58, 14);
		}
		return lblSem;
	}
	public JLabel getLblSeats() {
		if (lblSeats == null) {
			lblSeats = new JLabel("Sitze:");
			lblSeats.setBounds(35, 227, 46, 14);
		}
		return lblSeats;
	}
	public JLabel getLblPcs() {
		if (lblPcs == null) {
			lblPcs = new JLabel("PC-Plätze:");
			lblPcs.setBounds(35, 258, 58, 14);
		}
		return lblPcs;
	}
	public JLabel getLblBeamer() {
		if (lblBeamer == null) {
			lblBeamer = new JLabel("Beamer:");
			lblBeamer.setBounds(34, 289, 46, 14);
		}
		return lblBeamer;
	}
	public JLabel getLblVisual() {
		if (lblVisual == null) {
			lblVisual = new JLabel("Visualizer:");
			lblVisual.setBounds(34, 319, 58, 14);
		}
		return lblVisual;
	}
	public JLabel getLblOverhead() {
		if (lblOverhead == null) {
			lblOverhead = new JLabel("Overheads:");
			lblOverhead.setBounds(188, 227, 58, 14);
		}
		return lblOverhead;
	}
	public JLabel getLblBoards() {
		if (lblBoards == null) {
			lblBoards = new JLabel("Tafeln:");
			lblBoards.setBounds(188, 258, 46, 14);
		}
		return lblBoards;
	}
	public JLabel getLblWhiteboard() {
		if (lblWhiteboard == null) {
			lblWhiteboard = new JLabel("Whiteboards:");
			lblWhiteboard.setBounds(187, 289, 65, 14);
		}
		return lblWhiteboard;
	}

	public IntfCourse getCourse() {
		return course;
	}

	public void setCourse(IntfCourse course) {
		this.course = course;
	}
	public JLabel getLblCountSeats() {
		if (lblCountSeats == null) {
			lblCountSeats = new JLabel("n/a");
			lblCountSeats.setBounds(147, 227, 27, 14);
		}
		return lblCountSeats;
	}
	public JLabel getLblCountPcs() {
		if (lblCountPcs == null) {
			lblCountPcs = new JLabel("n/a");
			lblCountPcs.setBounds(147, 258, 27, 14);
		}
		return lblCountPcs;
	}
	public JLabel getLblCountBeamer() {
		if (lblCountBeamer == null) {
			lblCountBeamer = new JLabel("n/a");
			lblCountBeamer.setBounds(147, 289, 27, 14);
		}
		return lblCountBeamer;
	}
	public JLabel getLblCountVisual() {
		if (lblCountVisual == null) {
			lblCountVisual = new JLabel("n/a");
			lblCountVisual.setBounds(147, 319, 27, 14);
		}
		return lblCountVisual;
	}
	public JLabel getLblCountOverheads() {
		if (lblCountOverheads == null) {
			lblCountOverheads = new JLabel("n/a");
			lblCountOverheads.setBounds(311, 227, 27, 14);
		}
		return lblCountOverheads;
	}
	public JLabel getLblCountBoards() {
		if (lblCountBoards == null) {
			lblCountBoards = new JLabel("n/a");
			lblCountBoards.setBounds(311, 258, 27, 14);
		}
		return lblCountBoards;
	}
	public JLabel getLblCountWhiteboards() {
		if (lblCountWhiteboards == null) {
			lblCountWhiteboards = new JLabel("n/a");
			lblCountWhiteboards.setBounds(311, 289, 27, 14);
		}
		return lblCountWhiteboards;
	}
	public JTextArea getTxtrLegenSieWunschgren() {
		if (txtrLegenSieWunschgren == null) {
			txtrLegenSieWunschgren = new JTextArea();
			txtrLegenSieWunschgren.setOpaque(false);
			txtrLegenSieWunschgren.setFont(new Font("Tahoma", Font.ITALIC, 11));
			txtrLegenSieWunschgren.setLineWrap(true);
			txtrLegenSieWunschgren.setText("Legen sie Wunschgrößen fest und lassen Sie sich einen freien Raum vorschlagen (hinter Ihren Wunschgrößen sehen Sie die Größen des aktuellen Raumes):");
			txtrLegenSieWunschgren.setBounds(10, 174, 328, 46);
		}
		return txtrLegenSieWunschgren;
	}
}
