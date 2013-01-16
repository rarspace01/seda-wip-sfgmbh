package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.ProfessorshipTabBtnsControl;

/**
 * 
 * @author Anna
 *
 */
public class ProfessorshipTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProfessorshipname;
	private JTable roomTable;
	private JLabel lblProfessorshipname;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnDelete;
	private JTextPane tickerMsgPos1;
	private JButton btnFailureprompt;
	private JPanel uniIconPanel;
	private JLabel lblUniIcon;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ProfessorshipTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 50));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow][grow][grow][100px:100px:100px]", "[][][grow][bottom]"));
		
		JLabel lblLehrstuhlverwaltung = new JLabel("Lehrstuhlverwaltung");
		lblLehrstuhlverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblLehrstuhlverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblProfessorshipname = new JLabel("Lehrstuhlname:");
		add(lblProfessorshipname, "cell 2 0,aligny bottom");
		
		textFieldProfessorshipname = new JTextField();
		textFieldProfessorshipname.addActionListener(new CmbboxFilter());
		
		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx center,growy");
		lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
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
		leftTopPanel.setBounds(0, 0, 140, 393);
		leftPanel.add(leftTopPanel);
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. \r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.\r\n");
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setBounds(6, 11, 124, 365);
		leftTopPanel.add(tickerMsgPos1);
		
		organisationTableScrollPane = new JScrollPane();
		organisationTableScrollPane.setMinimumSize(new Dimension(400, 25));
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		roomTable = new JTable();
		roomTable.setModel(ViewManager.getInstance().getOrgaProfessorshipTableModel());
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
		
		btnFailureprompt = new JButton("Ausloggen");
		btnFailureprompt.addActionListener(new ProfessorshipTabBtnsControl("Fehlermeldung"));
		add(btnFailureprompt, "cell 2 3");
		
	}
	/**
	 * @return the textFieldProfessorshipname
	 */
	public JTextField getTextFieldProfessorshipname() {
		return textFieldProfessorshipname;
	}
	
}