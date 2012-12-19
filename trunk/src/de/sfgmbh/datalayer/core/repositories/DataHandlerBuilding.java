package de.sfgmbh.datalayer.core.repositories;

import java.util.List;

import de.sfgmbh.applayer.core.model.Building;
import de.sfgmbh.datalayer.core.definitions.IntfDataBuilding;

public class DataHandlerBuilding implements IntfDataBuilding{

	@Override
	public List<Building> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Building get(int iBuildingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Building> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Building> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Building toBeDeletedBuilding) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Building toBeSavedBuilding) {
		// TODO Auto-generated method stub
		return 0;
	}

}
