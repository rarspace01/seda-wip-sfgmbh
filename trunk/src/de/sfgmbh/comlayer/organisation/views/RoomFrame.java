package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.comlayer.organisation.controller.RoomFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.RoomFrameWin;
import java.awt.Toolkit;

public class RoomFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLevel;
	private JTextField txtRoomName;
	private JTextField txtBeamer;
	private JTextField txtPcSeats;
	private JTextField txtSeats;
	private JTextField txtVisualizer;
	private JTextField txtOverheads;
	private JTextField txtChalkboards;
	private JTextField txtWhiteboards;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public RoomFrame() {
		initialize();
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoomFrame.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Rauminfo erstellen");
		setBounds(100, 100, 266, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{101, 17, 98, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 28, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		addWindowListener(new RoomFrameWin());
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new RoomFrameBtns("save"));
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new RoomFrameBtns("cancle"));
		
		JLabel lblPasswort = new JLabel("Stockwerk:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
		gbc_lblPasswort.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswort.gridwidth = 2;
		gbc_lblPasswort.gridx = 0;
		gbc_lblPasswort.gridy = 1;
		contentPane.add(lblPasswort, gbc_lblPasswort);
		
		txtLevel = new JTextField();
		txtLevel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtLevel = new GridBagConstraints();
		gbc_txtLevel.fill = GridBagConstraints.BOTH;
		gbc_txtLevel.insets = new Insets(0, 0, 5, 0);
		gbc_txtLevel.gridwidth = 2;
		gbc_txtLevel.gridx = 1;
		gbc_txtLevel.gridy = 1;
		contentPane.add(txtLevel, gbc_txtLevel);
		
		JLabel lblNutzerkennung = new JLabel("Raumnummer:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNutzerkennung = new GridBagConstraints();
		gbc_lblNutzerkennung.anchor = GridBagConstraints.NORTH;
		gbc_lblNutzerkennung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNutzerkennung.insets = new Insets(0, 0, 5, 5);
		gbc_lblNutzerkennung.gridwidth = 2;
		gbc_lblNutzerkennung.gridx = 0;
		gbc_lblNutzerkennung.gridy = 2;
		contentPane.add(lblNutzerkennung, gbc_lblNutzerkennung);
		
		txtRoomName = new JTextField();
		txtRoomName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtRoomName = new GridBagConstraints();
		gbc_txtRoomName.fill = GridBagConstraints.BOTH;
		gbc_txtRoomName.insets = new Insets(0, 0, 5, 0);
		gbc_txtRoomName.gridwidth = 2;
		gbc_txtRoomName.gridx = 1;
		gbc_txtRoomName.gridy = 2;
		contentPane.add(txtRoomName, gbc_txtRoomName);
		
		JLabel lblNutzerklasse = new JLabel("Pl\u00E4tze:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNutzerklasse = new GridBagConstraints();
		gbc_lblNutzerklasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNutzerklasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblNutzerklasse.gridwidth = 2;
		gbc_lblNutzerklasse.gridx = 0;
		gbc_lblNutzerklasse.gridy = 3;
		contentPane.add(lblNutzerklasse, gbc_lblNutzerklasse);
		
		txtSeats = new JTextField();
		txtSeats.setText("0");
		txtSeats.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSeats = new GridBagConstraints();
		gbc_txtSeats.fill = GridBagConstraints.BOTH;
		gbc_txtSeats.insets = new Insets(0, 0, 5, 0);
		gbc_txtSeats.gridwidth = 2;
		gbc_txtSeats.gridx = 1;
		gbc_txtSeats.gridy = 3;
		contentPane.add(txtSeats, gbc_txtSeats);
		
		JLabel lblLehrstuhl = new JLabel("PC-Pl\u00E4tze:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLehrstuhl = new GridBagConstraints();
		gbc_lblLehrstuhl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLehrstuhl.insets = new Insets(0, 0, 5, 5);
		gbc_lblLehrstuhl.gridwidth = 2;
		gbc_lblLehrstuhl.gridx = 0;
		gbc_lblLehrstuhl.gridy = 4;
		contentPane.add(lblLehrstuhl, gbc_lblLehrstuhl);
		
		txtPcSeats = new JTextField();
		txtPcSeats.setText("0");
		txtPcSeats.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtPcSeats = new GridBagConstraints();
		gbc_txtPcSeats.fill = GridBagConstraints.BOTH;
		gbc_txtPcSeats.insets = new Insets(0, 0, 5, 0);
		gbc_txtPcSeats.gridwidth = 2;
		gbc_txtPcSeats.gridx = 1;
		gbc_txtPcSeats.gridy = 4;
		contentPane.add(txtPcSeats, gbc_txtPcSeats);
		
		JLabel lblBeamer = new JLabel("Beamer:");
		lblBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBeamer = new GridBagConstraints();
		gbc_lblBeamer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeamer.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeamer.gridwidth = 2;
		gbc_lblBeamer.gridx = 0;
		gbc_lblBeamer.gridy = 5;
		contentPane.add(lblBeamer, gbc_lblBeamer);
		
		txtBeamer = new JTextField();
		txtBeamer.setText("0");
		txtBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtBeamer = new GridBagConstraints();
		gbc_txtBeamer.fill = GridBagConstraints.BOTH;
		gbc_txtBeamer.insets = new Insets(0, 0, 5, 0);
		gbc_txtBeamer.gridwidth = 2;
		gbc_txtBeamer.gridx = 1;
		gbc_txtBeamer.gridy = 5;
		contentPane.add(txtBeamer, gbc_txtBeamer);
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		lblVisualizer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblVisualizer = new GridBagConstraints();
		gbc_lblVisualizer.anchor = GridBagConstraints.SOUTH;
		gbc_lblVisualizer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVisualizer.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisualizer.gridwidth = 2;
		gbc_lblVisualizer.gridx = 0;
		gbc_lblVisualizer.gridy = 6;
		contentPane.add(lblVisualizer, gbc_lblVisualizer);
		
		txtVisualizer = new JTextField();
		txtVisualizer.setText("0");
		txtVisualizer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtVisualizer = new GridBagConstraints();
		gbc_txtVisualizer.fill = GridBagConstraints.BOTH;
		gbc_txtVisualizer.insets = new Insets(0, 0, 5, 0);
		gbc_txtVisualizer.gridwidth = 2;
		gbc_txtVisualizer.gridx = 1;
		gbc_txtVisualizer.gridy = 6;
		contentPane.add(txtVisualizer, gbc_txtVisualizer);
		
		JLabel lblOverheads = new JLabel("Overheads:");
		lblOverheads.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOverheads = new GridBagConstraints();
		gbc_lblOverheads.anchor = GridBagConstraints.SOUTH;
		gbc_lblOverheads.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOverheads.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverheads.gridwidth = 2;
		gbc_lblOverheads.gridx = 0;
		gbc_lblOverheads.gridy = 7;
		contentPane.add(lblOverheads, gbc_lblOverheads);
		
		txtOverheads = new JTextField();
		txtOverheads.setText("0");
		txtOverheads.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtOverheads = new GridBagConstraints();
		gbc_txtOverheads.fill = GridBagConstraints.BOTH;
		gbc_txtOverheads.insets = new Insets(0, 0, 5, 0);
		gbc_txtOverheads.gridwidth = 2;
		gbc_txtOverheads.gridx = 1;
		gbc_txtOverheads.gridy = 7;
		contentPane.add(txtOverheads, gbc_txtOverheads);
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		lblTafeln.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTafeln = new GridBagConstraints();
		gbc_lblTafeln.anchor = GridBagConstraints.SOUTH;
		gbc_lblTafeln.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTafeln.insets = new Insets(0, 0, 5, 5);
		gbc_lblTafeln.gridwidth = 2;
		gbc_lblTafeln.gridx = 0;
		gbc_lblTafeln.gridy = 8;
		contentPane.add(lblTafeln, gbc_lblTafeln);
		
		txtChalkboards = new JTextField();
		txtChalkboards.setText("0");
		txtChalkboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtChalkboards = new GridBagConstraints();
		gbc_txtChalkboards.fill = GridBagConstraints.BOTH;
		gbc_txtChalkboards.insets = new Insets(0, 0, 5, 0);
		gbc_txtChalkboards.gridwidth = 2;
		gbc_txtChalkboards.gridx = 1;
		gbc_txtChalkboards.gridy = 8;
		contentPane.add(txtChalkboards, gbc_txtChalkboards);
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		lblWhiteboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWhiteboards = new GridBagConstraints();
		gbc_lblWhiteboards.anchor = GridBagConstraints.SOUTH;
		gbc_lblWhiteboards.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWhiteboards.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhiteboards.gridwidth = 2;
		gbc_lblWhiteboards.gridx = 0;
		gbc_lblWhiteboards.gridy = 9;
		contentPane.add(lblWhiteboards, gbc_lblWhiteboards);
		
		txtWhiteboards = new JTextField();
		txtWhiteboards.setText("0");
		txtWhiteboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtWhiteboards = new GridBagConstraints();
		gbc_txtWhiteboards.fill = GridBagConstraints.BOTH;
		gbc_txtWhiteboards.insets = new Insets(0, 0, 5, 0);
		gbc_txtWhiteboards.gridwidth = 2;
		gbc_txtWhiteboards.gridx = 1;
		gbc_txtWhiteboards.gridy = 9;
		contentPane.add(txtWhiteboards, gbc_txtWhiteboards);
		GridBagConstraints gbc_btnAbbrechen = new GridBagConstraints();
		gbc_btnAbbrechen.anchor = GridBagConstraints.EAST;
		gbc_btnAbbrechen.fill = GridBagConstraints.VERTICAL;
		gbc_btnAbbrechen.insets = new Insets(0, 0, 0, 5);
		gbc_btnAbbrechen.gridx = 0;
		gbc_btnAbbrechen.gridy = 11;
		contentPane.add(btnAbbrechen, gbc_btnAbbrechen);
		GridBagConstraints gbc_btnSpeichern = new GridBagConstraints();
		gbc_btnSpeichern.fill = GridBagConstraints.BOTH;
		gbc_btnSpeichern.gridx = 2;
		gbc_btnSpeichern.gridy = 11;
		contentPane.add(btnSpeichern, gbc_btnSpeichern);
	}
}
