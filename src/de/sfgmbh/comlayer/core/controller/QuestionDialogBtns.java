package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;

/**
 * Action listener for the question dialog buttons
 * 
 * @author hannes
 * 
 */
public class QuestionDialogBtns implements ActionListener,
		IntfComDialogObservable {

	private JDialog infoWinwod_;
	private String variant_;
	private IntfComDialogObserver currentObserver_;

	/**
	 * Create the listener for a specific window and variant
	 * 
	 * @param transmittedInfoWindow
	 *            - affectedWindow
	 * @param variant
	 */
	public QuestionDialogBtns(JDialog transmittedInfoWindow, String variant) {
		this.infoWinwod_ = transmittedInfoWindow;
		this.variant_ = variant;
		this.register((IntfComDialogObserver) this.infoWinwod_);
	}

	/**
	 * Update the observer and close on action
	 */
	public void actionPerformed(ActionEvent e) {
		if (this.variant_.equals("no")) {
			infoWinwod_.dispose();
			this.update("no");
		}
		if (this.variant_.equals("yes")) {
			infoWinwod_.dispose();
			this.update("yes");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#register(
	 * de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver)
	 */
	@Override
	public void register(IntfComDialogObserver observer) {
		this.currentObserver_ = observer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#unregister
	 * (de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver)
	 */
	@Override
	public void unregister(IntfComDialogObserver observer) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.comlayer.core.definitions.IntfComDialogObservable#update(java
	 * .lang.String)
	 */
	@Override
	public void update(String answer) {
		((IntfComDialogObserver) this.currentObserver_).answered(answer);
	}

}