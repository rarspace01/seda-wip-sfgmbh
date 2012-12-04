package de.sfgmbh.comlayer.core.views;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextPane;

import de.sfgmbh.comlayer.core.controller.InfoDialogBtnOk;

import java.awt.Dimension;
import java.awt.SystemColor;
import net.miginfocom.swing.MigLayout;

public class InfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextPane txtpnInfoDialogText;
	private JButton btnOk;

	/**
	 * Create the frame.
	 */
	public InfoDialog() {
		createContents();
		
	}
	public InfoDialog(String info) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Tahoma, Geneva, sans-serif; text-align: center;'>" + info + "</div>");
		createContents();
	}
	public InfoDialog(String info, String title) {
		if (info != null) {
			this.getTxtpnInfoWindowText().setText("<div style='font-family: Tahoma, Geneva, sans-serif; text-align: center;'>" + info + "</div>");
		}
		if (title != null) {
			createContents(title);
		} else {
			createContents();
		}
	}
	
	private void createContents() {
		setModal(true);
		setAlwaysOnTop(true);
		this.createContents("Fehler");
	}
	private void createContents(String title) {
		setTitle(title);
		setMinimumSize(new Dimension(215, 250));
		getContentPane().setLayout(new MigLayout("", "[180px:n,center][]", "[150px:n][23px][][][][][][][]"));
		getContentPane().add(getTxtpnInfoWindowText(), "cell 0 0 2 8,alignx center,aligny center");
		setLocationRelativeTo(null);
		getContentPane().add(getBtnOk(), "cell 0 8,alignx center,aligny center");
	}
	
	public JTextPane getTxtpnInfoWindowText() {
		if (txtpnInfoDialogText == null) {
			txtpnInfoDialogText = new JTextPane();
			txtpnInfoDialogText.setContentType("text/html");
			txtpnInfoDialogText.setSize(new Dimension(250, 150));
			txtpnInfoDialogText.setBackground(SystemColor.control);
			txtpnInfoDialogText.setText("<div style='font-family: Tahoma, Geneva, sans-serif; text-align: center;'>Info Window Text</div>");
		}
		return txtpnInfoDialogText;
	}
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.setPreferredSize(new Dimension(60, 23));
			btnOk.addActionListener(new InfoDialogBtnOk(this));
		}
		return btnOk;
	}
	
}
