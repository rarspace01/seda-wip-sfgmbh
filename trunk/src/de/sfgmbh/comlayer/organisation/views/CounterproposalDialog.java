package de.sfgmbh.comlayer.organisation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.core.model.CmbboxFilterDay;
import de.sfgmbh.comlayer.core.model.CmbboxFilterRoomnumber;
import de.sfgmbh.comlayer.core.model.CmbboxFilterTime;
import de.sfgmbh.comlayer.organisation.controller.CounterproposalFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogWin;
import javax.swing.ScrollPaneConstants;

/**
 * Modal Dialog for a counter proposal
 * 
 * @author anna
 * @author hannes
 *
 */
public class CounterproposalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtrUnterbreitenSieDem;
	private RoomAllocation proposalAllocation;
	private JLabel lblRoom;
	private JLabel lblDay;
	private JLabel lblTime;
	private JComboBox<String> cmbboxRoom;
	private JComboBox<String> cmbboxDay;
	private JComboBox<String> cmbboxTime;
	private JLabel lblRoomSeats;
	private JLabel lblStudents;
	private JLabel lblStatus;
	private JLabel lblSelectedRoomSeats;
	private JLabel lblSelectedStudents;
	private JLabel lblSelectedStatus;
	private CtrlRoomAllocation ctrlRoomAllocation = new CtrlRoomAllocation();
	private JScrollPane scrollPane;
	private JEditorPane editorPane;

	/**
	 * Create the frame.
	 */
	public CounterproposalDialog(RoomAllocation ra) {
		this.proposalAllocation = ra;
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
		this.setSuggestRoomAllocation(ra);
	}
	
	/**
	 * Customize according to a room allocation
	 * @param roomAllocation
	 */
	public void setRoomAllocation(RoomAllocation ra) {
		this.proposalAllocation = ra;
		this.proposalAllocation.setForceConflictingAllocations_();
		getCmbboxDay().removeActionListener(getCmbboxDay().getActionListeners()[0]);
		getCmbboxTime().removeActionListener(getCmbboxTime().getActionListeners()[0]);
		getCmbboxRoom().removeActionListener(getCmbboxRoom().getActionListeners()[0]);
		getCmbboxDay().setSelectedIndex(this.proposalAllocation.getDay_() - 1);
		getCmbboxTime().setSelectedIndex(this.proposalAllocation.getTime_() - 1);
		getCmbboxRoom().setSelectedItem(this.proposalAllocation.getRoom_().getRoomNumber_());
		getCmbboxDay().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getCmbboxTime().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getCmbboxRoom().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getEditorPane().setText(this.proposalAllocation.getOrgaMessage_());
		getLblSelectedRoomSeats().setText(String.valueOf(this.proposalAllocation.getRoom_().getSeats_()));
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
	public void setSuggestRoomAllocation(RoomAllocation ra) {
		RoomAllocation suggestRoomAllocation = ctrlRoomAllocation.suggest(ra);
		if (suggestRoomAllocation == null) {
			AppModel.getInstance().getExceptionHandler().setNewException("Scheinbar gibt es für diese Veranstaltung dieses Semester keinen freien Raum mehr,  der groß genug wäre.", "Information", "info");
			this.setRoomAllocation(ra);
		} else {
			this.setRoomAllocation(suggestRoomAllocation);
		}
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CounterproposalDialog.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Konfliktlösung");
		setBounds(100, 100, 320, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMsg = new JLabel("Nachricht an den Dozenten:");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMsg.setBounds(20, 239, 196, 14);
		contentPane.add(lblMsg);
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.setBounds(179, 333, 90, 28);
		btnSenden.addActionListener(new CounterproposalFrameBtns(this, "send"));
		contentPane.add(btnSenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new CounterproposalFrameBtns(this, "cancel"));
		btnAbbrechen.setBounds(67, 333, 90, 28);
		contentPane.add(btnAbbrechen);
		contentPane.add(getTxtrUnterbreitenSieDem());
		contentPane.add(getLblRoom());
		contentPane.add(getLblDay());
		contentPane.add(getLblTime());
		contentPane.add(getCmbboxRoom());
		contentPane.add(getCmbboxDay());
		contentPane.add(getScrollPane());
		contentPane.add(getCmbboxTime());
		contentPane.add(getLblRoomSeats());
		contentPane.add(getLblStudents());
		contentPane.add(getLblStatus());
		contentPane.add(getLblSelectedRoomSeats());
		contentPane.add(getLblSelectedStudents());
		contentPane.add(getLblSelectedStatus());
		
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
			txtrUnterbreitenSieDem.setText("Unterbreiten Sie dem Dozenten " +
					this.proposalAllocation.getCourse_().getLecturer_().getlName_() +
					" für die Veranstaltung " +
					this.proposalAllocation.getCourse_().getCourseAcronym_() +
					" im " +
					this.proposalAllocation.getSemester_() +
					" einen Gegenvorschlag, der sofort freigeschalten wird, wenn der Dozent ihn annimmt:");
			txtrUnterbreitenSieDem.setBounds(10, 11, 284, 73);
		}
		return txtrUnterbreitenSieDem;
	}
	public JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Raum:");
			lblRoom.setBounds(56, 86, 46, 14);
		}
		return lblRoom;
	}
	public JLabel getLblDay() {
		if (lblDay == null) {
			lblDay = new JLabel("Tag:");
			lblDay.setBounds(56, 111, 46, 14);
		}
		return lblDay;
	}
	public JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("Zeit:");
			lblTime.setBounds(56, 136, 46, 14);
		}
		return lblTime;
	}
	public JComboBox<String> getCmbboxRoom() {
		if (cmbboxRoom == null) {
			cmbboxRoom = new JComboBox<String>();
			cmbboxRoom.setModel(new CmbboxFilterRoomnumber(cmbboxRoom, "select"));
			cmbboxRoom.setEditable(true);
			cmbboxRoom.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxRoom.setBounds(112, 86, 102, 20);
		}
		return cmbboxRoom;
	}
	public JComboBox<String> getCmbboxDay() {
		if (cmbboxDay == null) {
			cmbboxDay = new JComboBox<String>();
			cmbboxDay.setModel(new CmbboxFilterDay("select"));
			cmbboxDay.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxDay.setBounds(112, 111, 102, 20);
		}
		return cmbboxDay;
	}
	public JComboBox<String> getCmbboxTime() {
		if (cmbboxTime == null) {
			cmbboxTime = new JComboBox<String>();
			cmbboxTime.setModel(new CmbboxFilterTime("select"));
			cmbboxTime.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxTime.setBounds(112, 136, 102, 20);
		}
		return cmbboxTime;
	}
	public JLabel getLblRoomSeats() {
		if (lblRoomSeats == null) {
			lblRoomSeats = new JLabel("Plätze des gewählten Raums:");
			lblRoomSeats.setBounds(34, 171, 146, 14);
		}
		return lblRoomSeats;
	}
	public JLabel getLblStudents() {
		if (lblStudents == null) {
			lblStudents = new JLabel("Erwartete Teilnehmer:");
			lblStudents.setBounds(34, 186, 137, 14);
		}
		return lblStudents;
	}
	public JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Belegung zur gewählten Zeit:");
			lblStatus.setBounds(34, 201, 146, 14);
		}
		return lblStatus;
	}
	public JLabel getLblSelectedRoomSeats() {
		if (lblSelectedRoomSeats == null) {
			lblSelectedRoomSeats = new JLabel("n/a");
			lblSelectedRoomSeats.setBounds(223, 171, 46, 14);
		}
		return lblSelectedRoomSeats;
	}
	public JLabel getLblSelectedStudents() {
		if (lblSelectedStudents == null) {
			lblSelectedStudents = new JLabel(String.valueOf(this.proposalAllocation.getCourse_().getExpectedAttendees_()));
			lblSelectedStudents.setBounds(223, 186, 46, 14);
		}
		return lblSelectedStudents;
	}
	public JLabel getLblSelectedStatus() {
		if (lblSelectedStatus == null) {
			lblSelectedStatus = new JLabel("n/a");
			lblSelectedStatus.setBounds(223, 201, 46, 14);
		}
		return lblSelectedStatus;
	}

	/**
	 * @return the proposalAllocation
	 */
	public RoomAllocation getProposalAllocation() {
		return proposalAllocation;
	}

	/**
	 * @param proposalAllocation the proposalAllocation to set
	 */
	public void setProposalAllocation(RoomAllocation proposalAllocation) {
		this.proposalAllocation = proposalAllocation;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setAutoscrolls(true);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setSize(new Dimension(800, 600));
			scrollPane.setMinimumSize(new Dimension(800, 600));
			scrollPane.setMaximumSize(new Dimension(800, 600));
			scrollPane.setBounds(20, 259, 249, 63);
			scrollPane.setViewportView(getEditorPane());
		}
		return scrollPane;
	}
	public JEditorPane getEditorPane() {
		if (editorPane == null) {
			editorPane = new JEditorPane();
			editorPane.setBorder(null);
		}
		return editorPane;
	}
}
