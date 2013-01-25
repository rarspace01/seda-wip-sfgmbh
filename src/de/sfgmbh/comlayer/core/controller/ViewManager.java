package de.sfgmbh.comlayer.core.controller;

import de.sfgmbh.comlayer.core.model.BaseTableMain;
import de.sfgmbh.comlayer.core.model.RoomTableMain;
import de.sfgmbh.comlayer.core.views.BaseTab;
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
import de.sfgmbh.comlayer.organisation.views.UserTab;
import de.sfgmbh.comlayer.timetable.model.CoreTimetableTabTable;
import de.sfgmbh.comlayer.timetable.views.CoreTimetableTab;

/**
 * Manage instances of view objects as a lot of view objects shall be present
 * only one time or need special treatment. This is helpful to avoid too complex
 * dependency injection chains or a lot of singletons.
 * 
 * @author anna
 * @author hannes
 * @author denis
 * 
 */
public class ViewManager {

	private static ViewManager uniqueInstance_ = new ViewManager();

	private ViewManager() {
	}

	/**
	 * Get the singleton instance of the view manager
	 * 
	 * @return the view manager
	 */
	public static ViewManager getInstance() {
		if (uniqueInstance_ == null) {
			uniqueInstance_ = new ViewManager();
		}
		return uniqueInstance_;
	}

	/**
	 * Module: Core (global area - all other modules have dependencies to this
	 * one)
	 */
	private BaseTab baseTab;
	private BaseTableMain baseTableMain;
	private RoomTableMain roomTableMain;
	private LiveTickerPanel liveTickerPanel;
	private CoreTimetableTab coreTimetableTab_;
	private CoreTimetableTabTable coreTimetableTabTable_;

	/**
	 * @return the core base tab
	 */
	public BaseTab getCoreBaseTab() {
		if (this.baseTab == null) {
			this.baseTab = new BaseTab();
		}
		return this.baseTab;
	}

	/**
	 * @return a live ticker panel
	 */
	public LiveTickerPanel getCoreLiveTickerPanel() {
		// Always return a new instance as one Swing component my only be
		// registered in one single Frame.
		// Later here needs to be a logic to sync the sliding position of all
		// created instances.
		this.liveTickerPanel = new LiveTickerPanel();
		return this.liveTickerPanel;
	}

	/**
	 * @return the table model of the core base table
	 */
	public BaseTableMain getCoreBaseTableModel() {
		if (this.baseTableMain == null) {
			this.baseTableMain = new BaseTableMain();
		}
		return this.baseTableMain;
	}

	/**
	 * @return the table model of the core room table
	 */
	public RoomTableMain getCoreRoomTableModel() {
		if (this.roomTableMain == null) {
			this.roomTableMain = new RoomTableMain();
		}
		return this.roomTableMain;
	}

	/**
	 * Module: Timetable
	 */
	/**
	 * @return the timetable tab
	 */
	public CoreTimetableTab getCoreTimetableTab() {
		if (this.coreTimetableTab_ == null) {
			this.coreTimetableTab_ = new CoreTimetableTab();
		}
		return this.coreTimetableTab_;
	}

	/**
	 * @return the table of the timetable tab
	 */
	public CoreTimetableTabTable getCoreTimetableTabTable() {
		if (this.coreTimetableTabTable_ == null) {
			this.coreTimetableTabTable_ = new CoreTimetableTabTable();
		}
		return this.coreTimetableTabTable_;
	}

	/**
	 * Module: Organization
	 */
	private RoomTab roomTab;
	private UserTab userTab;
	private ChairTab chairTab;
	private RequestTab requestTab;
	private RoomFrame roomFrame;
	private RequestTabTable requestTabTable;
	private UserTabTable userTabTable;
	private RoomTabTable roomTabTable;
	private ChairTabTable chairTabTable;
	private RoomtableTab roomtableTab;
	private RoomtableTable roomtableTable;

	/**
	 * @return the roomtableTable
	 */
	public RoomtableTable getOrgaRoomtableTableModel() {
		if (this.roomtableTable == null) {
			this.roomtableTable = new RoomtableTable();
		}
		return this.roomtableTable;
	}

	/**
	 * @return the roomtableTab
	 */
	public RoomtableTab getOrgaRoomtableTab() {
		if (this.roomtableTab == null) {
			this.roomtableTab = new RoomtableTab();
		}
		return this.roomtableTab;
	}

	/**
	 * @return the chairTabTable
	 */
	public ChairTabTable getOrgaChairTableModel() {
		if (this.chairTabTable == null) {
			this.chairTabTable = new ChairTabTable();
		}
		return this.chairTabTable;
	}

	/**
	 * @return the roomTabTable
	 */
	public RoomTabTable getOrgaRoomTableModel() {
		if (this.roomTabTable == null) {
			this.roomTabTable = new RoomTabTable();
		}
		return this.roomTabTable;
	}

	/**
	 * @return the userTabTable
	 */
	public UserTabTable getOrgaUserTableModel() {
		if (this.userTabTable == null) {
			this.userTabTable = new UserTabTable();
		}
		return this.userTabTable;
	}

	/**
	 * @return the requestTabTable
	 */
	public RequestTabTable getOrgaRequestTableModel() {
		if (this.requestTabTable == null) {
			this.requestTabTable = new RequestTabTable();
		}
		return this.requestTabTable;
	}

	/**
	 * @return the roomTab
	 */
	public RoomTab getOrgaRoomTab() {
		if (this.roomTab == null) {
			this.roomTab = new RoomTab();
		}
		return this.roomTab;
	}

	/**
	 * @return the userTab
	 */
	public UserTab getOrgaUserTab() {
		if (this.userTab == null) {
			this.userTab = new UserTab();
		}
		return this.userTab;
	}

	/**
	 * @return the requestTab
	 */
	public RequestTab getOrgaRquestTab() {
		if (this.requestTab == null) {
			this.requestTab = new RequestTab();
		}
		return this.requestTab;
	}

	/**
	 * @return the chairTab
	 */
	public ChairTab getOrgaChairTab() {
		if (this.chairTab == null) {
			this.chairTab = new ChairTab();
		}
		return this.chairTab;
	}

	/**
	 * @return roomFrame
	 */
	public RoomFrame getOrgaRoomFrame() {
		if (this.roomFrame == null) {
			this.roomFrame = new RoomFrame();
		}
		return this.roomFrame;
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

	/**
	 * @return the lecturer start tab
	 */
	public StartTab getLecturerStartTab() {
		if (this.startTab == null) {
			this.startTab = new StartTab();
		}
		return this.startTab;
	}

	/**
	 * @return the lecturer start tab top table
	 */
	public StartTabTableTop getLecturerStartTabTableTop() {
		if (this.startTabTableTop == null) {
			this.startTabTableTop = new StartTabTableTop();
		}
		return this.startTabTableTop;
	}

	/**
	 * @return the lecturer start tab bottom table
	 */
	public StartTabTableBottom getLecturerStartTabTableBottom() {
		if (this.startTabTableBottom == null) {
			this.startTabTableBottom = new StartTabTableBottom();
		}
		return this.startTabTableBottom;
	}

	/**
	 * @return the lecturer start tab
	 */
	public TimetableTabTable getLecturerTimetableTabTable() {
		if (this.timetableTabTable == null) {
			this.timetableTabTable = new TimetableTabTable();
		}
		return this.timetableTabTable;
	}

	/**
	 * @return the lecturer timetable tab
	 */
	public TimetableTab getLecturerTimetableTab() {
		if (this.timetableTab == null) {
			this.timetableTab = new TimetableTab();
		}
		return this.timetableTab;
	}

	/**
	 * @return the lecturer chair timetable tab
	 */
	public ProfessorshipTimetableTab getChairTimetableTab() {
		if (this.chairTimetableTab == null) {
			this.chairTimetableTab = new ProfessorshipTimetableTab();
		}
		return this.chairTimetableTab;
	}

	/**
	 * @return the lecturer chair timetable table
	 */
	public ChairTimetableTabTable getLecturerChairimetableTabTable() {
		if (this.chairTimetableTabTable == null) {
			this.chairTimetableTabTable = new ChairTimetableTabTable();
		}
		return this.chairTimetableTabTable;
	}

	/**
	 * Module: Student Profile - will probably not be implemented in the first
	 * version of the program
	 */
	/*
	 * private StudProfil studProfilGui;
	 * 
	 * public StudProfil getStudProfil() { if (this.studProfilGui == null) {
	 * this.studProfilGui = new StudProfil(); } return this.studProfilGui; }
	 */
}
