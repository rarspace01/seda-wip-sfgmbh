package modules.verwaltung.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.verwaltung.controller.RaumMaskeWindow;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VerwaltungRaumMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNutzerkennung;
	private JTextField txtPasswort;
	private JComboBox textField;
	private JComboBox textField_1;
	private JComboBox textField_2;
	private JComboBox textField_3;
	private JComboBox textField_4;
	private JComboBox textField_5;
	private JComboBox textField_6;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public VerwaltungRaumMaskeWindow() {
		setTitle("Raum hinzuf\u00FCgen");
		setBounds(100, 100, 266, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Raumnummer:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(12, 36, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblPasswort = new JLabel("Stockwerk:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(12, 12, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("Pl\u00E4tze:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(12, 69, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("PC-Pl\u00E4tze:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(12, 100, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		txtNutzerkennung = new JTextField();
		txtNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNutzerkennung.setBounds(115, 9, 116, 20);
		contentPane.add(txtNutzerkennung);
		
		txtPasswort = new JTextField();
		txtPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPasswort.setBounds(115, 36, 116, 20);
		contentPane.add(txtPasswort);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(133, 288, 90, 28);
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(23, 288, 90, 28);
		contentPane.add(btnAbbrechen);
		
		JLabel lblBeamer = new JLabel("Beamer:");
		lblBeamer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblBeamer.setBounds(12, 134, 120, 14);
		contentPane.add(lblBeamer);
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		lblVisualizer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblVisualizer.setBounds(12, 165, 120, 14);
		contentPane.add(lblVisualizer);
		
		JLabel lblOverheads = new JLabel("Overheads:");
		lblOverheads.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblOverheads.setBounds(12, 192, 120, 14);
		contentPane.add(lblOverheads);
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		lblTafeln.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTafeln.setBounds(12, 219, 120, 14);
		contentPane.add(lblTafeln);
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		lblWhiteboards.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblWhiteboards.setBounds(12, 250, 120, 14);
		contentPane.add(lblWhiteboards);
		
		textField = new JComboBox();
		textField.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setBounds(115, 131, 116, 20);
		contentPane.add(textField);
		
		textField_1 = new JComboBox();
		textField_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_1.setBounds(115, 97, 116, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JComboBox();
		textField_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_2.setBounds(115, 66, 116, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JComboBox();
		textField_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_3.setBounds(115, 159, 116, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JComboBox();
		textField_4.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_4.setBounds(115, 186, 116, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JComboBox();
		textField_5.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_5.setBounds(115, 213, 116, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JComboBox();
		textField_6.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		textField_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_6.setBounds(115, 244, 116, 20);
		contentPane.add(textField_6);
		
		addWindowListener(new RaumMaskeWindow());
	}
}
