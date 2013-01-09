package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.datalayer.core.definitions.IntfDataChair;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerChair implements IntfDataChair, IntfDataFilter, IntfDataObservable {

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
				//returnChair.set

				listChair.add(returnChair);
				returnChair = null;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
