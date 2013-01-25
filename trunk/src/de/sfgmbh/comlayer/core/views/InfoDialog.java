package de.sfgmbh.comlayer.core.views;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.InfoDialogBtnOk;

/**
 * Modal dialog for all kinds of system messages
 * 
 * @author hannes
 *
 */
public class InfoDialog extends JDialog implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JTextPane txtpnInfoDialogText_;
	private JButton btnOk_;
	private JLabel lblIcon_;

	/**
	 * Create the dialog with default message, title and settings
	 */
	public InfoDialog() {
		createContents();
	}
	
	/**
	 * Create the dialog with a custom text and a default title and settings
	 * @param text
	 */
	public InfoDialog(String text) {
		this.setDialog(text);
	}
	
	/**
	 * Create the dialog with a custom text and title and default settings
	 * @param text
	 * @param title
	 */
	public InfoDialog(String text, String title) {
		this.setDialog(text, title);
	}
	
	/**
	 * Create the dialog with a custom text, title and settings
	 * The settings (like a custom logo or buttons) are set depending on the submitted variant string.
	 * Supported variant strings are:<br>
	 * "success", "info", "warn" and "error" where "error" is the default variant.
	 * @param text
	 * @param title
	 * @param variant
	 */
	public InfoDialog(String text, String title, String variant) {
		this.setDialog(text, title, variant);
	}
	
	
	private void setDialog(String info) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri, monospace; text-align: left;'>" + info + "</div>");
		createContents();
	}
	
	private void setDialog(String info, String title) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri,monospace; text-align: left;'>" + info + "</div>");
		createContents(title);
	}
	
	private void setDialog(String info, String title, String variant) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri,monospace; text-align: left;'>" + info + "</div>");
		if (variant.equals("success")) {
			this.getLblIcon().setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/Success.png")));
		} else if (variant.equals("info")) {
			this.getLblIcon().setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/InfoDialogIcon.png")));
		} else if (variant.equals("warn")) {
			this.getLblIcon().setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/warning.png")));
		} else if (variant.equals("error")) {
			this.getLblIcon().setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/error.png")));
		}
		createContents(title);
	}
	
	private void createContents() {
		this.createContents("Fehler");
	}
	private void createContents(String title) {
		setTitle(title);
		setModal(true);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		getContentPane().setLayout(new MigLayout("", "[5px:n][center][5px:n][250px:n:500px,center][10px:n:10px]", "[100px:n:400px,center][40px:n:40px,center]"));
		getContentPane().add(getLblIcon(), "cell 1 0,alignx center,aligny center");
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
	
	private JTextPane getTxtpnInfoWindowText() {
		if (txtpnInfoDialogText_ == null) {
			txtpnInfoDialogText_ = new JTextPane();
			txtpnInfoDialogText_.setEditable(false);
			txtpnInfoDialogText_.setContentType("text/html");
			txtpnInfoDialogText_.setSize(new Dimension(250, 150));
			txtpnInfoDialogText_.setBackground(SystemColor.control);
			txtpnInfoDialogText_.setText("<div style='font-family: Calibri, monospace; text-align: left;'>Info Window Text</div>");
		}
		return txtpnInfoDialogText_;
	}
	
	
	private JButton getBtnOk() {
		if (btnOk_ == null) {
			btnOk_ = new JButton("OK");
			btnOk_.setPreferredSize(new Dimension(75, 23));
			btnOk_.addActionListener(new InfoDialogBtnOk(this));
		}
		return btnOk_;
	}
	
	private JLabel getLblIcon() {
		if (lblIcon_ == null) {
			lblIcon_ = new JLabel("");
			lblIcon_.setIcon(new ImageIcon(InfoDialog.class.getResource("/de/sfgmbh/comlayer/core/images/error.png")));
			lblIcon_.setMaximumSize(new Dimension(100, 100));
			lblIcon_.setMinimumSize(new Dimension(100, 100));
		}
		return lblIcon_;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {
		String msg = AppModel.getInstance().getExceptionHandler().getExceptionMsg_();
		String title = AppModel.getInstance().getExceptionHandler().getExceptionTitle_();
		String variant = AppModel.getInstance().getExceptionHandler().getExceptionVariante_();
		
		if (variant != null && title != null) {
			InfoDialog span = new InfoDialog(msg, title, variant);
			span.setVisible(true);
		} else if (title != null) {
			InfoDialog span = new InfoDialog(msg, title);
			span.setVisible(true);
		} else {
			InfoDialog span = new InfoDialog(msg);
			span.setVisible(true);
		}
	}
}
