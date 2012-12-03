package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import de.sfgmbh.comlayer.lecturer.controller.RoomRequestFrameBtns;
import de.sfgmbh.comlayer.lecturer.controller.CourseFrameWin;

public class RoomRequestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RoomRequestFrame() {
		setTitle("Raumanfrage senden");
		setBounds(100, 100, 475, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel_3 = new JLabel("Veranstaltung: SEDA-WIP-B");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_3, "cell 2 0 2 1");
		
		JLabel lblNewLabel = new JLabel("Geben Sie ihren Raum- und Zeitwunsch an:");
		contentPane.add(lblNewLabel, "cell 1 2 3 1");
		
		JLabel lblStockwer = new JLabel("Raum:");
		contentPane.add(lblStockwer, "cell 1 3");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		contentPane.add(comboBox_2, "cell 3 3,growx");
		
		JLabel lblGewnschteRaum = new JLabel("Tag:");
		contentPane.add(lblGewnschteRaum, "cell 1 4");
		
		JComboBox comboBox_3 = new JComboBox();
		contentPane.add(comboBox_3, "cell 3 4,growx");
		
		JLabel lblZeit = new JLabel("Zeit:");
		contentPane.add(lblZeit, "cell 1 5");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 3 5,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Oder lassen Sie sich einen Raum anhand von Kriterien vorschlagen:");
		contentPane.add(lblNewLabel_1, "cell 1 7 4 1");
		
		JLabel label_4 = new JLabel("Tag:");
		contentPane.add(label_4, "cell 1 8");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Di."}));
		contentPane.add(comboBox_1, "cell 3 8,growx");
		
		JLabel label_5 = new JLabel("Zeit:");
		contentPane.add(label_5, "cell 1 9");
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_11, "cell 3 9,growx");
		
		JLabel lblGebude = new JLabel("Geb\u00E4ude:");
		contentPane.add(lblGebude, "cell 1 10");
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] {"Erba 1"}));
		contentPane.add(comboBox_12, "cell 3 10,growx");
		
		JLabel lblPltze = new JLabel("Pl\u00E4tze:");
		contentPane.add(lblPltze, "cell 1 11");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setEditable(true);
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"30"}));
		contentPane.add(comboBox_4, "cell 3 11,growx");
		
		JLabel lblPcarbeitspltze = new JLabel("PC-Arbeitspl\u00E4tze:");
		contentPane.add(lblPcarbeitspltze, "cell 1 12");
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setEditable(true);
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_5, "cell 3 12,growx");
		
		JLabel lblBeamer = new JLabel("Beamer:");
		contentPane.add(lblBeamer, "cell 1 13");
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setEditable(true);
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_6, "cell 3 13,growx");
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		contentPane.add(lblVisualizer, "cell 1 14");
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setEditable(true);
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_7, "cell 3 14,growx");
		
		JLabel lblOverhead = new JLabel("Overhead:");
		contentPane.add(lblOverhead, "cell 1 15");
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setEditable(true);
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_8, "cell 3 15,growx");
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		contentPane.add(lblTafeln, "cell 1 16");
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setEditable(true);
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_9, "cell 3 16,growx");
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		contentPane.add(lblWhiteboards, "cell 1 17");
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setEditable(true);
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"<egal>"}));
		contentPane.add(comboBox_10, "cell 3 17,growx");
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new RoomRequestFrameBtns("cancle"));
		contentPane.add(btnAbbrechen, "cell 3 19,alignx right");
		
		JButton btnNewButton = new JButton("Los!");
		btnNewButton.addActionListener(new RoomRequestFrameBtns("go"));
		contentPane.add(btnNewButton, "cell 4 19");
		
		addWindowListener(new CourseFrameWin());
	}

}
