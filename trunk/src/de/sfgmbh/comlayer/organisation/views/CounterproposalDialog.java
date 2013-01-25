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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.core.model.CmbboxFilterDay;
import de.sfgmbh.comlayer.core.model.CmbboxFilterRoomnumber;
import de.sfgmbh.comlayer.core.model.CmbboxFilterTime;
import de.sfgmbh.comlayer.organisation.controller.CounterproposalFrameBtns;

/**
 * Modal Dialog for a counter proposal
 * 
 * @author anna
 * @author hannes
 *
 */
public class CounterproposalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_;
	private JTextArea txtCounterOffer_;
	private IntfRoomAllocation proposalAllocation_;
	private JLabel lblRoom_;
	private JLabel lblDay_;
	private JLabel lblTime_;
	private JComboBox<String> cmbboxRoom_;
	private JComboBox<String> cmbboxDay_;
	private JComboBox<String> cmbboxTime_;
	private JLabel lblRoomSeats_;
	private JLabel lblStudents_;
	private JLabel lblStatus_;
	private JLabel lblSelectedRoomSeats_;
	private JLabel lblSelectedStudents_;
	private JLabel lblSelectedStatus_;
	private IntfCtrlRoomAllocation ctrlRoomAllocation_ = new CtrlRoomAllocation();
	private JScrollPane scrollPane_;
	private JEditorPane editorPane_;

	/**
	 * Create the frame.
	 */
	public CounterproposalDialog(IntfRoomAllocation roomAllocation) {
		this.proposalAllocation_ = roomAllocation;
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
		this.setSuggestRoomAllocation(roomAllocation);
	}
	
	/**
	 * Customize according to a room allocation
	 * @param roomAllocation
	 */
	public void setRoomAllocation(IntfRoomAllocation roomAllocation) {
		this.proposalAllocation_ = roomAllocation;
		this.proposalAllocation_.setForceConflictingAllocations_();
		getCmbboxDay().removeActionListener(getCmbboxDay().getActionListeners()[0]);
		getCmbboxTime().removeActionListener(getCmbboxTime().getActionListeners()[0]);
		getCmbboxRoom().removeActionListener(getCmbboxRoom().getActionListeners()[0]);
		getCmbboxDay().setSelectedIndex(this.proposalAllocation_.getDay_() - 1);
		getCmbboxTime().setSelectedIndex(this.proposalAllocation_.getTime_() - 1);
		getCmbboxRoom().setSelectedItem(this.proposalAllocation_.getRoom_().getRoomNumber_());
		getCmbboxDay().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getCmbboxTime().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getCmbboxRoom().addActionListener(new CounterproposalFrameBtns(this, "combo"));
		getEditorPane().setText(this.proposalAllocation_.getOrgaMessage_());
		getLblSelectedRoomSeats().setText(String.valueOf(this.proposalAllocation_.getRoom_().getSeats_()));
		if (this.proposalAllocation_.getConflictingAllocations_().isEmpty()) {
			getLblSelectedStatus().setText("frei");
		} else {
			getLblSelectedStatus().setText("belegt");
		}
	}
	
	/**
	 * Customize according to a suggested room allocation
	 * @param roomAllocation
	 */
	public void setSuggestRoomAllocation(IntfRoomAllocation roomAllocation) {
		IntfRoomAllocation suggestRoomAllocation = ctrlRoomAllocation_.suggest(roomAllocation);
		if (suggestRoomAllocation == null) {
			AppModel.getInstance().getExceptionHandler().setNewException("Scheinbar gibt es für diese Veranstaltung dieses Semester keinen freien Raum mehr,  der groß genug wäre.", "Information", "info");
			this.setRoomAllocation(roomAllocation);
		} else {
			this.setRoomAllocation(suggestRoomAllocation);
		}
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CounterproposalDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setTitle("Konflikt lösen");
		setBounds(100, 100, 320, 410);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);
		
		JLabel lblMsg = new JLabel("Nachricht an den Dozenten:");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMsg.setBounds(20, 239, 196, 14);
		contentPane_.add(lblMsg);
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.setBounds(179, 333, 90, 28);
		btnSenden.addActionListener(new CounterproposalFrameBtns(this, "send"));
		contentPane_.add(btnSenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new CounterproposalFrameBtns(this, "cancel"));
		btnAbbrechen.setBounds(67, 333, 90, 28);
		contentPane_.add(btnAbbrechen);
		contentPane_.add(getTxtrUnterbreitenSieDem());
		contentPane_.add(getLblRoom());
		contentPane_.add(getLblDay());
		contentPane_.add(getLblTime());
		contentPane_.add(getCmbboxRoom());
		contentPane_.add(getCmbboxDay());
		contentPane_.add(getScrollPane());
		contentPane_.add(getCmbboxTime());
		contentPane_.add(getLblRoomSeats());
		contentPane_.add(getLblStudents());
		contentPane_.add(getLblStatus());
		contentPane_.add(getLblSelectedRoomSeats());
		contentPane_.add(getLblSelectedStudents());
		contentPane_.add(getLblSelectedStatus());
		
	}
	/**
	 * 
	 * @return the txtCounterOffer
	 */
	public JTextArea getTxtrUnterbreitenSieDem() {
		if (txtCounterOffer_ == null) {
			txtCounterOffer_ = new JTextArea();
			txtCounterOffer_.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtCounterOffer_.setOpaque(false);
			txtCounterOffer_.setEditable(false);
			txtCounterOffer_.setWrapStyleWord(true);
			txtCounterOffer_.setLineWrap(true);
			txtCounterOffer_.setText("Unterbreiten Sie dem Dozenten " +
					this.proposalAllocation_.getCourse_().getLecturer_().getlName_() +
					" für die Veranstaltung " +
					this.proposalAllocation_.getCourse_().getCourseAcronym_() +
					" im " +
					this.proposalAllocation_.getSemester_() +
					" einen Gegenvorschlag, der sofort freigeschalten wird, wenn der Dozent ihn annimmt:");
			txtCounterOffer_.setBounds(10, 11, 284, 73);
		}
		return txtCounterOffer_;
	}
	/**
	 * 
	 * @return the lblRoom
	 */
	public JLabel getLblRoom() {
		if (lblRoom_ == null) {
			lblRoom_ = new JLabel("Raum:");
			lblRoom_.setBounds(56, 86, 46, 14);
		}
		return lblRoom_;
	}
	/**
	 * 
	 * @return the lblDay
	 */
	public JLabel getLblDay() {
		if (lblDay_ == null) {
			lblDay_ = new JLabel("Tag:");
			lblDay_.setBounds(56, 111, 46, 14);
		}
		return lblDay_;
	}
	/**
	 * 
	 * @return the lblTime
	 */
	public JLabel getLblTime() {
		if (lblTime_ == null) {
			lblTime_ = new JLabel("Zeit:");
			lblTime_.setBounds(56, 136, 46, 14);
		}
		return lblTime_;
	}
	/**
	 * 
	 * @return the cmbboxRoom
	 */
	public JComboBox<String> getCmbboxRoom() {
		if (cmbboxRoom_ == null) {
			cmbboxRoom_ = new JComboBox<String>();
			cmbboxRoom_.setModel(new CmbboxFilterRoomnumber(cmbboxRoom_, "select"));
			cmbboxRoom_.setEditable(true);
			cmbboxRoom_.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxRoom_.setBounds(112, 86, 102, 20);
		}
		return cmbboxRoom_;
	}
	/**
	 * 
	 * @return the cmbboxDay
	 */
	public JComboBox<String> getCmbboxDay() {
		if (cmbboxDay_ == null) {
			cmbboxDay_ = new JComboBox<String>();
			cmbboxDay_.setModel(new CmbboxFilterDay("select"));
			cmbboxDay_.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxDay_.setBounds(112, 111, 102, 20);
		}
		return cmbboxDay_;
	}
	/**
	 * 
	 * @return the cmbboxTime
	 */
	public JComboBox<String> getCmbboxTime() {
		if (cmbboxTime_ == null) {
			cmbboxTime_ = new JComboBox<String>();
			cmbboxTime_.setModel(new CmbboxFilterTime("select"));
			cmbboxTime_.addActionListener(new CounterproposalFrameBtns(this, "combo"));
			cmbboxTime_.setBounds(112, 136, 102, 20);
		}
		return cmbboxTime_;
	}
	/**
	 * 
	 * @return the lblRoomSeats
	 */
	public JLabel getLblRoomSeats() {
		if (lblRoomSeats_ == null) {
			lblRoomSeats_ = new JLabel("Plätze des gewählten Raums:");
			lblRoomSeats_.setBounds(34, 171, 146, 14);
		}
		return lblRoomSeats_;
	}
	/**
	 * 
	 * @return the lblStudents
	 */
	public JLabel getLblStudents() {
		if (lblStudents_ == null) {
			lblStudents_ = new JLabel("Erwartete Teilnehmer:");
			lblStudents_.setBounds(34, 186, 137, 14);
		}
		return lblStudents_;
	}
	/**
	 * 
	 * @return the lblStatus
	 */
	public JLabel getLblStatus() {
		if (lblStatus_ == null) {
			lblStatus_ = new JLabel("Belegung zur gewählten Zeit:");
			lblStatus_.setBounds(34, 201, 146, 14);
		}
		return lblStatus_;
	}
	/**
	 * 
	 * @return the lblselectedStudents
	 */
	public JLabel getLblSelectedRoomSeats() {
		if (lblSelectedRoomSeats_ == null) {
			lblSelectedRoomSeats_ = new JLabel("n/a");
			lblSelectedRoomSeats_.setBounds(223, 171, 46, 14);
		}
		return lblSelectedRoomSeats_;
	}
	/**
	 * 
	 * @return the lblSelectedstudents
	 */
	public JLabel getLblSelectedStudents() {
		if (lblSelectedStudents_ == null) {
			lblSelectedStudents_ = new JLabel(String.valueOf(this.proposalAllocation_.getCourse_().getExpectedAttendees_()));
			lblSelectedStudents_.setBounds(223, 186, 46, 14);
		}
		return lblSelectedStudents_;
	}
	/**
	 * 
	 * @return the lblSelectedStatus
	 */
	public JLabel getLblSelectedStatus() {
		if (lblSelectedStatus_ == null) {
			lblSelectedStatus_ = new JLabel("n/a");
			lblSelectedStatus_.setBounds(223, 201, 46, 14);
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
	 * @param proposalAllocation the proposalAllocation to set
	 */
	public void setProposalAllocation(IntfRoomAllocation proposalAllocation) {
		this.proposalAllocation_ = proposalAllocation;
	}
	/**
	 * 
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		if (scrollPane_ == null) {
			scrollPane_ = new JScrollPane();
			scrollPane_.setAutoscrolls(true);
			scrollPane_.setBackground(Color.WHITE);
			scrollPane_.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane_.setSize(new Dimension(800, 600));
			scrollPane_.setMinimumSize(new Dimension(800, 600));
			scrollPane_.setMaximumSize(new Dimension(800, 600));
			scrollPane_.setBounds(20, 259, 249, 63);
			scrollPane_.setViewportView(getEditorPane());
		}
		return scrollPane_;
	}
	/**
	 * 
	 * @return the editorPane
	 */
	public JEditorPane getEditorPane() {
		if (editorPane_ == null) {
			editorPane_ = new JEditorPane();
			editorPane_.setBorder(null);
		}
		return editorPane_;
	}
}
