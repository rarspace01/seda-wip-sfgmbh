package de.sfgmbh.comlayer.organisation.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import de.sfgmbh.comlayer.organisation.controller.ProfessorshipFrameWin;
import de.sfgmbh.comlayer.organisation.controller.ProfessorshipFrameBtns;

public class ProfessorshipFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLehrstuhlname;
	private JTextField txtFak;
	private JTextField txtInhaber;
	private JTextField txtGebaude;
	private JTextField txtStock;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ProfessorshipFrame() {
		setTitle("Lehrst\u00FChle hinzuf\u00FCgen/bearbeiten");
		setBounds(100, 100, 266, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Lehrstuhlname:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 11, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("Inhaber:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPasswort = new JLabel("Fakult\u00E4t:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 69, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("(Haupt-)Geb\u00E4ude:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 96, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("(Haupt-)Stock:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(12, 127, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		txtLehrstuhlname = new JTextField();
		txtLehrstuhlname.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLehrstuhlname.setBounds(115, 9, 116, 20);
		contentPane.add(txtLehrstuhlname);
		txtLehrstuhlname.setColumns(10);
		
		txtFak = new JTextField();
		txtFak.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFak.setColumns(10);
		txtFak.setBounds(115, 67, 116, 20);
		contentPane.add(txtFak);
		
		txtInhaber = new JTextField();
		txtInhaber.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtInhaber.setColumns(10);
		txtInhaber.setBounds(115, 40, 116, 20);
		contentPane.add(txtInhaber);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(141, 179, 90, 28);
		btnSpeichern.addActionListener(new ProfessorshipFrameBtns("save"));
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(40, 179, 90, 28);
		btnAbbrechen.addActionListener(new ProfessorshipFrameBtns("cancle"));
		contentPane.add(btnAbbrechen);
		
		txtGebaude = new JTextField();
		txtGebaude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtGebaude.setColumns(10);
		txtGebaude.setBounds(115, 94, 116, 20);
		contentPane.add(txtGebaude);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtStock.setColumns(10);
		txtStock.setBounds(115, 125, 116, 20);
		contentPane.add(txtStock);
		
		addWindowListener(new ProfessorshipFrameWin());
	}
}
