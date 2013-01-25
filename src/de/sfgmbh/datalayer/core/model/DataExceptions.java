package de.sfgmbh.datalayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;

/**
 * Exception handler for Data Layer. <br>
 * This class is capable to store user friendly messages as well as variants
 * (like "error", "info" and so on). Those may be used by interested objects
 * from neighboring layers which implement the IntfDataObserver interface in any
 * way they see it fit.
 * 
 * @author hannes
 * 
 */
public class DataExceptions implements IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private String exceptionMsg_;
	private String exceptionTitle_;
	private String exceptionVariante_;
	private boolean isNew_;

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
	 * 
	 * @param msg
	 */
	public void setNewException(String msg) {
		if (this.isNew_ == true) {
			this.exceptionTitle_ = null;
			this.exceptionVariante_ = null;
		}
		this.exceptionMsg_ = msg;
		this.update();
		this.isNew_ = true;
	}

	/**
	 * Set a new exception message and title and update all observer
	 * 
	 * @param msg
	 * @param title
	 */
	public void setNewException(String msg, String title) {
		if (this.isNew_ == true) {
			this.exceptionVariante_ = null;
		}
		this.exceptionTitle_ = title;
		this.isNew_ = false;
		this.setNewException(msg);
	}

	/**
	 * Set a new exception message, title and variant and update all observer
	 * 
	 * @param msg
	 * @param title
	 * @param variant
	 */
	public void setNewException(String msg, String title, String variant) {
		this.exceptionMsg_ = null;
		this.exceptionTitle_ = null;
		this.isNew_ = false;
		this.exceptionVariante_ = variant;
		this.setNewException(msg, title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObservable#update()
	 */
	@Override
	public void update() {
		// Create a private observer list to avoid
		// ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfDataObserver> currentObservers = (ArrayList<IntfDataObserver>) observer_
				.clone();

		for (IntfDataObserver observer : currentObservers) {
			if (observer instanceof IntfDataObserver) {
				observer.change();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.datalayer.core.definitions.IntfDataObservable#register(de.sfgmbh
	 * .datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void register(IntfDataObserver observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			this.setNewException(
					"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!",
					"Fehler!");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.datalayer.core.definitions.IntfDataObservable#unregister(de
	 * .sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}
}
