package de.sfgmbh.comlayer.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.sfgmbh.applayer.core.model.AppModel;

/**
 * View helper to always display certain formats and similar stuff in the GUI the same way
 * 
 * 
 * @author hannes
 * @author denis
 *
 */
public class ViewHelper {

	/**
	 * Returns a formated day string for a given integer (valid integer: 1 to 7)
	 * @param day
	 * @return a formated day string
	 */
	public static String getDay(int day) {
		if (day == 1) {
			return "Mo.";
		} else if (day == 2) {
			return "Di.";
		} else if (day == 3) {
			return "Mi.";
		} else if (day == 4) {
			return "Do.";
		} else if (day == 5) {
			return "Fr.";
		} else if (day == 6) {
			return "Sa.";
		} else if (day == 7) {
			return "So.";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Ein Tag mit einem nicht unterstützen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated time string for a given integer (valid integer: 1 to 7)
	 * @param time
	 * @return a formated time string
	 */
	public static String getTime(int time) {
		if (time == 1) {
			return "08:00 - 10:00";
		} else if (time == 2) {
			return "10:00 - 12:00";
		} else if (time == 3) {
			return "12:00 - 14:00";
		} else if (time == 4) {
			return "14:00 - 16:00";
		} else if (time == 5) {
			return "16:00 - 18:00";
		} else if (time == 6) {
			return "18:00 - 20:00";
		} else if (time == 7) {
			return "20:00 - 22:00";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine Zeit mit einem nicht unterstützen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated boolean string for a given boolean value
	 * @param booleanValue
	 * @return a formated boolean string
	 */
	public static String getTextForBoolean(boolean booleanValue) {
		if(booleanValue){
			return "ja";
		} else {
			return "nein";
		}
	}
	
	/**
	 * Returns the German value for a given user class
	 * @param userClass
	 * @return the German value for a given user class
	 */
	public static String getUserClass(String userClass) {
		if (userClass.equals("orga")) {
			return "Verwaltung";
		} else if (userClass.equals("lecturer")) {
			return "Dozenten";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine unbekannte Nutzerklasse wurde verwendet.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns the formated string for a status
	 * @param status
	 * @return the formated string for a status
	 */
	public static String getAllocationStatus(String status) {
		if (status.equals("waiting")) {
			return "wartend";
		} else if (status.equals("accepted")) {
			return "freigegeben";
		} else if (status.equals("denied")) {
			return "abgelehnt";
		} else if (status.equals("counter")) {
			return "Gegenvorschlag";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine unbekannter Status wurde verwendet.", "Achtung!");
			return null;
		}
	}
	
	public static String getGlobalDateFormat(Date date) {
		String newDate = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(date);
		return newDate;
	}
}
