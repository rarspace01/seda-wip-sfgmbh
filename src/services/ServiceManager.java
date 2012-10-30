package services;

import modules.coregui.model.*;
import modules.coregui.views.CoreGUI;
import modules.dozenten.views.*;
import modules.studentenprofil.views.StudProfil;
import modules.stundenplan.views.Stundenplan;
import modules.verwaltung.views.Verwaltung;

public class ServiceManager {
	
	/**
	* Module: CoreGUI
	*/
	protected CoreGUI coreGui;
	protected VeranstaltungsTable vTableModel;
	
	public CoreGUI getCoreGUI() {
		if (this.coreGui == null) {
			this.coreGui = new CoreGUI();
		}
		return this.coreGui;
	}
	public VeranstaltungsTable getVTableModel(){
		if (this.vTableModel == null) {
			this.vTableModel = new VeranstaltungsTable();
		}
		return this.vTableModel;
	}
	
	/**
	* Module: Stundenplan
	*/
	protected Stundenplan stundenplanGui;
	
	public Stundenplan getStundenplan() {
		if (this.stundenplanGui == null) {
			this.stundenplanGui = new Stundenplan();
		}
		return this.stundenplanGui;
	}
	
	/**
	* Module: Verwaltung
	*/
	protected Verwaltung verwaltungGui;
	
	public Verwaltung getVerwaltung() {
		if (this.verwaltungGui == null) {
			this.verwaltungGui = new Verwaltung();
		}
		return this.verwaltungGui;
	}
	
	/**
	* Module: Dozenten
	*/
	protected DozentenTab dozentenGui;
	protected DozentenLVMaskeWindow dozLVMaske;
	
	public DozentenTab getDozenten() {
		if (this.dozentenGui == null) {
			this.dozentenGui = new DozentenTab();
		}
		return this.dozentenGui;
	}
	public DozentenLVMaskeWindow getDozLVMaske() {
		if (this.dozLVMaske == null) {
			this.dozLVMaske = new DozentenLVMaskeWindow();
		}
		return this.dozLVMaske;
	}
	
	/**
	* Module: Studentenporfil
	*/
	protected StudProfil studProfilGui;
	
	public StudProfil getStudProfil() {
		if (this.studProfilGui == null) {
			this.studProfilGui = new StudProfil();
		}
		return this.studProfilGui;
	}
	
	/**
	* Session Manager
	*/
	protected SessionManager sessionManager;
	
	public SessionManager getSM(){
		if (this.sessionManager == null) {
			this.sessionManager = new SessionManager();
		}
		return this.sessionManager;
	}
}
