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

public class RoomFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNutzerkennung;
	private JTextField txtPasswort;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public RoomFrame() {
		initialize();
	}
	private void initialize() {
		setTitle("Raum Info");
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
		
		txtNutzerkennung = new JTextField();
		txtNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtNutzerkennung = new GridBagConstraints();
		gbc_txtNutzerkennung.fill = GridBagConstraints.BOTH;
		gbc_txtNutzerkennung.insets = new Insets(0, 0, 5, 0);
		gbc_txtNutzerkennung.gridwidth = 2;
		gbc_txtNutzerkennung.gridx = 1;
		gbc_txtNutzerkennung.gridy = 1;
		contentPane.add(txtNutzerkennung, gbc_txtNutzerkennung);
		
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
		
		txtPasswort = new JTextField();
		txtPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_txtPasswort = new GridBagConstraints();
		gbc_txtPasswort.fill = GridBagConstraints.BOTH;
		gbc_txtPasswort.insets = new Insets(0, 0, 5, 0);
		gbc_txtPasswort.gridwidth = 2;
		gbc_txtPasswort.gridx = 1;
		gbc_txtPasswort.gridy = 2;
		contentPane.add(txtPasswort, gbc_txtPasswort);
		
		JLabel lblNutzerklasse = new JLabel("Pl\u00E4tze:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNutzerklasse = new GridBagConstraints();
		gbc_lblNutzerklasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNutzerklasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblNutzerklasse.gridwidth = 2;
		gbc_lblNutzerklasse.gridx = 0;
		gbc_lblNutzerklasse.gridy = 3;
		contentPane.add(lblNutzerklasse, gbc_lblNutzerklasse);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		contentPane.add(textField_2, gbc_textField_2);
		
		JLabel lblLehrstuhl = new JLabel("PC-Pl\u00E4tze:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLehrstuhl = new GridBagConstraints();
		gbc_lblLehrstuhl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLehrstuhl.insets = new Insets(0, 0, 5, 5);
		gbc_lblLehrstuhl.gridwidth = 2;
		gbc_lblLehrstuhl.gridx = 0;
		gbc_lblLehrstuhl.gridy = 4;
		contentPane.add(lblLehrstuhl, gbc_lblLehrstuhl);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		contentPane.add(textField_1, gbc_textField_1);
		
		JLabel lblBeamer = new JLabel("Beamer:");
		lblBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBeamer = new GridBagConstraints();
		gbc_lblBeamer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeamer.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeamer.gridwidth = 2;
		gbc_lblBeamer.gridx = 0;
		gbc_lblBeamer.gridy = 5;
		contentPane.add(lblBeamer, gbc_lblBeamer);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridwidth = 2;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		
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
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 6;
		contentPane.add(textField_3, gbc_textField_3);
		
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
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridwidth = 2;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 7;
		contentPane.add(textField_4, gbc_textField_4);
		
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
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridwidth = 2;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 8;
		contentPane.add(textField_5, gbc_textField_5);
		
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
		
		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridwidth = 2;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 9;
		contentPane.add(textField_6, gbc_textField_6);
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
