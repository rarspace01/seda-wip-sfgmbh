package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Action listener for the counter proposal dialog
 * 
 * @author hannes
 *
 */
public class CounterproposalFrameBtns implements ActionListener {
	
	private String ctrlAction;
	private CounterproposalDialog motherDialog;
	
	
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog) {
		this.ctrlAction = "default";
		this.motherDialog = motherDialog;
	}
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog, String action) {
		this.ctrlAction = action;
		this.motherDialog = motherDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Cancle button is pressed
		if (this.ctrlAction.equals("cancel")){
			this.motherDialog.dispose();
		}
		
		// Sent button is pressed
		if (this.ctrlAction.equals("send")){
			//
		}
		
	}
}
