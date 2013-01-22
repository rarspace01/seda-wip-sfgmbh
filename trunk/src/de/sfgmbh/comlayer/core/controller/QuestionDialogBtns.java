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
public class QuestionDialogBtns implements ActionListener, IntfComDialogObservable {
	
	private JDialog infoWinwod_;
	private String variant_;
	private IntfComDialogObserver currentObserver_;
	
	/**
	 * Create the listener for a specific window and variant
	 * @param transmittedInfoWindow - affectedWindow
	 * @param variant
	 */
	public QuestionDialogBtns(JDialog transmittedInfoWindow, String variant) {
		this.infoWinwod_ = transmittedInfoWindow;
		this.variant_ = variant;
		this.register((IntfComDialogObserver) this.infoWinwod_);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (this.variant_.equals("no")){
			infoWinwod_.dispose();
			this.update("no");
		}
		if (this.variant_.equals("yes")){
			infoWinwod_.dispose();
			this.update("yes");
		}
	}

	@Override
	public void register(IntfComDialogObserver observer) {
			this.currentObserver_ = observer;
		
		
	}

	@Override
	public void unregister(IntfComDialogObserver observer) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(String answer) {
			((IntfComDialogObserver) this.currentObserver_).answered(answer);
	}


}