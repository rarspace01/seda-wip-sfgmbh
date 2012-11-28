package modules.verwaltung.views;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modules.verwaltung.controller.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;

public class VerwaltungLehrstuhlTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldLehrstuhlname;
	private JTable raumverwaltungTable;
	private JLabel lblLehrstuhlname;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JScrollPane verwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnHinzufugen;
	private JButton btnAnfragen;
	private JButton btnLschen;
	private JButton btnLivetickerBeabeiten;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public VerwaltungLehrstuhlTab() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		JLabel lblLehrstuhlverwaltung = new JLabel("Lehrstuhlverwaltung");
		lblLehrstuhlverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblLehrstuhlverwaltung, "cell 0 0,alignx center");
		
		lblLehrstuhlname = new JLabel("Lehrstuhlname:");
		add(lblLehrstuhlname, "cell 2 0");
		
		textFieldLehrstuhlname = new JTextField();
		textFieldLehrstuhlname.addKeyListener(new CmbboxFilter());
		textFieldLehrstuhlname.setText("<alle>");
		textFieldLehrstuhlname.setColumns(10);
		add(textFieldLehrstuhlname, "cell 2 1,growx");
		
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
		
		JButton btnRaumverwaltung = new JButton("R\u00E4ume");
		btnRaumverwaltung.addActionListener(new BtnsNav("Raumverw"));
		leftBottomPanel.add(btnRaumverwaltung, "cell 0 2");
		
		btnLivetickerBeabeiten = new JButton("LiveTicker");
		btnLivetickerBeabeiten.addActionListener(new BtnsNav("LiveTicker"));
		leftBottomPanel.add(btnLivetickerBeabeiten, "cell 0 3");
		
		btnAnfragen = new JButton("Anfragen");
		btnAnfragen.addActionListener(new BtnsNav("Anfrageverw"));
		leftBottomPanel.add(btnAnfragen, "cell 0 4");
		
		verwaltungTableScrollPane = new JScrollPane();
		verwaltungTableScrollPane.setMinimumSize(new Dimension(400, 25));
		add(verwaltungTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setColumnHeaderView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnHinzufugen = new JButton("hinzuf\u00FCgen");
		btnHinzufugen.addActionListener(new LehrstuhlTabBtnsControl("hinz"));
		btnHinzufugen.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnHinzufugen);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new LehrstuhlTabBtnsControl("bearbeiten"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnLschen = new JButton("l\u00F6schen");
		btnLschen.addActionListener(new LehrstuhlTabBtnsControl("loschen"));
		btnLschen.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnLschen);
		
	}

	
}