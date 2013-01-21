package de.sfgmbh.comlayer.core.views;

import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.QuestionDialogBtns;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;

/**
 * Modal dialog with more options
 * 
 * @author hannes
 *
 */
public class QuestionDialog extends JDialog implements IntfComDialogObserver, IntfComDialogObservable {

	private static final long serialVersionUID = 1L;
	private JTextPane txtpnInfoDialogText;
	private JButton btnYes;
	private JLabel lblNewLabel;
	private JButton btnNo;
	private IntfComDialogObserver currentObserver_;

	/**
	 * Create the dialog with default message, title and settings
	 */
	public QuestionDialog() {
		createContents();
	}
	
	/**
	 * Create the dialog with a custom text and a default title and settings
	 * @param text
	 */
	public QuestionDialog(String text) {
		this.setDialog(text);
	}
	
	/**
	 * Create the dialog with a custom text and title and default settings
	 * @param text
	 * @param title
	 */
	public QuestionDialog(String text, String title) {
		this.setDialog(text, title);
	}
	
	/**
	 * Create the dialog with a custom text, title and settings
	 * The settings (like a custom logo or buttons) are set depending on the submitted variant string.
	 * @param text
	 * @param title
	 * @param variant
	 */
	public QuestionDialog(String text, String title, String variant) {
		this.setDialog(text, title);
		// jet to be implemented
	}
	
	
	private void setDialog(String info) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri, monospace; text-align: left;'>" + info + "</div>");
		createContents();
	}
	
	private void setDialog(String info, String title) {
		this.getTxtpnInfoWindowText().setText("<div style='font-family: Calibri,monospace; text-align: left;'>" + info + "</div>");
		createContents(title);
	}
	
	private void createContents() {
		this.createContents("Wollen Sie das wirklich?");
	}
	private void createContents(String title) {
		setTitle(title);
		setModal(true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new MigLayout("", "[5px:n][center][5px:n][250px:n:500px,center][10px:n:10px]", "[100px:n:400px,center][40px:n:40px,center]"));
		getContentPane().add(getLblNewLabel(), "cell 1 0,alignx center,aligny center");
		getContentPane().add(getTxtpnInfoWindowText(), "cell 3 0,alignx left,aligny center");
		getContentPane().add(getBtnNo(), "flowx,cell 3 1,alignx right");
		getContentPane().add(getBtnYes(), "cell 3 1,alignx right,aligny center");
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
	
	
	private JButton getBtnYes() {
		if (btnYes == null) {
			btnYes = new JButton("Ja");
			btnYes.addActionListener(new QuestionDialogBtns(this, "yes"));
			btnYes.setPreferredSize(new Dimension(75, 23));
		}
		return btnYes;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(QuestionDialog.class.getResource("/de/sfgmbh/comlayer/core/images/Question.png")));
			lblNewLabel.setMaximumSize(new Dimension(100, 100));
		}
		return lblNewLabel;
	}
	public JButton getBtnNo() {
		if (btnNo == null) {
			btnNo = new JButton("Nein");
			btnNo.addActionListener(new QuestionDialogBtns(this, "no"));
			btnNo.setPreferredSize(new Dimension(75, 23));
		}
		return btnNo;
	}

	@Override
	public void register(IntfComDialogObserver observer) {
		this.currentObserver_ = observer;
	}

	@Override
	public void update(String answer) {
			(this.currentObserver_).answered(answer);
	}

	@Override
	public void answered(String answer) {
		this.update(answer);
	}
}
