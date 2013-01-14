package de.sfgmbh.comlayer.core.views;

import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.InfoDialogBtnOk;

public class InfoDialog extends JDialog implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JTextPane txtpnInfoDialogText;
	private JButton btnOk;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public InfoDialog() {
		createContents();
	}
	public InfoDialog(String info) {
		this.setDialog(info);
	}
	public InfoDialog(String info, String title) {
		this.setDialog(info, title);
	}
	
	public void setDialog(String info) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri, monospace; text-align: left;'>" + info + "</div>");
		createContents();
	}
	
	public void setDialog(String info, String title) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri,monospace; text-align: left;'>" + info + "</div>");
		createContents(title);
	}
	
	private void createContents() {
		this.createContents("Fehler");
	}
	private void createContents(String title) {
		setTitle(title);
		setModal(true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new MigLayout("", "[5px:n][center][5px:n][250px:n:500px,center][10px:n:10px]", "[100px:n:400px,center][40px:n:40px,center]"));
		getContentPane().add(getLblNewLabel(), "cell 1 0,alignx center,aligny center");
		getContentPane().add(getTxtpnInfoWindowText(), "cell 3 0,alignx left,aligny center");
		getContentPane().add(getBtnOk(), "cell 3 1,alignx right,aligny center");
		pack();
		setLocationRelativeTo(null);
		
		// Do the resizing
		Integer infoHeight = this.getTxtpnInfoWindowText().getHeight() + 95;
		if (infoHeight < 195) {
			infoHeight = 195;
		}
		this.setSize(this.getWidth(), (infoHeight));
	}
	
	public JTextPane getTxtpnInfoWindowText() {
		if (txtpnInfoDialogText == null) {
			txtpnInfoDialogText = new JTextPane();
			txtpnInfoDialogText.setEditable(false);
			txtpnInfoDialogText.setContentType("text/html");
			txtpnInfoDialogText.setSize(new Dimension(250, 150));
			txtpnInfoDialogText.setBackground(SystemColor.control);
			txtpnInfoDialogText.setText("<div style='font-family: Calibri, monospace; text-align: left;'>Info Window Text</div>");
		}
		return txtpnInfoDialogText;
	}
	
	
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.setPreferredSize(new Dimension(75, 23));
			btnOk.addActionListener(new InfoDialogBtnOk(this));
		}
		return btnOk;
	}
	
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/views/InfoDialogIcon.png")));
			lblNewLabel.setMaximumSize(new Dimension(100, 100));
		}
		return lblNewLabel;
	}
	
	@Override
	public void change() {
		String msg = AppModel.getInstance().getExceptionHandler().getExceptionMsg_();
		String title = AppModel.getInstance().getExceptionHandler().getExceptionTitle_();
		
		if (title != null) {
			InfoDialog span = new InfoDialog(msg, title);
			span.setVisible(true);
		} else {
			InfoDialog span = new InfoDialog(msg);
			span.setVisible(true);
		}
	}
}
