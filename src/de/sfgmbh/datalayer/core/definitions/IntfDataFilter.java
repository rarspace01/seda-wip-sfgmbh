package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

public interface IntfDataFilter {

	public List<?> getByFilter(String filterName, String filterValue); 
	
}
