package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class AppExceptions implements IntfAppObservable, IntfDataObserver {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private String exceptionMsg_;
	private String exceptionTitle_;
	private String exceptionVariante_;
	private boolean new_;
	
	/**
	 * Register this class as observer of data layer exceptions
	 */
	public AppExceptions() {
		DataModel.getInstance().dataExceptions.register(this);
	}
	
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
			if (o instanceof IntfAppObserver) {
				((IntfAppObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			this.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!", "Fehler!");
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

	@Override
	public void change() {
		String msg = DataModel.getInstance().dataExceptions.getExceptionMsg_();
		String title = DataModel.getInstance().dataExceptions.getExceptionTitle_();
		// String variant = DataModel.getInstance().dataExcaptions.getExceptionVariante_(); 
		
		if (title != null) {
			this.setNewException(msg, title);
		} else {
			this.setNewException(msg);
		}
		
	}
}
