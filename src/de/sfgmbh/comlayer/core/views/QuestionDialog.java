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
	private JTextPane txtpnInfoDialogText_;
	private JButton btnYes_;
	private JLabel lblNewLabel_;
	private JButton btnNo_;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(QuestionDialog.class.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
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
	
	
	private JButton getBtnYes() {
		if (btnYes_ == null) {
			btnYes_ = new JButton("Ja");
			btnYes_.addActionListener(new QuestionDialogBtns(this, "yes"));
			btnYes_.setPreferredSize(new Dimension(75, 23));
		}
		return btnYes_;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel_ == null) {
			lblNewLabel_ = new JLabel("");
			lblNewLabel_.setIcon(new ImageIcon(QuestionDialog.class.getResource("/de/sfgmbh/comlayer/core/images/Question.png")));
			lblNewLabel_.setMaximumSize(new Dimension(100, 100));
		}
		return lblNewLabel_;
	}
	private JButton getBtnNo() {
		if (btnNo_ == null) {
			btnNo_ = new JButton("Nein");
			btnNo_.addActionListener(new QuestionDialogBtns(this, "no"));
			btnNo_.setPreferredSize(new Dimension(75, 23));
		}
		return btnNo_;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#register(de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver)
	 */
	@Override
	public void register(IntfComDialogObserver observer) {
		this.currentObserver_ = observer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#unregister(de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver)
	 */
	@Override
	public void unregister(IntfComDialogObserver observer) {
		this.currentObserver_ = null;
		
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#update(java.lang.String)
	 */
	@Override
	public void update(String answer) {
			(this.currentObserver_).answered(answer);
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver#answered(java.lang.String)
	 */
	@Override
	public void answered(String answer) {
		this.update(answer);
	}


}
