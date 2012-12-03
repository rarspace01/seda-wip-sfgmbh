package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import de.sfgmbh.comlayer.organisation.controller.CounterproposalFrameWin;
import de.sfgmbh.comlayer.organisation.controller.CounterproposalFrameBtns;

public class CounterproposalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtGebaude;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	public CounterproposalFrame() {
		setTitle("Konfliktl\u00F6sung");
		setBounds(100, 100, 320, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDozent = new JLabel("Dozent:");
		lblDozent.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDozent.setBounds(12, 11, 120, 14);
		contentPane.add(lblDozent);
		
		JLabel lblEmail = new JLabel("Lehrveranstaltung:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPasswort = new JLabel("Raum:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 69, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("Zeit:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 96, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("Nachricht an Dozent:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(12, 127, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.setBounds(181, 236, 90, 28);
		btnSenden.addActionListener(new CounterproposalFrameBtns("send"));
		contentPane.add(btnSenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new CounterproposalFrameBtns("cancle"));
		btnAbbrechen.setBounds(69, 236, 90, 28);
		contentPane.add(btnAbbrechen);
		
		txtGebaude = new JTextField();
		txtGebaude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtGebaude.setColumns(10);
		txtGebaude.setBounds(12, 152, 259, 73);
		contentPane.add(txtGebaude);
		
		JComboBox comboBoxDozent = new JComboBox();
		comboBoxDozent.setModel(new DefaultComboBoxModel(new String[] {"Benker"}));
		comboBoxDozent.setEditable(true);
		comboBoxDozent.setBounds(142, 9, 129, 20);
		contentPane.add(comboBoxDozent);
		
		JComboBox comboBoxRaum = new JComboBox();
		comboBoxRaum.setModel(new DefaultComboBoxModel(new String[] {"05.002"}));
		comboBoxRaum.setEditable(true);
		comboBoxRaum.setBounds(142, 67, 129, 20);
		contentPane.add(comboBoxRaum);
		
		JComboBox comboBoxZeit = new JComboBox();
		comboBoxZeit.setModel(new DefaultComboBoxModel(new String[] {"Mo. 10:00 - 12:00"}));
		comboBoxZeit.setBounds(142, 94, 129, 20);
		contentPane.add(comboBoxZeit);
		
		JComboBox comboBoxLV = new JComboBox();
		comboBoxLV.setModel(new DefaultComboBoxModel(new String[] {"WI-Projekt"}));
		comboBoxLV.setEditable(true);
		comboBoxLV.setBounds(142, 40, 129, 20);
		contentPane.add(comboBoxLV);
		
		addWindowListener(new CounterproposalFrameWin());
	}
}
