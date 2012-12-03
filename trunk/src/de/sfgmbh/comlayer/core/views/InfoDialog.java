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
		this.getTxtpnInfoWindowText().setText(info);
		createContents();
	}
	public InfoDialog(String info, String title) {
		if (info != null) {
			this.getTxtpnInfoWindowText().setText(info);
		}
		if (info != null) {
			createContents(info);
		} else {
			createContents();
		}
	}
	
	private void createContents() {
		setModal(true);
		setAlwaysOnTop(true);
		this.createContents("Info");
	}
	private void createContents(String title) {
		setTitle(title);
		setMinimumSize(new Dimension(215, 250));
		getContentPane().setLayout(new MigLayout("", "[180px:n,center]", "[150px:n][23px]"));
		getContentPane().add(getTxtpnInfoWindowText(), "cell 0 0,alignx center,aligny center");
		setLocationRelativeTo(null);
		getContentPane().add(getBtnOk(), "cell 0 1,alignx center,aligny center");
	}
	
	public JTextPane getTxtpnInfoWindowText() {
		if (txtpnInfoDialogText == null) {
			txtpnInfoDialogText = new JTextPane();
			txtpnInfoDialogText.setContentType("text/html");
			txtpnInfoDialogText.setSize(new Dimension(250, 150));
			txtpnInfoDialogText.setBackground(SystemColor.control);
			txtpnInfoDialogText.setText("Info Window Text");
		}
		return txtpnInfoDialogText;
	}
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addActionListener(new InfoDialogBtnOk(this));
		}
		return btnOk;
	}
	
}
