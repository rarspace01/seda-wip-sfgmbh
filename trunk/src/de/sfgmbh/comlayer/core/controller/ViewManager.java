package de.sfgmbh.comlayer.core.controller;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.model.BaseTableMain;
import de.sfgmbh.comlayer.core.model.RoomTableMain;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.lecturer.model.ProfessorshipTimetableTabTable;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableBottom;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableTop;
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
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipEditFrame;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipFrame;
import de.sfgmbh.comlayer.organisation.views.ProfessorshipTab;
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
	private ProfessorshipTab professorshipTab;
	private RequestTab requestTab;
	private RoomFrame roomFrame;
	private UserCreateDialog userFrame;
	private UserCreateDialog userEditFrame;
	private ProfessorshipFrame professorshipFrame;
	private ProfessorshipEditFrame professorshipEditFrame;
	private CounterproposalDialog counterproposalFrame;
	private RequestTabTable requestTabTable;
	private UserTabTable userTabTable;
	private RoomTabTable roomTabTable;
	private ProfessorshipTabTable professorshipTabTable;
	private RoomtableTab roomtableTab;
	private RoomtableTable roomtableTable;
	
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
	public UserCreateDialog getOrgaUserCreateDialog() {
		if (this.userFrame == null) {
			this.userFrame = new UserCreateDialog();
		}
		return this.userFrame;
	}
	public UserCreateDialog getOrgaUserEditFrame(User user) {
		if (this.userEditFrame == null) {
			this.userEditFrame = new UserCreateDialog(user);
		}
		return this.userEditFrame;
	}
	public UserCreateDialog getOrgaUserEditFrame() {
		if (this.userEditFrame == null) {
			this.userEditFrame = new UserCreateDialog(null);
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
	public CounterproposalDialog getOrgaCounterproposalFrame() {
		if (this.counterproposalFrame == null) {
			this.counterproposalFrame = new CounterproposalDialog();
		}
		return this.counterproposalFrame;
	}
	
	/**
	* Module: Lecturer
	*/
	private StartTab startTab;
	private StartTabTableTop startTabTableTop;
	private StartTabTableBottom startTabTableBottom;
	private CourseFrame courseFrame;
	private CourseEditFrame courseEditFrame;
	private RoomRequestFrame roomRquestFrame;
	private TimetableTabTable timetableTabTable;
	private TimetableTab timetableTab;
	private ProfessorshipTimetableTabTable professorshipTimetableTabTable;
	private ProfessorshipTimetableTab professorshipTimetableTab;
	
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
	private StudProfil studProfilGui;
	
	public StudProfil getStudProfil() {
		if (this.studProfilGui == null) {
			this.studProfilGui = new StudProfil();
		}
		return this.studProfilGui;
	}
	*/
}
