package de.sfgmbh.comlayer.core.views;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class LiveTickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea txtHeader;
	private JTextPane txtTicker;
	
	/**
	 * Create the panel.
	 */
	public LiveTickerPanel() {

		createContents();
	}
	private void createContents() {
		setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), null));
		setLayout(null);
		setBounds(0, 6, 140, 310);
		add(getTxtHeader());
		add(getTxtTicker());
	}
	public JTextArea getTxtHeader() {
		if (txtHeader == null) {
			txtHeader = new JTextArea();
			txtHeader.setOpaque(false);
			txtHeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtHeader.setEditable(false);
			txtHeader.setText("Infos & nahe Termine:");
			txtHeader.setBounds(5, 5, 130, 18);
		}
		return txtHeader;
	}
	public JTextPane getTxtTicker() {
		if (txtTicker == null) {
			txtTicker = new JTextPane();
			txtTicker.setOpaque(false);
			txtTicker.setEditable(false);
			txtTicker.setContentType("text/html");
			txtTicker.setBounds(5, 21, 130, 285);
			txtTicker.setText("<div style=\"font-family: Tahoma, Calibri, monospace; font-size: 11pt;\"><strong>Logindaten:</strong><br>Dozenten:<br>Doz // Doz <br><br>Studenten:<br>Stud // Stud<br><br>Verwaltung:<br>Verw // Verw <br><br> Anderenfalls Fehler!</div>");
		}
		return txtTicker;
	}
}
