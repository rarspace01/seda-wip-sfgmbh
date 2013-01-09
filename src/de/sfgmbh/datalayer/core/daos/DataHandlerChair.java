package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.datalayer.core.definitions.IntfDataChair;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerChair implements IntfDataChair, IntfDataFilter, IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();

	@Override
	public List<Chair> getAll() {
		List<Chair> listChair = new ArrayList<Chair>();
		Chair returnChair = null;

		String SqlStatement = "SELECT * FROM public.chair";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {

				returnChair = new Chair();

				returnChair.setChairId_(resultSet.getInt("chairid"));
				returnChair.setChairName_(resultSet.getString("chairname"));
				returnChair.setChairOwner_(
						DataModel.getInstance().dataHandlerUser.get(resultSet.getInt("chairowner")));
				returnChair.setBuildingId_(resultSet.getInt("buildingid"));
				returnChair.setChairLevel_(resultSet.getString("chairlevel"));
				returnChair.setFaculty_(resultSet.getString("faculty"));

				listChair.add(returnChair);
				returnChair = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerChair-02) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerChair-03) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listChair;
	}

	@Override
	public Chair get(int ChairId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chair> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chair> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Chair Chair) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Chair Chair) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Chair> getByFilter(String filterName, String filterValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfDataObserver) {
				((IntfDataObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			DataModel.getInstance().dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerChair-01", "Fehler!");
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

}
