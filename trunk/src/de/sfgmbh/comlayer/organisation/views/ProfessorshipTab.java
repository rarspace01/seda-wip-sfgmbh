package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.organisation.controller.BtnsNav;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.ProfessorshipTabBtnsControl;
import de.sfgmbh.init.Bootstrap;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class ProfessorshipTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProfessorshipname;
	private JTable roomTable;
	private JLabel lblProfessorshipname;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnRequest;
	private JButton btnDelete;
	private JButton btnLivetickerEdit;
	private JTextPane tickerMsgPos1;
	private JButton btnFailureprompt;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ProfessorshipTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[][][grow][]"));
		
		JLabel lblLehrstuhlverwaltung = new JLabel("Lehrstuhlverwaltung");
		lblLehrstuhlverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblLehrstuhlverwaltung, "cell 0 0,alignx center");
		
		lblProfessorshipname = new JLabel("Lehrstuhlname:");
		add(lblProfessorshipname, "cell 2 0");
		
		textFieldProfessorshipname = new JTextField();
		textFieldProfessorshipname.addKeyListener(new CmbboxFilter());
		textFieldProfessorshipname.setText("<alle>");
		textFieldProfessorshipname.setColumns(10);
		add(textFieldProfessorshipname, "cell 2 1,growx");
		
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
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. \r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.\r\n");
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setBounds(6, 11, 134, 291);
		leftTopPanel.add(tickerMsgPos1);
		
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
		
		btnLivetickerEdit = new JButton("LiveTicker");
		btnLivetickerEdit.addActionListener(new BtnsNav("liveticker"));
		leftBottomPanel.add(btnLivetickerEdit, "cell 0 3");
		
		btnRequest = new JButton("Anfragen");
		btnRequest.addActionListener(new BtnsNav("Anfrageverw"));
		leftBottomPanel.add(btnRequest, "cell 0 4");
		
		organisationTableScrollPane = new JScrollPane();
		organisationTableScrollPane.setMinimumSize(new Dimension(400, 25));
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		roomTable = new JTable();
		roomTable.setModel(Bootstrap.serviceManager.getOrgaProfessorshipTableModel());
		roomTable.setShowVerticalLines(false);
		roomTable.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane.setViewportView(roomTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnAdd = new JButton("hinzuf\u00FCgen");
		btnAdd.addActionListener(new ProfessorshipTabBtnsControl("hinz"));
		btnAdd.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnAdd);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new ProfessorshipTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new ProfessorshipTabBtnsControl("loschen"));
		btnDelete.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnDelete);
		
		btnFailureprompt = new JButton("Fehlermeldung");
		btnFailureprompt.addActionListener(new ProfessorshipTabBtnsControl("Fehlermeldung"));
		add(btnFailureprompt, "cell 2 3");
		
	}

	
}