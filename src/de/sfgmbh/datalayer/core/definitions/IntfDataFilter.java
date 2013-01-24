package de.sfgmbh.datalayer.core.definitions;

import java.util.HashMap;
import java.util.List;
/**
 * 
 * @author denis
 * @author hannes
 */
public interface IntfDataFilter {

	public List<?> getByFilter(HashMap<String, String> filter); 
	
}
