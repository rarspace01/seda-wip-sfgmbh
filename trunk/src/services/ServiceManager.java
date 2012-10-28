package services;

import gui.*;
import modules.coregui.model.*;

public class ServiceManager {
	
	/**
	* Define objects were instance management is necessary 
	*/
	// GUI
	protected CoreGUI coreGui;
	protected Stundenplan stundenplanGui;
	protected Verwaltung verwaltungGui;
	protected Dozenten dozentenGui;
	protected StudProfil studProfilGui;
	
	// Session Manager
	protected SessionManager sessionManager;
	
	// Module specific models
	protected VeranstaltungsTable vTableModel;
	
	// DB Repositories 
	
	
	/**
	* Get current instances, if there is none build one
	*/
	//GUI
	public Stundenplan getStundenplan() {
		if (this.stundenplanGui == null) {
			this.stundenplanGui = new Stundenplan();
		}
		return this.stundenplanGui;
	}
	public Verwaltung getVerwaltung() {
		if (this.verwaltungGui == null) {
			this.verwaltungGui = new Verwaltung();
		}
		return this.verwaltungGui;
	}
	public Dozenten getDozenten() {
		if (this.dozentenGui == null) {
			this.dozentenGui = new Dozenten();
		}
		return this.dozentenGui;
	}
	public StudProfil getStudProfil() {
		if (this.studProfilGui == null) {
			this.studProfilGui = new StudProfil();
		}
		return this.studProfilGui;
	}
	public CoreGUI getCoreGUI() {
		if (this.coreGui == null) {
			this.coreGui = new CoreGUI();
		}
		return this.coreGui;
	}
	
	// Session Manager
	public SessionManager getSM(){
		if (this.sessionManager == null) {
			this.sessionManager = new SessionManager();
		}
		return this.sessionManager;
	}
	
	// Module specific models
	public VeranstaltungsTable getVTableModel(){
		if (this.vTableModel == null) {
			this.vTableModel = new VeranstaltungsTable();
		}
		return this.vTableModel;
	}
	
	// DB Repositories
}
