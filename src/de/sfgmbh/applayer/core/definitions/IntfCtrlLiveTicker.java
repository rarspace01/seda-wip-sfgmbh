package de.sfgmbh.applayer.core.definitions;

import java.util.List;

/**
 * Interface for the live ticker controller
 * @author hannes
 *
 */
public interface IntfCtrlLiveTicker {

	/**
	 * Get all allocations which should be displayed in the live ticker.<br>
	 * This allocations only affect the current day, public allocations and there are at most 15 allocations retrieved (started by the most recent on)
	 * @return a list of room allocations
	 */
	public abstract List<IntfRoomAllocation> getTickerAllocations();

}