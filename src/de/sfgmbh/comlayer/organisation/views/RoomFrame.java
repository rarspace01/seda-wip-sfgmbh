package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.comlayer.organisation.controller.RoomFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.RoomFrameWin;

/**
 * Dialog/Frame to create and edit rooms
 * 
 * @author denis
 *
 */
public class RoomFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane_;
	private JTextField txtRoomid_;
	private JTextField txtLevel_;
	private JTextField txtRoomNumber_;
	private JTextField txtBeamer_;
	private JTextField txtPcSeats_;
	private JTextField txtSeats_;
	private JTextField txtVisualizer_;
	private JTextField txtOverheads_;
	private JTextField txtChalkboards_;
	private JTextField txtWhiteboards_;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public RoomFrame() {
		setModal(true);
		initialize();
		setLocationRelativeTo(null);
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoomFrame.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setTitle("Neuen Raum anlegen");
		setBounds(100, 100, 266, 400);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{101, 17, 98, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 28, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane_.setLayout(gbl_contentPane);
		
		addWindowListener(new RoomFrameWin());
		
		JButton btnSave = new JButton("Speichern");
		btnSave.addActionListener(new RoomFrameBtns("save"));
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new RoomFrameBtns("cancle"));
		
		JLabel lblLevel = new JLabel("Stockwerk:");
		lblLevel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridwidth = 2;
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 1;
		contentPane_.add(lblLevel, gbc_lblLevel);
		
		txtRoomid_=new JTextField("-1");
		
		txtLevel_ = new JTextField();
		txtLevel_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtLevel = new GridBagConstraints();
		gbc_txtLevel.fill = GridBagConstraints.BOTH;
		gbc_txtLevel.insets = new Insets(0, 0, 5, 0);
		gbc_txtLevel.gridwidth = 2;
		gbc_txtLevel.gridx = 1;
		gbc_txtLevel.gridy = 1;
		contentPane_.add(txtLevel_, gbc_txtLevel);
		
		JLabel lblRoomnumber = new JLabel("Raumnummer:");
		lblRoomnumber.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblRoomnumber = new GridBagConstraints();
		gbc_lblRoomnumber.anchor = GridBagConstraints.NORTH;
		gbc_lblRoomnumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRoomnumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomnumber.gridwidth = 2;
		gbc_lblRoomnumber.gridx = 0;
		gbc_lblRoomnumber.gridy = 2;
		contentPane_.add(lblRoomnumber, gbc_lblRoomnumber);
		
		txtRoomNumber_ = new JTextField();
		txtRoomNumber_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtRoomName = new GridBagConstraints();
		gbc_txtRoomName.fill = GridBagConstraints.BOTH;
		gbc_txtRoomName.insets = new Insets(0, 0, 5, 0);
		gbc_txtRoomName.gridwidth = 2;
		gbc_txtRoomName.gridx = 1;
		gbc_txtRoomName.gridy = 2;
		contentPane_.add(txtRoomNumber_, gbc_txtRoomName);
		
		JLabel lblSeats = new JLabel("Pl\u00E4tze:");
		lblSeats.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSeats = new GridBagConstraints();
		gbc_lblSeats.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeats.gridwidth = 2;
		gbc_lblSeats.gridx = 0;
		gbc_lblSeats.gridy = 3;
		contentPane_.add(lblSeats, gbc_lblSeats);
		
		txtSeats_ = new JTextField();
		txtSeats_.setText("0");
		txtSeats_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSeats = new GridBagConstraints();
		gbc_txtSeats.fill = GridBagConstraints.BOTH;
		gbc_txtSeats.insets = new Insets(0, 0, 5, 0);
		gbc_txtSeats.gridwidth = 2;
		gbc_txtSeats.gridx = 1;
		gbc_txtSeats.gridy = 3;
		contentPane_.add(txtSeats_, gbc_txtSeats);
		
		JLabel lblPcSeats = new JLabel("PC-Pl\u00E4tze:");
		lblPcSeats.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPcSeats = new GridBagConstraints();
		gbc_lblPcSeats.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPcSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblPcSeats.gridwidth = 2;
		gbc_lblPcSeats.gridx = 0;
		gbc_lblPcSeats.gridy = 4;
		contentPane_.add(lblPcSeats, gbc_lblPcSeats);
		
		txtPcSeats_ = new JTextField();
		txtPcSeats_.setText("0");
		txtPcSeats_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtPcSeats = new GridBagConstraints();
		gbc_txtPcSeats.fill = GridBagConstraints.BOTH;
		gbc_txtPcSeats.insets = new Insets(0, 0, 5, 0);
		gbc_txtPcSeats.gridwidth = 2;
		gbc_txtPcSeats.gridx = 1;
		gbc_txtPcSeats.gridy = 4;
		contentPane_.add(txtPcSeats_, gbc_txtPcSeats);
		
		JLabel lblBeamer = new JLabel("Beamer:");
		lblBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBeamer = new GridBagConstraints();
		gbc_lblBeamer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeamer.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeamer.gridwidth = 2;
		gbc_lblBeamer.gridx = 0;
		gbc_lblBeamer.gridy = 5;
		contentPane_.add(lblBeamer, gbc_lblBeamer);
		
		txtBeamer_ = new JTextField();
		txtBeamer_.setText("0");
		txtBeamer_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtBeamer = new GridBagConstraints();
		gbc_txtBeamer.fill = GridBagConstraints.BOTH;
		gbc_txtBeamer.insets = new Insets(0, 0, 5, 0);
		gbc_txtBeamer.gridwidth = 2;
		gbc_txtBeamer.gridx = 1;
		gbc_txtBeamer.gridy = 5;
		contentPane_.add(txtBeamer_, gbc_txtBeamer);
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		lblVisualizer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblVisualizer = new GridBagConstraints();
		gbc_lblVisualizer.anchor = GridBagConstraints.SOUTH;
		gbc_lblVisualizer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVisualizer.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisualizer.gridwidth = 2;
		gbc_lblVisualizer.gridx = 0;
		gbc_lblVisualizer.gridy = 6;
		contentPane_.add(lblVisualizer, gbc_lblVisualizer);
		
		txtVisualizer_ = new JTextField();
		txtVisualizer_.setText("0");
		txtVisualizer_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtVisualizer = new GridBagConstraints();
		gbc_txtVisualizer.fill = GridBagConstraints.BOTH;
		gbc_txtVisualizer.insets = new Insets(0, 0, 5, 0);
		gbc_txtVisualizer.gridwidth = 2;
		gbc_txtVisualizer.gridx = 1;
		gbc_txtVisualizer.gridy = 6;
		contentPane_.add(txtVisualizer_, gbc_txtVisualizer);
		
		JLabel lblOverheads = new JLabel("Overheads:");
		lblOverheads.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOverheads = new GridBagConstraints();
		gbc_lblOverheads.anchor = GridBagConstraints.SOUTH;
		gbc_lblOverheads.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOverheads.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverheads.gridwidth = 2;
		gbc_lblOverheads.gridx = 0;
		gbc_lblOverheads.gridy = 7;
		contentPane_.add(lblOverheads, gbc_lblOverheads);
		
		txtOverheads_ = new JTextField();
		txtOverheads_.setText("0");
		txtOverheads_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtOverheads = new GridBagConstraints();
		gbc_txtOverheads.fill = GridBagConstraints.BOTH;
		gbc_txtOverheads.insets = new Insets(0, 0, 5, 0);
		gbc_txtOverheads.gridwidth = 2;
		gbc_txtOverheads.gridx = 1;
		gbc_txtOverheads.gridy = 7;
		contentPane_.add(txtOverheads_, gbc_txtOverheads);
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		lblTafeln.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTafeln = new GridBagConstraints();
		gbc_lblTafeln.anchor = GridBagConstraints.SOUTH;
		gbc_lblTafeln.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTafeln.insets = new Insets(0, 0, 5, 5);
		gbc_lblTafeln.gridwidth = 2;
		gbc_lblTafeln.gridx = 0;
		gbc_lblTafeln.gridy = 8;
		contentPane_.add(lblTafeln, gbc_lblTafeln);
		
		txtChalkboards_ = new JTextField();
		txtChalkboards_.setText("0");
		txtChalkboards_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtChalkboards = new GridBagConstraints();
		gbc_txtChalkboards.fill = GridBagConstraints.BOTH;
		gbc_txtChalkboards.insets = new Insets(0, 0, 5, 0);
		gbc_txtChalkboards.gridwidth = 2;
		gbc_txtChalkboards.gridx = 1;
		gbc_txtChalkboards.gridy = 8;
		contentPane_.add(txtChalkboards_, gbc_txtChalkboards);
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		lblWhiteboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWhiteboards = new GridBagConstraints();
		gbc_lblWhiteboards.anchor = GridBagConstraints.SOUTH;
		gbc_lblWhiteboards.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWhiteboards.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhiteboards.gridwidth = 2;
		gbc_lblWhiteboards.gridx = 0;
		gbc_lblWhiteboards.gridy = 9;
		contentPane_.add(lblWhiteboards, gbc_lblWhiteboards);
		
		txtWhiteboards_ = new JTextField();
		txtWhiteboards_.setText("0");
		txtWhiteboards_.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtWhiteboards = new GridBagConstraints();
		gbc_txtWhiteboards.fill = GridBagConstraints.BOTH;
		gbc_txtWhiteboards.insets = new Insets(0, 0, 5, 0);
		gbc_txtWhiteboards.gridwidth = 2;
		gbc_txtWhiteboards.gridx = 1;
		gbc_txtWhiteboards.gridy = 9;
		contentPane_.add(txtWhiteboards_, gbc_txtWhiteboards);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 11;
		contentPane_.add(btnCancel, gbc_btnCancel);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 11;
		contentPane_.add(btnSave, gbc_btnSave);
	}
	
	/**
	 * resets the values from the frame
	 */
	public void reset(){
		this.getTxtroomid().setText("-1");
		this.getTxtRoomNumber().setText("");
		this.getTxtLevel().setText("");
		this.getTxtSeats().setText("");
		this.getTxtPcSeats().setText("");
		this.getTxtBeamer().setText("");
		this.getTxtChalkboards().setText("");
		this.getTxtOverheads().setText("");
		this.getTxtVisualizer().setText("");
		this.getTxtWhiteboards().setText("");
	}
	
	/**
	 * 
	 * @return the txtLevel
	 */
	public JTextField getTxtLevel() {
		return txtLevel_;
	}
	/**
	 * 
	 * @param txtLevel - the txtLevel to set
	 */
	public void setTxtLevel(JTextField txtLevel) {
		this.txtLevel_ = txtLevel;
	}
	/**
	 * 
	 * @return the TxtRoomNumber
	 */
	public JTextField getTxtRoomNumber() {
		return txtRoomNumber_;
	}
	/**
	 * 
	 * @param txtRoomNumber - the txtRoomNumber to set
	 */
	public void setTxtRoomNumber(JTextField txtRoomNumber) {
		this.txtRoomNumber_ = txtRoomNumber;
	}
	/**
	 * 
	 * @return the txtBeamer
	 */
	public JTextField getTxtBeamer() {
		return txtBeamer_;
	}
	/**
	 * 
	 * @param the txtBeamer to set
	 */
	public void setTxtBeamer(JTextField txtBeamer) {
		this.txtBeamer_ = txtBeamer;
	}
	/**
	 * 
	 * @return the txtPcSeats
	 */
	public JTextField getTxtPcSeats() {
		return txtPcSeats_;
	}
	/**
	 * 
	 * @param txtPcSeats - the txtPcSeats to set
	 */
	public void setTxtPcSeats(JTextField txtPcSeats) {
		this.txtPcSeats_ = txtPcSeats;
	}
	/**
	 * 
	 * @return the txtSeats
	 */
	public JTextField getTxtSeats() {
		return txtSeats_;
	}
	/**
	 * 
	 * @param the txtSeats to set
	 */
	public void setTxtSeats(JTextField txtSeats) {
		this.txtSeats_ = txtSeats;
	}
	/**
	 * 
	 * @return the txtVisualizer
	 */
	public JTextField getTxtVisualizer() {
		return txtVisualizer_;
	}
	/**
	 * 
	 * @param the txtVisualizer to set
	 */
	public void setTxtVisualizer(JTextField txtVisualizer) {
		this.txtVisualizer_ = txtVisualizer;
	}
	/**
	 * 
	 * @return the txtOverheads
	 */
	public JTextField getTxtOverheads() {
		return txtOverheads_;
	}
	/**
	 * 
	 * @param the txtOverheads to set
	 */
	public void setTxtOverheads(JTextField txtOverheads) {
		this.txtOverheads_ = txtOverheads;
	}
	/**
	 * 
	 * @return the txtChalkboards
	 */
	public JTextField getTxtChalkboards() {
		return txtChalkboards_;
	}
	/**
	 * 
	 * @param the txtChalkboards to set
	 */
	public void setTxtChalkboards(JTextField txtChalkboards) {
		this.txtChalkboards_ = txtChalkboards;
	}
	/**
	 * 
	 * @return the txtWhiteboards
	 */
	public JTextField getTxtWhiteboards() {
		return txtWhiteboards_;
	}
	/**
	 * 
	 * @param the txtWhiteboards to set
	 */
	public void setTxtWhiteboards(JTextField txtWhiteboards) {
		this.txtWhiteboards_ = txtWhiteboards;
	}
	/**
	 * 
	 * @return the txtRoomid
	 */
	public JTextField getTxtroomid() {
		return txtRoomid_;
	}
	
}
