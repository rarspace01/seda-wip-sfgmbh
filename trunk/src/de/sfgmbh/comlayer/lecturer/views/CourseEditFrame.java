package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.lecturer.controller.CourseFrameBtns;
import de.sfgmbh.comlayer.lecturer.controller.CourseFrameWin;
import java.awt.Toolkit;

public class CourseEditFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public CourseEditFrame() {
		initialize();
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CourseEditFrame.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Lehrveranstaltungen bearbeiten");
		setBounds(100, 100, 367, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][]", "[][][][][][][][][][][]"));
		
		JLabel lblStockwer = new JLabel("Lehrstuhl:");
		contentPane.add(lblStockwer, "cell 1 1");
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setEditable(true);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"SEDA", "ISDL", "IIS"}));
		contentPane.add(comboBox_2, "cell 3 1,growx");
		
		JLabel lblGewnschteRaum = new JLabel("Dozent:");
		contentPane.add(lblGewnschteRaum, "cell 1 2");
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setEditable(true);
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"Wolf", "Benker", "Sinz"}));
		contentPane.add(comboBox_3, "cell 3 2,growx");
		
		JLabel lblPltze = new JLabel("Bezeichnung:");
		contentPane.add(lblPltze, "cell 1 3");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 3,growx");
		textField.setColumns(10);
		
		JLabel lblArt = new JLabel("Art:");
		contentPane.add(lblArt, "cell 1 4");
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"\u00DCbung"}));
		contentPane.add(comboBox_1, "cell 3 4,growx");
		
		JLabel lblSemester = new JLabel("Semester:");
		contentPane.add(lblSemester, "cell 1 5");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"WS1213"}));
		contentPane.add(comboBox, "cell 3 5,growx");
		
		JLabel lblTafeln = new JLabel("SWS:");
		contentPane.add(lblTafeln, "cell 1 6");
		
		JComboBox<String> comboBox_9 = new JComboBox<String>();
		comboBox_9.setModel(new DefaultComboBoxModel<String>(new String[] {"2", "4", "6", "8"}));
		contentPane.add(comboBox_9, "cell 3 6,growx");
		
		JLabel lblWhiteboards = new JLabel("Erw. Teilnehmer:");
		contentPane.add(lblWhiteboards, "cell 1 7");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		contentPane.add(textField_1, "cell 3 7,growx");
		
		JLabel lblBeschreibung = new JLabel("Beschreibung:");
		contentPane.add(lblBeschreibung, "cell 1 8");
		
		textField_2 = new JTextField();
		textField_2.setMinimumSize(new Dimension(6, 40));
		textField_2.setColumns(10);
		contentPane.add(textField_2, "cell 3 8 1 2,growx");
		
		JButton btnNewButton = new JButton("Abbrechen");
		btnNewButton.addActionListener(new CourseFrameBtns("cancle"));
		contentPane.add(btnNewButton, "cell 3 10,alignx right");
		
		JButton btnAbbrechen = new JButton("Speichern");
		btnAbbrechen.addActionListener(new CourseFrameBtns("save"));
		contentPane.add(btnAbbrechen, "cell 4 10");
		
		addWindowListener(new CourseFrameWin());
	}


}
