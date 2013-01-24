package de.sfgmbh.applayer.core.definitions;

import java.util.List;

import javax.swing.JTable;

import de.sfgmbh.applayer.core.model.RoomAllocation;

/**
 * Interface to handles the methods we need for the tables in the gui
 * @author denis
 *
 */
public interface IntfCtrlGenericTables {

	/**
	 * retrieves the Number of html breaklines in the string
	 * @param inputString
	 * @return count - Number of < br /> in String
	 */
	public abstract int countBreaklines(String inputString);
	
	/**
	 * resizes the given table dynamicly based on the row max size
	 * @param table
	 */
	public void resizeRows(JTable table);
	
	/**
	 * manages the reload process of a table, based on a given {@link RoomAllocation} {@link List}
	 * @param table
	 * @param roomAllocationList
	 * @param showRoomName - enables the Room output
	 */
	public abstract void reloadTable(JTable stundenplanTable,
			List<IntfRoomAllocation> roomAllocationList, boolean showRoomName, boolean markDuplicates);

}