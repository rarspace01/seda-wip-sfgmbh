package de.sfgmbh.comlayer.organisation.controller;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 
 * @author anna
 * @author hannes
 *
 */
public class UserCreateDialogWin implements WindowListener {
	
	private Window window_;
	
	public UserCreateDialogWin (Window window){
		this.window_ = window;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		this.window_.dispose();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
