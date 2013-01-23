package de.sfgmbh.init;

public class ResetDb {
	
	public ResetDb() {
	}
	
	public void factoryReset(){
		dropTables();
		createTables();
		insertData();
	}
	
	private void insertData() {
		
	}

	private void createTables() {
		
	}

	private void dropTables(){
	}
	
}
