package modules.verwaltung.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.verwaltung.controller.NutzerMaskeWindow;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VerwaltungNutzerMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNutzerkennung;
	private JTextField txtPasswort;
	private JTextField txtEmail;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VerwaltungNutzerMaskeWindow() {
		setTitle("Nutzer hinzuf\u00FCgen");
		setBounds(100, 100, 266, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Nutzerkennung:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 11, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 69, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("Nutzerklasse:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 96, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("Lehrstuhl:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(12, 127, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		txtNutzerkennung = new JTextField();
		txtNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNutzerkennung.setBounds(115, 9, 116, 20);
		contentPane.add(txtNutzerkennung);
		txtNutzerkennung.setColumns(10);
		
		txtPasswort = new JTextField();
		txtPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPasswort.setColumns(10);
		txtPasswort.setBounds(115, 67, 116, 20);
		contentPane.add(txtPasswort);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(115, 40, 116, 20);
		contentPane.add(txtEmail);
		
		JComboBox comboBoxNutzerklasse = new JComboBox();
		comboBoxNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxNutzerklasse.setModel(new DefaultComboBoxModel(new String[] {"Verwaltung", "Dozent", "Student"}));
		comboBoxNutzerklasse.setBounds(115, 94, 116, 20);
		contentPane.add(comboBoxNutzerklasse);
		
		JComboBox comboBoxLehrstuhl = new JComboBox();
		comboBoxLehrstuhl.setEditable(true);
		comboBoxLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		comboBoxLehrstuhl.setBounds(115, 125, 116, 20);
		contentPane.add(comboBoxLehrstuhl);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(141, 179, 90, 28);
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(40, 179, 90, 28);
		contentPane.add(btnAbbrechen);
		
		addWindowListener(new NutzerMaskeWindow());
	}
}
