package de.sfgmbh.datalayer.core.definitions;

import java.util.HashMap;
import java.util.List;

public interface IntfDataFilter {

	public List<?> getByFilter(HashMap<String, String> filter); 
	
}
