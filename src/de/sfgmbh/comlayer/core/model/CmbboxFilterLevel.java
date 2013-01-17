package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;

/**
 * Model for room level combo boxes
 * 
 * @author hannes
 * @author mario
 *
 */
public class CmbboxFilterLevel extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterLevel() {
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.change();
	}

	@Override
	public void change() {
		
		// Build and clean up the model on change - do not use
		// removeAllElements() as it can cause null pointer exceptions when an
		// observer model has null elements at any time
		
		for(int i=0;i<this.getSize();i++){
			System.out.println(""+i+" - " + this.getElementAt(i));
		}
		
		int initalSize = this.getSize();
		this.addElement("dummy");
		for (int i = 0; i<this.getSize()-1; i++) {
			this.removeElementAt(i);
		}
		
		this.addElement("<alle>");
		for (Room room : AppModel.getInstance().getRepositoryRoom().getAll()){
			if (this.getIndexOf(room.getLevel_()) < 0) {
				this.addElement(room.getLevel_());
			}
		}
		
		if(this.getIndexOf("dummy")>-1){
		this.removeElementAt(this.getIndexOf("dummy"));
		}
		
	}
}
