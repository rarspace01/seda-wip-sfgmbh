package de.sfgmbh.datalayer.core.definitions;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;

public interface IntfDataFilter {

	public List<?> getByFilter(HashMap<String, String> filter); 
	
}
