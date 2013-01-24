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
	public static String getDay(int d) {
		if (d == 1) {
			return "Mo.";
		} else if (d == 2) {
			return "Di.";
		} else if (d == 3) {
			return "Mi.";
		} else if (d == 4) {
			return "Do.";
		} else if (d == 5) {
			return "Fr.";
		} else if (d == 6) {
			return "Sa.";
		} else if (d == 7) {
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
	public static String getTime(int t) {
		if (t == 1) {
			return "08:00 - 10:00";
		} else if (t == 2) {
			return "10:00 - 12:00";
		} else if (t == 3) {
			return "12:00 - 14:00";
		} else if (t == 4) {
			return "14:00 - 16:00";
		} else if (t == 5) {
			return "16:00 - 18:00";
		} else if (t == 6) {
			return "18:00 - 20:00";
		} else if (t == 7) {
			return "20:00 - 22:00";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine Zeit mit einem nicht unterstützen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated boolean string for a given boolean value
	 * @param boolean
	 * @return a formated boolean string
	 */
	public static String getBoolean(boolean b) {
		if(b){
			return "ja";
		} else {
			return "nein";
		}
	}
	
	/**
	 * Returns the German value for a given user class
	 * @param uuserClass
	 * @return the German value for a given user class
	 */
	public static String getUserClass(String uc) {
		if (uc.equals("orga")) {
			return "Verwaltung";
		} else if (uc.equals("lecturer")) {
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
	public static String getAllocationStatus(String s) {
		if (s.equals("waiting")) {
			return "wartend";
		} else if (s.equals("accepted")) {
			return "freigegeben";
		} else if (s.equals("denied")) {
			return "abgelehnt";
		} else if (s.equals("counter")) {
			return "Gegenvorschlag";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine unbekannter Status wurde verwendet.", "Achtung!");
			return null;
		}
	}
	
	public static String getGlobalDateFormat(Date d) {
		String newDate = new SimpleDateFormat("dd.MM.yy 'um' HH:mm:ss").format(d);
		return newDate;
	}
}
