package de.sfgmbh.comlayer.core.controller;

import de.sfgmbh.applayer.core.model.AppModel;

public class ViewHelper {

	/**
	 * Returns a formated day string for a given integer (valid integer: 1 to 7)
	 * @param day d
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
			AppModel.getInstance().appExcaptions.setNewException("Ein Tag mit einem nicht unterstützen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
	
	/**
	 * Returns a formated time string for a given integer (valid integer: 1 to 7)
	 * @param time t
	 * @return a formated time string
	 */
	public String getTime(int t) {
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
			AppModel.getInstance().appExcaptions.setNewException("Eine Zeit mit einem nicht unterstützen Format konnte nicht angezeigt werden.", "Achtung!");
			return null;
		}
	}
}
