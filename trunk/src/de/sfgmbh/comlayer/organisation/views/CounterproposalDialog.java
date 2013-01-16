package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.model.CmbboxFilterDay;
import de.sfgmbh.comlayer.core.model.CmbboxFilterRoomnumber;
import de.sfgmbh.comlayer.core.model.CmbboxFilterTime;
import de.sfgmbh.comlayer.organisation.controller.CounterproposalFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.UserCreateDialogWin;

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
	private JTextField textField;
	private JLabel lblDay;
	private JLabel lblTime;
	private JComboBox<String> cmbboxRoom;
	private JComboBox<String> cmbboxDay;
	private JComboBox<String> cmbboxTime;
	private JLabel lblRoomSeats;
	private JLabel lblStudents;
	private JLabel lblSelectedStatus;
	private JLabel lblSelectedRoomSeats;
	private JLabel lblSelectedStudents;
	private JLabel label_1;

	/**
	 * Create the frame.
	 */
	public CounterproposalDialog(RoomAllocation ra) {
		this.proposalAllocation = ra;
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CounterproposalDialog.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Konfliktl\u00F6sung");
		setBounds(100, 100, 320, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMsg = new JLabel("Nachricht an den Dozenten:");
		lblMsg.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
		contentPane.add(getTextField());
		contentPane.add(getLblDay());
		contentPane.add(getLblTime());
		contentPane.add(getCmbboxRoom());
		contentPane.add(getCmbboxDay());
		contentPane.add(getCmbboxTime());
		contentPane.add(getLblRoomSeats());
		contentPane.add(getLblStudents());
		contentPane.add(getLblSelectedStatus());
		contentPane.add(getLblSelectedRoomSeats());
		contentPane.add(getLblSelectedStudents());
		contentPane.add(getLabel_1());
		
		addWindowListener(new UserCreateDialogWin(this));
	}
	public JTextArea getTxtrUnterbreitenSieDem() {
		if (txtrUnterbreitenSieDem == null) {
			txtrUnterbreitenSieDem = new JTextArea();
			txtrUnterbreitenSieDem.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(20, 264, 249, 58);
			textField.setColumns(10);
		}
		return textField;
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
			cmbboxRoom.setModel(new CmbboxFilterRoomnumber("select"));
			cmbboxRoom.setEditable(true);
			cmbboxRoom.setBounds(124, 83, 102, 20);
		}
		return cmbboxRoom;
	}
	public JComboBox<String> getCmbboxDay() {
		if (cmbboxDay == null) {
			cmbboxDay = new JComboBox<String>();
			cmbboxDay.setModel(new CmbboxFilterDay("select"));
			cmbboxDay.setBounds(124, 108, 102, 20);
		}
		return cmbboxDay;
	}
	public JComboBox<String> getCmbboxTime() {
		if (cmbboxTime == null) {
			cmbboxTime = new JComboBox<String>();
			cmbboxTime.setModel(new CmbboxFilterTime("select"));
			cmbboxTime.setBounds(124, 133, 102, 20);
		}
		return cmbboxTime;
	}
	public JLabel getLblRoomSeats() {
		if (lblRoomSeats == null) {
			lblRoomSeats = new JLabel("Plätze des gewählten Raums:");
			lblRoomSeats.setBounds(20, 171, 146, 14);
		}
		return lblRoomSeats;
	}
	public JLabel getLblStudents() {
		if (lblStudents == null) {
			lblStudents = new JLabel("Erwartete Teilnehmer:");
			lblStudents.setBounds(20, 186, 137, 14);
		}
		return lblStudents;
	}
	public JLabel getLblSelectedStatus() {
		if (lblSelectedStatus == null) {
			lblSelectedStatus = new JLabel("Belegung zur gewählten Zeit:");
			lblSelectedStatus.setBounds(20, 201, 170, 14);
		}
		return lblSelectedStatus;
	}
	public JLabel getLblSelectedRoomSeats() {
		if (lblSelectedRoomSeats == null) {
			lblSelectedRoomSeats = new JLabel("n/a");
			lblSelectedRoomSeats.setBounds(170, 171, 46, 14);
		}
		return lblSelectedRoomSeats;
	}
	public JLabel getLblSelectedStudents() {
		if (lblSelectedStudents == null) {
			lblSelectedStudents = new JLabel(String.valueOf(this.proposalAllocation.getCourse_().getExpectedAttendees_()));
			lblSelectedStudents.setBounds(170, 186, 46, 14);
		}
		return lblSelectedStudents;
	}
	public JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("n/a");
			label_1.setBounds(170, 201, 46, 14);
		}
		return label_1;
	}
}
