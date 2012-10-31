package modules.verwaltung.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.verwaltung.controller.RaumMaskeWindow;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VerwaltungRaumMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNutzerkennung;
	private JTextField txtPasswort;
	private JTextField txtEmail;
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
	public VerwaltungRaumMaskeWindow() {
		setBounds(100, 100, 266, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Raumnummer:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 11, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("Geb\u00E4ude:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(12, 42, 120, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPasswort = new JLabel("Stockwerk:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 69, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("Pl\u00E4tze:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 96, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("PC-Pl\u00E4tze:");
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
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(148, 322, 90, 28);
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(47, 322, 90, 28);
		contentPane.add(btnAbbrechen);
		
		JLabel lblBeamer = new JLabel("Beamer:");
		lblBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblBeamer.setBounds(12, 161, 120, 14);
		contentPane.add(lblBeamer);
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		lblVisualizer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblVisualizer.setBounds(12, 192, 120, 14);
		contentPane.add(lblVisualizer);
		
		JLabel lblOverheads = new JLabel("Overheads:");
		lblOverheads.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblOverheads.setBounds(12, 219, 120, 14);
		contentPane.add(lblOverheads);
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		lblTafeln.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTafeln.setBounds(12, 246, 120, 14);
		contentPane.add(lblTafeln);
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		lblWhiteboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblWhiteboards.setBounds(12, 277, 120, 14);
		contentPane.add(lblWhiteboards);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(115, 155, 116, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(115, 128, 116, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(115, 97, 116, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(115, 190, 116, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(115, 217, 116, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(115, 244, 116, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(115, 275, 116, 20);
		contentPane.add(textField_6);
		
		addWindowListener(new RaumMaskeWindow());
	}
}
