package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Class to handle the behavior when there is an unexpected problem or something
 * the user should get informed about (in a user-friendly way).
 * 
 * @author hannes
 * 
 */
public class AppException implements IntfAppObservable, IntfDataObserver {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private String exceptionMsg_;
	private String exceptionTitle_;
	private String exceptionVariante_;
	private boolean new_;

	/**
	 * Create this class and register it as observer of data layer exceptions
	 */
	public AppException() {
		DataModel.getInstance().getExceptionsHandler().register(this);
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
	 * 
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
	 * 
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
	 * Set a new exception message, title and variant and update all observer <br>
	 * <br>
	 * 
	 * Valid status strings for the variant are:<br>
	 * "success" - The look and feel gets customized to indicate success<br>
	 * "info" - The look and feel gets customized for displaying information<br>
	 * "warn" - The look and feel gets customized for displaying warnings<br>
	 * "error" - The look and feel gets customized to show a problem<br>
	 * <br>
	 * 
	 * "error" is the default look and feel if no or an invalid variant was
	 * submitted.
	 * 
	 * @param msg
	 * @param title
	 * @param variant
	 */
	public void setNewException(String msg, String title, String variant) {
		this.exceptionMsg_ = null;
		this.exceptionTitle_ = null;
		this.new_ = false;
		if (variant.equals("success")) {
			this.exceptionVariante_ = variant;
		} else if (variant.equals("info")) {
			this.exceptionVariante_ = variant;
		} else if (variant.equals("warn")) {
			this.exceptionVariante_ = variant;
		} else {
			this.exceptionVariante_ = "error";
		}
		this.setNewException(msg, title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	@Override
	public void update() {

		// Create a private observer list to avoid
		// ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfAppObserver> currentObservers = (ArrayList<IntfAppObserver>) observer_
				.clone();

		for (IntfAppObserver observer : currentObservers) {
			if (observer instanceof IntfAppObserver) {
				observer.change();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh
	 * .applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void register(IntfAppObserver observer) {
		if (observer instanceof IntfAppObserver) {
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
	 * de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh
	 * .applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	@Override
	public void change() {
		String msg = DataModel.getInstance().getExceptionsHandler()
				.getExceptionMsg_();
		String title = DataModel.getInstance().getExceptionsHandler()
				.getExceptionTitle_();
		// String variant =
		// DataModel.getInstance().dataExcaptions.getExceptionVariante_();

		if (title != null) {
			this.setNewException(msg, title);
		} else {
			this.setNewException(msg);
		}

	}
}
