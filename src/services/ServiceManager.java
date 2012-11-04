package services;

import modules.coregui.model.*;
import modules.coregui.views.CoreGUI;
import modules.dozenten.model.*;
import modules.dozenten.views.*;
import modules.studentenprofil.views.StudProfil;
import modules.stundenplan.views.Stundenplan;
import modules.verwaltung.views.*;

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
	protected VerwaltungRaumTab verwaltungGui;
	protected VerwaltungNutzerTab verwaltungNutzerGui;
	protected VerwaltungLehrstuhlTab verwaltungLehrstuhlGui;
	protected VerwaltungAnfrageTab verwaltungAnfrageGui;
	protected VerwaltungRaumMaskeWindow verwaltungRaumWindow;
	protected VerwaltungNutzerMaskeWindow verwaltungNutzerWindow;
	protected VerwaltungLehrstuhlMaskeWindow verwaltungLehrstuhlWindow;
	protected VerwaltungGegenvorschlagMaskeWindow verwaltungGegenvorschlagWindow;
	
	public VerwaltungRaumTab getVerwRaum() {
		if (this.verwaltungGui == null) {
			this.verwaltungGui = new VerwaltungRaumTab();
		}
		return this.verwaltungGui;
	}
	public VerwaltungNutzerTab getVerwNutzer() {
		if (this.verwaltungNutzerGui == null) {
			this.verwaltungNutzerGui = new VerwaltungNutzerTab();
		}
		return this.verwaltungNutzerGui;
	}
	public VerwaltungAnfrageTab getVerwAnfrage() {
		if (this.verwaltungAnfrageGui == null) {
			this.verwaltungAnfrageGui = new VerwaltungAnfrageTab();
		}
		return this.verwaltungAnfrageGui;
	}
	public VerwaltungLehrstuhlTab getVerwLehrstuhl() {
		if (this.verwaltungLehrstuhlGui == null) {
			this.verwaltungLehrstuhlGui = new VerwaltungLehrstuhlTab();
		}
		return this.verwaltungLehrstuhlGui;
	}
	public VerwaltungRaumMaskeWindow getVerwRaumMaske() {
		if (this.verwaltungRaumWindow == null) {
			this.verwaltungRaumWindow = new VerwaltungRaumMaskeWindow();
		}
		return this.verwaltungRaumWindow;
	}
	public VerwaltungNutzerMaskeWindow getVerwNutzerMaske() {
		if (this.verwaltungNutzerWindow == null) {
			this.verwaltungNutzerWindow = new VerwaltungNutzerMaskeWindow();
		}
		return this.verwaltungNutzerWindow;
	}
	public VerwaltungLehrstuhlMaskeWindow getVerwLehrstuhlMaske() {
		if (this.verwaltungLehrstuhlWindow == null) {
			this.verwaltungLehrstuhlWindow = new VerwaltungLehrstuhlMaskeWindow();
		}
		return this.verwaltungLehrstuhlWindow;
	}
	public VerwaltungGegenvorschlagMaskeWindow getVerwGegenvorschlagMaske() {
		if (this.verwaltungGegenvorschlagWindow == null) {
			this.verwaltungGegenvorschlagWindow = new VerwaltungGegenvorschlagMaskeWindow();
		}
		return this.verwaltungGegenvorschlagWindow;
	}
	
	/**
	* Module: Dozenten
	*/
	protected DozentenTab dozentenGui;
	protected DozentenLVMaskeWindow dozLVMaske;
	protected DozentenStundenplanTable dozTableModel;
	
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
	public DozentenStundenplanTable getDozStunenplanTableModel() {
		if (this.dozTableModel == null) {
			this.dozTableModel = new DozentenStundenplanTable();
		}
		return this.dozTableModel;
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
