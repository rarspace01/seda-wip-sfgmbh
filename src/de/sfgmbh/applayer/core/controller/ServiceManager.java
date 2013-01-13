package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.comlayer.core.model.BaseTableMain;
import de.sfgmbh.comlayer.core.model.RoomTableMain;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.lecturer.model.ProfessorshipTimetableTabTable;
import de.sfgmbh.comlayer.lecturer.model.TimetableTabTable;
import de.sfgmbh.comlayer.lecturer.views.CourseEditFrame;
import de.sfgmbh.comlayer.lecturer.views.CourseFrame;
import de.sfgmbh.comlayer.lecturer.views.ProfessorshipTimetableTab;
import de.sfgmbh.comlayer.lecturer.views.RoomRequestFrame;
import de.sfgmbh.comlayer.lecturer.views.StartTab;
import de.sfgmbh.comlayer.lecturer.views.TimetableTab;
import de.sfgmbh.comlayer.organisation.model.ProfessorshipTabTable;
import de.sfgmbh.comlayer.organisation.model.RequestTabTable;
import de.sfgmbh.comlayer.organisation.model.RoomTabTable;
import de.sfgmbh.comlayer.organisation.model.RoomtableTable;
import de.sfgmbh.comlayer.organisation.model.UserTabTable;
import de.sfgmbh.comlayer.organisation.views.CounterproposalFrame;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipEditFrame;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipFrame;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipTab;
import de.sfgmbh.comlayer.organisation.views.RequestTab;
import de.sfgmbh.comlayer.organisation.views.RoomEditFrame;
import de.sfgmbh.comlayer.organisation.views.RoomFrame;
import de.sfgmbh.comlayer.organisation.views.RoomTab;
import de.sfgmbh.comlayer.organisation.views.RoomtableTab;
import de.sfgmbh.comlayer.organisation.views.UserEditFrame;
import de.sfgmbh.comlayer.organisation.views.UserCreateDialog;
import de.sfgmbh.comlayer.organisation.views.UserTab;
import de.sfgmbh.comlayer.timetable.views.PublicTimetableTab;


public class ServiceManager {
	
	private static ServiceManager uniqueInstance_ = new ServiceManager();
	
	public ServiceManager() {
		uniqueInstance_ = this;
	}
	
	public static ServiceManager getInstance() {
		return uniqueInstance_;
	}

	public void dispose() {
		uniqueInstance_ = null;
	}
	
	/**
	* Module: Core (global area - all other modules have dependencies to this one)
	*/
	protected BaseTab baseTab;
	protected InfoDialog infoDialog;
	protected BaseTableMain baseTableMain;
	protected RoomTableMain roomTableMain;
	
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
	protected PublicTimetableTab publicTimetableTab;
	
	public PublicTimetableTab getPublicTimetableTab() {
		if (this.publicTimetableTab == null) {
			this.publicTimetableTab = new PublicTimetableTab();
		}
		return this.publicTimetableTab;
	}
	
	/**
	* Module: Organization
	*/
	protected RoomTab roomTab;
	protected UserTab userTab;
	protected ProfessorshipTab professorshipTab;
	protected RequestTab requestTab;
	protected RoomFrame roomFrame;
	protected RoomEditFrame roomEditFrame;
	protected UserCreateDialog userFrame;
	protected UserEditFrame userEditFrame;
	protected ProfessorshipFrame professorshipFrame;
	protected ProfessorshipEditFrame professorshipEditFrame;
	protected CounterproposalFrame counterproposalFrame;
	protected RequestTabTable requestTabTable;
	protected UserTabTable userTabTable;
	protected RoomTabTable roomTabTable;
	protected ProfessorshipTabTable professorshipTabTable;
	protected RoomtableTab roomtableTab;
	protected RoomtableTable roomtableTable;
	
	public RoomtableTable getOrgaRoomtableTableModel() {
		if (this.roomtableTable == null) {
			this.roomtableTable = new RoomtableTable();
		}
		return this.roomtableTable;
	}
	public RoomtableTab getOrgaRoomtableTab() {
		if (this.roomtableTab == null) {
			this.roomtableTab = new RoomtableTab();
		}
		return this.roomtableTab;
	}
	public ProfessorshipTabTable getOrgaProfessorshipTableModel() {
		if (this.professorshipTabTable == null) {
			this.professorshipTabTable = new ProfessorshipTabTable();
		}
		return this.professorshipTabTable;
	}
	public RoomTabTable getOrgaRoomTableModel() {
		if (this.roomTabTable == null) {
			this.roomTabTable = new RoomTabTable();
		}
		return this.roomTabTable;
	}
	public UserTabTable getOrgaUserTableModel() {
		if (this.userTabTable == null) {
			this.userTabTable = new UserTabTable();
		}
		return this.userTabTable;
	}
	public RequestTabTable getOrgaRequestTableModel() {
		if (this.requestTabTable == null) {
			this.requestTabTable = new RequestTabTable();
		}
		return this.requestTabTable;
	}
	public RoomTab getOrgaRoomTab() {
		if (this.roomTab == null) {
			this.roomTab = new RoomTab();
		}
		return this.roomTab;
	}
	public UserTab getOrgaUserTab() {
		if (this.userTab == null) {
			this.userTab = new UserTab();
		}
		return this.userTab;
	}
	public RequestTab getOrgaRquestTab() {
		if (this.requestTab == null) {
			this.requestTab = new RequestTab();
		}
		return this.requestTab;
	}
	public ProfessorshipTab getOrgaProfessorshipTab() {
		if (this.professorshipTab == null) {
			this.professorshipTab = new ProfessorshipTab();
		}
		return this.professorshipTab;
	}
	public RoomFrame getOrgaRoomFrame() {
		if (this.roomFrame == null) {
			this.roomFrame = new RoomFrame();
		}
		return this.roomFrame;
	}
	public RoomEditFrame getOrgaRoomEditFrame() {
		if (this.roomEditFrame == null) {
			this.roomEditFrame = new RoomEditFrame();
		}
		return this.roomEditFrame;
	}
	public UserCreateDialog getOrgaUserCreateDialog() {
		if (this.userFrame == null) {
			this.userFrame = new UserCreateDialog();
		}
		return this.userFrame;
	}
	public UserEditFrame getOrgaUserEditFrame() {
		if (this.userEditFrame == null) {
			this.userEditFrame = new UserEditFrame();
		}
		return this.userEditFrame;
	}
	public ProfessorshipFrame getOrgaProfessorshipFrame() {
		if (this.professorshipFrame == null) {
			this.professorshipFrame = new ProfessorshipFrame();
		}
		return this.professorshipFrame;
	}
	public ProfessorshipEditFrame getOrgaProfessorshipEditFrame() {
		if (this.professorshipEditFrame == null) {
			this.professorshipEditFrame = new ProfessorshipEditFrame();
		}
		return this.professorshipEditFrame;
	}
	public CounterproposalFrame getOrgaCounterproposalFrame() {
		if (this.counterproposalFrame == null) {
			this.counterproposalFrame = new CounterproposalFrame();
		}
		return this.counterproposalFrame;
	}
	
	/**
	* Module: Lecturer
	*/
	protected StartTab startTab;
	protected CourseFrame courseFrame;
	protected CourseEditFrame courseEditFrame;
	protected RoomRequestFrame roomRquestFrame;
	protected TimetableTabTable timetableTabTable;
	protected TimetableTab timetableTab;
	protected ProfessorshipTimetableTabTable professorshipTimetableTabTable;
	protected ProfessorshipTimetableTab professorshipTimetableTab;
	
	public StartTab getLecturerStartTab() {
		if (this.startTab == null) {
			this.startTab = new StartTab();
		}
		return this.startTab;
	}
	public CourseFrame getLecturerCourseFrame() {
		if (this.courseFrame == null) {
			this.courseFrame = new CourseFrame();
		}
		return this.courseFrame;
	}
	public CourseEditFrame getLecturerCourseEditFrame() {
		if (this.courseEditFrame == null) {
			this.courseEditFrame = new CourseEditFrame();
		}
		return this.courseEditFrame;
	}
	public RoomRequestFrame getLecturerRoomRequestFrame() {
		if (this.roomRquestFrame == null) {
			this.roomRquestFrame = new RoomRequestFrame();
		}
		return this.roomRquestFrame;
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
	public ProfessorshipTimetableTab getLecturerProfessorshipTimetableTab() {
		if (this.professorshipTimetableTab == null) {
			this.professorshipTimetableTab = new ProfessorshipTimetableTab();
		}
		return this.professorshipTimetableTab;
	}
	public ProfessorshipTimetableTabTable getLecturerProfessorshipTimetableTabTable() {
		if (this.professorshipTimetableTabTable == null) {
			this.professorshipTimetableTabTable = new ProfessorshipTimetableTabTable();
		}
		return this.professorshipTimetableTabTable;
	}
	/**
	* Module: Student Profile - will probably not be implemented in the first version of the program
	*/
	/**
	protected StudProfil studProfilGui;
	
	public StudProfil getStudProfil() {
		if (this.studProfilGui == null) {
			this.studProfilGui = new StudProfil();
		}
		return this.studProfilGui;
	}
	*/
	
	/**
	* Session Manager
	*/
	protected SessionManager sessionManager;
	
	public SessionManager getSessionManager(){
		if (this.sessionManager == null) {
			this.sessionManager = new SessionManager();
		}
		return this.sessionManager;
	}
}
