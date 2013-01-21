package de.sfgmbh.comlayer.core.controller;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.model.BaseTableMain;
import de.sfgmbh.comlayer.core.model.RoomTableMain;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.core.views.LiveTickerPanel;
import de.sfgmbh.comlayer.lecturer.model.ChairTimetableTabTable;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableBottom;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableTop;
import de.sfgmbh.comlayer.lecturer.model.TimetableTabTable;
import de.sfgmbh.comlayer.lecturer.views.ProfessorshipTimetableTab;
import de.sfgmbh.comlayer.lecturer.views.StartTab;
import de.sfgmbh.comlayer.lecturer.views.TimetableTab;
import de.sfgmbh.comlayer.organisation.model.ChairTabTable;
import de.sfgmbh.comlayer.organisation.model.RequestTabTable;
import de.sfgmbh.comlayer.organisation.model.RoomTabTable;
import de.sfgmbh.comlayer.organisation.model.RoomtableTable;
import de.sfgmbh.comlayer.organisation.model.UserTabTable;
import de.sfgmbh.comlayer.organisation.views.ChairTab;
import de.sfgmbh.comlayer.organisation.views.RequestTab;
import de.sfgmbh.comlayer.organisation.views.RoomFrame;
import de.sfgmbh.comlayer.organisation.views.RoomTab;
import de.sfgmbh.comlayer.organisation.views.RoomtableTab;
import de.sfgmbh.comlayer.organisation.views.UserCreateDialog;
import de.sfgmbh.comlayer.organisation.views.UserTab;
import de.sfgmbh.comlayer.timetable.views.PublicTimetableTab;


public class ViewManager {
	
	private static ViewManager uniqueInstance_ = new ViewManager();
	
	public ViewManager() {
		uniqueInstance_ = this;
	}
	
	public static ViewManager getInstance() {
		return uniqueInstance_;
	}

	public void dispose() {
		uniqueInstance_ = null;
	}
	
	/**
	* Module: Core (global area - all other modules have dependencies to this one)
	*/
	private BaseTab baseTab;
	private InfoDialog infoDialog;
	private BaseTableMain baseTableMain;
	private RoomTableMain roomTableMain;
	private LiveTickerPanel liveTickerPanel;
	
	public BaseTab getCoreBaseTab() {
		if (this.baseTab == null) {
			this.baseTab = new BaseTab();
		}
		return this.baseTab;
	}
	public InfoDialog getCoreInfoDialog() {
		if (this.infoDialog == null) {
			this.infoDialog = new InfoDialog();
		}
		return this.infoDialog;
	}
	public LiveTickerPanel getCoreLiveTickerPanel() {
		// Always return a new instance as one Swing component my only be registered in one Frame.
		// Later here needs to be a logic to sync the sliding position of all created instances.
		this.liveTickerPanel = new LiveTickerPanel();
		return this.liveTickerPanel;
	}
	public BaseTableMain getCoreBaseTableModel(){
		if (this.baseTableMain == null) {
			this.baseTableMain = new BaseTableMain();
		}
		return this.baseTableMain;
	}
	
	public RoomTableMain getCoreRoomTableModel(){
		if (this.roomTableMain == null) {
			this.roomTableMain = new RoomTableMain();
		}
		return this.roomTableMain;
	}
	
	/**
	* Module: Timetable
	*/
	private PublicTimetableTab publicTimetableTab;
	
	public PublicTimetableTab getPublicTimetableTab() {
		if (this.publicTimetableTab == null) {
			this.publicTimetableTab = new PublicTimetableTab();
		}
		return this.publicTimetableTab;
	}
	
	/**
	* Module: Organization
	*/
	private RoomTab roomTab;
	private UserTab userTab;
	private ChairTab chairTab;
	private RequestTab requestTab;
	private RoomFrame roomFrame;
	private UserCreateDialog userFrame;
	private UserCreateDialog userEditFrame;
	private RequestTabTable requestTabTable;
	private UserTabTable userTabTable;
	private RoomTabTable roomTabTable;
	private ChairTabTable chairTabTable;
	private RoomtableTab roomtableTab;
	private RoomtableTable roomtableTable;
	/**
	 * 
	 * @return the roomtableTable
	 */
	public RoomtableTable getOrgaRoomtableTableModel() {
		if (this.roomtableTable == null) {
			this.roomtableTable = new RoomtableTable();
		}
		return this.roomtableTable;
	}
	/**
	 * 
	 * @return the roomtableTab
	 */
	public RoomtableTab getOrgaRoomtableTab() {
		if (this.roomtableTab == null) {
			this.roomtableTab = new RoomtableTab();
		}
		return this.roomtableTab;
	}
	/**
	 * 
	 * @return the chairTabTable
	 */
	public ChairTabTable getOrgaChairTableModel() {
		if (this.chairTabTable == null) {
			this.chairTabTable = new ChairTabTable();
		}
		return this.chairTabTable;
	}
	/**
	 * 
	 * @return the roomTabTable
	 */
	public RoomTabTable getOrgaRoomTableModel() {
		if (this.roomTabTable == null) {
			this.roomTabTable = new RoomTabTable();
		}
		return this.roomTabTable;
	}
	/**
	 * 
	 * @return the userTabTable
	 */
	public UserTabTable getOrgaUserTableModel() {
		if (this.userTabTable == null) {
			this.userTabTable = new UserTabTable();
		}
		return this.userTabTable;
	}
	/**
	 * 
	 * @return the requestTabTable
	 */
	public RequestTabTable getOrgaRequestTableModel() {
		if (this.requestTabTable == null) {
			this.requestTabTable = new RequestTabTable();
		}
		return this.requestTabTable;
	}
	/**
	 * 
	 * @return the roomTab
	 */
	public RoomTab getOrgaRoomTab() {
		if (this.roomTab == null) {
			this.roomTab = new RoomTab();
		}
		return this.roomTab;
	}
	/**
	 * 
	 * @return the userTab
	 */
	public UserTab getOrgaUserTab() {
		if (this.userTab == null) {
			this.userTab = new UserTab();
		}
		return this.userTab;
	}
	/**
	 * 
	 * @return the requestTab
	 */
	public RequestTab getOrgaRquestTab() {
		if (this.requestTab == null) {
			this.requestTab = new RequestTab();
		}
		return this.requestTab;
	}
	/**
	 * 
	 * @return the chairTab
	 */
	public ChairTab getOrgaChairTab() {
		if (this.chairTab == null) {
			this.chairTab = new ChairTab();
		}
		return this.chairTab;
	}
	/**
	 * 
	 * @return roomFrame
	 */
	public RoomFrame getOrgaRoomFrame() {
		if (this.roomFrame == null) {
			this.roomFrame = new RoomFrame();
		}
		return this.roomFrame;
	}
	/**
	 * 
	 * @return the userFrame
	 */
	public UserCreateDialog getOrgaUserCreateDialog() {
		if (this.userFrame == null) {
			this.userFrame = new UserCreateDialog();
		}
		return this.userFrame;
	}
	/**
	 * 
	 * @param user
	 * @return the userEditFrame for a certain user
	 */
	public UserCreateDialog getOrgaUserEditFrame(User user) {
		if (this.userEditFrame == null) {
			this.userEditFrame = new UserCreateDialog(user);
		}
		return this.userEditFrame;
	}
	/**
	 * 
	 * @return the userEditFrame
	 */
	public UserCreateDialog getOrgaUserEditFrame() {
		if (this.userEditFrame == null) {
			this.userEditFrame = new UserCreateDialog(null);
		}
		return this.userEditFrame;
	}
	
	/**
	* Module: Lecturer
	*/
	private StartTab startTab;
	private StartTabTableTop startTabTableTop;
	private StartTabTableBottom startTabTableBottom;
	private TimetableTabTable timetableTabTable;
	private TimetableTab timetableTab;
	private ChairTimetableTabTable chairTimetableTabTable;
	private ProfessorshipTimetableTab chairTimetableTab;
	
	public StartTab getLecturerStartTab() {
		if (this.startTab == null) {
			this.startTab = new StartTab();
		}
		return this.startTab;
	}
	public StartTabTableTop getLecturerStartTabTableTop() {
		if (this.startTabTableTop == null) {
			this.startTabTableTop = new StartTabTableTop();
		}
		return this.startTabTableTop;
	}
	public StartTabTableBottom getLecturerStartTabTableBottom() {
		if (this.startTabTableBottom == null) {
			this.startTabTableBottom = new StartTabTableBottom();
		}
		return this.startTabTableBottom;
	}
	public TimetableTabTable getLecturerTimetableTabTable() {
		if (this.timetableTabTable == null) {
			this.timetableTabTable = new TimetableTabTable();
		}
		return this.timetableTabTable;
	}
	public TimetableTab getLecturerTimetableTab() {
		if (this.timetableTab == null) {
			this.timetableTab = new TimetableTab();
		}
		return this.timetableTab;
	}
	public ProfessorshipTimetableTab getChairTimetableTab() {
		if (this.chairTimetableTab == null) {
			this.chairTimetableTab = new ProfessorshipTimetableTab();
		}
		return this.chairTimetableTab;
	}
	public ChairTimetableTabTable getLecturerChairimetableTabTable() {
		if (this.chairTimetableTabTable == null) {
			this.chairTimetableTabTable = new ChairTimetableTabTable();
		}
		return this.chairTimetableTabTable;
	}
	
	
	
	/**
	* Module: Student Profile - will probably not be implemented in the first version of the program
	*/
	/*
	private StudProfil studProfilGui;
	
	public StudProfil getStudProfil() {
		if (this.studProfilGui == null) {
			this.studProfilGui = new StudProfil();
		}
		return this.studProfilGui;
	}
	*/
}
