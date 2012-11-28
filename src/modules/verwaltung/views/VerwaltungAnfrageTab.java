package modules.verwaltung.views;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modules.verwaltung.controller.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.Font;

public class VerwaltungAnfrageTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable raumverwaltungTable;
	private JLabel lblDozent;
	private JLabel lblLehrstuhl;
	private JLabel lblStatus;
	private JLabel lblPcpltze;
	private JComboBox<String> comboBoxDozent;
	private JComboBox<String> comboBoxLehrstuhl;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JScrollPane verwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnFreigeben;
	private JComboBox<String> comboBoxStatus;
	private JComboBox<String> comboBoxSemester;
	private JButton btnRäume;
	private JButton btnLivetickerBearbeiten;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VerwaltungAnfrageTab() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		JLabel lblRaumverwaltung = new JLabel("Raumanfragen");
		lblRaumverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRaumverwaltung, "cell 0 0,alignx center");
		
		lblDozent = new JLabel("Dozent:");
		add(lblDozent, "cell 2 0");
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		add(lblLehrstuhl, "cell 3 0");
		
		lblStatus = new JLabel("Freigabestatus:");
		add(lblStatus, "cell 4 0");
		
		lblPcpltze = new JLabel("Semester:");
		add(lblPcpltze, "cell 5 0");
		
		comboBoxDozent = new JComboBox<String>();
		comboBoxDozent.addActionListener(new CmbboxFilter());
		comboBoxDozent.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxDozent.setEditable(true);
		comboBoxDozent.setAutoscrolls(true);
		add(comboBoxDozent, "cell 2 1,growx");
		
		comboBoxLehrstuhl = new JComboBox<String>();
		comboBoxLehrstuhl.addActionListener(new CmbboxFilter());
		comboBoxLehrstuhl.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxLehrstuhl.setEditable(true);
		comboBoxLehrstuhl.setAutoscrolls(true);
		add(comboBoxLehrstuhl, "cell 3 1,growx");
		
		comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.addActionListener(new CmbboxFilter());
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxStatus.setEditable(true);
		comboBoxStatus.setAutoscrolls(true);
		add(comboBoxStatus, "cell 4 1,growx");
		
		comboBoxSemester = new JComboBox<String>();
		comboBoxSemester.addActionListener(new CmbboxFilter());
		comboBoxSemester.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		add(comboBoxSemester, "cell 5 1,growx");
		
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setMaximumSize(new Dimension(140, 32767));
		leftPanel.setBorder(null);
		add(leftPanel, "cell 0 2,grow");
		
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(null);
		leftTopPanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		leftTopPanel.setBounds(0, 6, 140, 313);
		leftPanel.add(leftTopPanel);
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setBounds(6, 6, 55, 16);
		leftTopPanel.add(lblInfo);
		
		leftBottomPanel = new JPanel();
		leftBottomPanel.setBounds(0, 319, 140, 161);
		leftPanel.add(leftBottomPanel);
		leftBottomPanel.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		JLabel lblAndereBereiche = new JLabel("Verwaltungs-Bereiche:");
		leftBottomPanel.add(lblAndereBereiche, "cell 0 0");
		
		JButton btnNutzerverwaltung = new JButton("Nutzer");
		btnNutzerverwaltung.addActionListener(new BtnsNav("Nutzerverw"));
		leftBottomPanel.add(btnNutzerverwaltung, "cell 0 1");
		
		btnRäume = new JButton("R\u00E4ume");
		btnRäume.addActionListener(new BtnsNav("Raumverw"));
		leftBottomPanel.add(btnRäume, "cell 0 2");
		
		JButton btnLehrstuhlverwaltung = new JButton("Lehrst\u00FChle");
		btnLehrstuhlverwaltung.addActionListener(new BtnsNav("Lehrstuhlverw"));
		
		btnLivetickerBearbeiten = new JButton("LiveTicker");
		btnLivetickerBearbeiten.addActionListener(new BtnsNav("LiveTicker"));
		leftBottomPanel.add(btnLivetickerBearbeiten, "cell 0 3");
		leftBottomPanel.add(btnLehrstuhlverwaltung, "cell 0 4");
		
		verwaltungTableScrollPane = new JScrollPane();
		add(verwaltungTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setColumnHeaderView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(120, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnFreigeben = new JButton("freigeben");
		btnFreigeben.setBounds(6, 11, 88, 23);
		btnFreigeben.addActionListener(new AnfrageTabBtnsControl("freigeben"));
		buttonPanel.add(btnFreigeben);
		
		JButton btnAblehnen = new JButton("ablehnen");
		btnAblehnen.setBounds(6, 45, 88, 23);
		btnAblehnen.addActionListener(new AnfrageTabBtnsControl("ablehnen"));
		buttonPanel.add(btnAblehnen);
		
		JButton btnGegenvorschlag = new JButton("Konfliktl\u00F6sung");
		btnGegenvorschlag.setMinimumSize(new Dimension(90, 23));
		btnGegenvorschlag.setMaximumSize(new Dimension(90, 23));
		btnGegenvorschlag.setPreferredSize(new Dimension(50, 23));
		btnGegenvorschlag.addActionListener(new AnfrageTabBtnsControl("Konflikt"));
		btnGegenvorschlag.setBounds(6, 79, 99, 23);
		buttonPanel.add(btnGegenvorschlag);
		
	}
}