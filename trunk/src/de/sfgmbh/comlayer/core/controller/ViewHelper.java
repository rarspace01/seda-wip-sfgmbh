package de.sfgmbh.comlayer.core.controller;

import de.sfgmbh.applayer.core.model.AppModel;

public class ViewHelper {

	/**
	 * Returns a formated day string for a given integer (valid integer: 1 to 7)
	 * @param d
	 * @return a formated day string
	 */
	public String getDay(int d) {
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
			AppModel.getInstance().getExceptionHandler().setNewException("Ein Tag mit einem nicht unterst�tzen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated time string for a given integer (valid integer: 1 to 7)
	 * @param t
	 * @return a formated time string
	 */
	public static String getTime(int t) {
		if (t == 1) {
			return "8:00 - 10:00";
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
			AppModel.getInstance().getExceptionHandler().setNewException("Eine Zeit mit einem nicht unterst�tzen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated boolean string for a given boolean value
	 * @param b
	 * @return a formated boolean string
	 */
	public String getBoolean(boolean b) {
		if(b){
			return "ja";
		} else {
			return "nein";
		}
	}
	
	/**
	 * Returns the German value for a given user class
	 * @param uc
	 * @return the German value for a given user class
	 */
	public String getUserClass(String uc) {
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
	 * @param s
	 * @return the formated string for a status
	 */
	public String getAllocationStatus(String s) {
		if (s.equals("waiting")) {
			return "wartend";
		} else if (s.equals("accepted")) {
			return "freigegeben";
		} else if (s.equals("denied")) {
			return "abgelehnt";
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine unbekannter Status wurde verwendet.", "Achtung!");
			return null;
		}
	}
}
