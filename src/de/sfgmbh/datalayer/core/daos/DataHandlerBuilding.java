package de.sfgmbh.datalayer.core.daos;

import java.util.List;

import de.sfgmbh.applayer.core.model.Building;
import de.sfgmbh.datalayer.core.definitions.IntfDataBuilding;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;

public class DataHandlerBuilding implements IntfDataBuilding, IntfDataObservable{

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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(Object observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Object observer) {
		// TODO Auto-generated method stub
		
	}

}
