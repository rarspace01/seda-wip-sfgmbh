package modules.dozenten.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.dozenten.controller.LVMaskeWindow;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DozentenLVMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public DozentenLVMaskeWindow() {
		setTitle("Lehrveranstaltungen hinzuf\u00FCgen");
		setBounds(100, 100, 367, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][]", "[][][][][][][][][][][][][][][][]"));
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2, "cell 5 0");
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1, "cell 6 0");
		
		JLabel label = new JLabel("");
		contentPane.add(label, "cell 7 0");
		
		JLabel lblNewLabel_2 = new JLabel("");
		contentPane.add(lblNewLabel_2, "cell 8 0");
		
		JLabel lblStockwer = new JLabel("Lehrstuhl:");
		contentPane.add(lblStockwer, "cell 1 2");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"SEDA", "ISDL", "IIS"}));
		contentPane.add(comboBox_2, "cell 3 2,growx");
		
		JLabel lblGewnschteRaum = new JLabel("Dozent:");
		contentPane.add(lblGewnschteRaum, "cell 1 3");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Wolf", "Benker", "Sinz"}));
		contentPane.add(comboBox_3, "cell 3 3,growx");
		
		JLabel lblPltze = new JLabel("Bezeichnung:");
		contentPane.add(lblPltze, "cell 1 4");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 4,growx");
		textField.setColumns(10);
		
		JLabel lblTafeln = new JLabel("SWS:");
		contentPane.add(lblTafeln, "cell 1 6");
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "6", "8"}));
		contentPane.add(comboBox_9, "cell 3 6,growx");
		
		JLabel lblWhiteboards = new JLabel("Uhrzeit:");
		contentPane.add(lblWhiteboards, "cell 1 7");
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"08:00-10:00 Uhr", "10:00-12:00 Uhr", "12:00-14:00 Uhr", "14:00-16:00 Uhr", "16:00-18:00 Uhr", "18:00-20:00 Uhr"}));
		contentPane.add(comboBox_10, "cell 3 7,growx");
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		contentPane.add(btnAbbrechen, "cell 1 8");
		
		JButton btnNewButton = new JButton("Raumanfrage senden");
		contentPane.add(btnNewButton, "cell 3 8");
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3, "cell 4 8");
		
		addWindowListener(new LVMaskeWindow());
	}

}
