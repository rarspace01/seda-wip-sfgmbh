package de.sfgmbh.comlayer.core.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for semester combo boxes
 * 
 * @author hannes
 * @author mario
 *
 */
public class CmbboxFilterSemester extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	private String variant_;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterSemester() {
		this.variant_ = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param variant
	 */
	public CmbboxFilterSemester(String variant) {
		this.variant_ = variant;
		this.build();
		
	}

	public void build() {
		
		// Build the last, the current and the next 3 semesters dynamically
		Date currentDate = new Date();
		String month = new SimpleDateFormat("M").format(currentDate);
		String year = new SimpleDateFormat("yy").format(currentDate);
		boolean summer;
		if (Integer.parseInt(month) > 3 && Integer.parseInt(month) < 10) {
			summer = true;
		} else {
			summer = false;
		}
		
		// Do the actual string generation
		ArrayList<String> semester = new ArrayList<String>();
		for (int run = 0; run < 5; run++) {
			if (summer) {
				if (run == 0) {
					semester.add("WS " + (Integer.parseInt(year) - 1) + "/" + Integer.parseInt(year));
				}
				if (run == 1) {
					semester.add("SS " + Integer.parseInt(year));
				}
				if (run == 2) {
					semester.add("WS " + Integer.parseInt(year) + "/" + (Integer.parseInt(year) + 1));
				}
				if (run == 3) {
					semester.add("SS " + (Integer.parseInt(year) + 1));
				}
				if (run == 4) {
					semester.add("WS " + (Integer.parseInt(year) + 1) + "/" + (Integer.parseInt(year) + 2));
				}
			} else {
				if (run == 0) {
					if (Integer.parseInt(month) == 10 || Integer.parseInt(month) == 11 || Integer.parseInt(month) == 12) {
						semester.add("SS " + (Integer.parseInt(year)));
					} else {
						semester.add("SS " + (Integer.parseInt(year) - 1));
					}
				}
				if (run == 1) {
					if (Integer.parseInt(month) == 10 || Integer.parseInt(month) == 11 || Integer.parseInt(month) == 12) {
						semester.add("WS " + (Integer.parseInt(year)) + "/" + (Integer.parseInt(year) + 1));
					} else {
						semester.add("WS " + (Integer.parseInt(year) - 1) + "/" + (Integer.parseInt(year)));
					}
				}
				if (run == 2) {
					if (Integer.parseInt(month) == 10 || Integer.parseInt(month) == 11 || Integer.parseInt(month) == 12) {
						semester.add("SS " + (Integer.parseInt(year) + 1));
					} else {
						semester.add("SS " + (Integer.parseInt(year)));
					}
				}
				if (run == 3) {
					if (Integer.parseInt(month) == 10 || Integer.parseInt(month) == 11 || Integer.parseInt(month) == 12) {
						semester.add("WS " + (Integer.parseInt(year) + 1) + "/" + (Integer.parseInt(year) + 2));
					} else {
						semester.add("WS " + (Integer.parseInt(year) ) + "/" + (Integer.parseInt(year) + 1));
					}
				}
				if (run == 4) {
					if (Integer.parseInt(month) == 10 || Integer.parseInt(month) == 11 || Integer.parseInt(month) == 12) {
						semester.add("SS " + (Integer.parseInt(year) + 2));
					} else {
						semester.add("SS " + (Integer.parseInt(year) + 1));
					}
				}
			}
		}
		
		
		// Generate the actual model entries
		ArrayList<String> elemtens = new ArrayList<String>();
		if (this.variant_.equals("select")) {
			for (String sem : semester) {
				elemtens.add(sem);
			}
		} else {
			elemtens.add("<alle>");
			for (String sem : semester) {
				elemtens.add(sem);
			}
		}
		
		for (String element : elemtens) {
			this.addElement(element);
		}
		
		// Preselect currently hardcoded
		if (this.variant_.equals("select")) {
			this.setSelectedItem("SS 13");
		}
	}
}
