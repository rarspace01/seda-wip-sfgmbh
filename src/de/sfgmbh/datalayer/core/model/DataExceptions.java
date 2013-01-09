package de.sfgmbh.datalayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;

public class DataExceptions implements IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private String exceptionMsg_;
	private String exceptionTitle_;
	private String exceptionVariante_;
	private boolean new_;
	
	/**
	 * @return the exceptionMsg_
	 */
	public String getExceptionMsg_() {
		return exceptionMsg_;
	}

	/**
	 * @return the exceptionTitle_
	 */
	public String getExceptionTitle_() {
		return exceptionTitle_;
	}

	/**
	 * @return the exceptionVariante_
	 */
	public String getExceptionVariante_() {
		return exceptionVariante_;
	}

	/**
	 * Set a new exception message and update all observer
	 * @param msg
	 */
	public void setNewException(String msg) {
		if (this.new_ == true) {
			this.exceptionTitle_ = null;
			this.exceptionVariante_ = null;
		}
		this.exceptionMsg_ = msg;
		this.update();
		this.new_ = true;
	}
	
	/**
	 * Set a new exception message and title and update all observer
	 * @param msg
	 * @param title
	 */
	public void setNewException(String msg, String title) {
		if (this.new_ == true) {
			this.exceptionVariante_ = null;
		}
		this.exceptionTitle_ = title;
		this.new_ = false;
		this.setNewException(msg);
	}
	
	/**
	 * Set a new exception message, title and variant and update all observer
	 * @param msg
	 * @param title
	 * @param variant
	 */
	public void setNewException(String msg, String title, String variant) {
		this.exceptionMsg_ = null;
		this.exceptionTitle_ = null;
		this.new_ = false;
		this.exceptionVariante_ = variant;
		this.setNewException(msg, title);
	}
	
	/**
	 * 
	 */
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfDataObserver) {
				((IntfDataObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			this.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!", "Fehler!");
		}
		
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}
}
