package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.lecturer.views.StartTab;


/**
 * Action when the login button is clicked or enter is pressed
 * 
 * @author hannes
 *
 */
public class BaseLogin implements ActionListener {
	
	private CtrlBaseTab ctrlBaseTab;
	private String version;
	
	/**
	 * Creates a default BaseBtnLogin object
	 */
	public BaseLogin() {
		this.ctrlBaseTab = new CtrlBaseTab();
	}
	
	/**
	 * Creates a BaseBtnLogin object based on a submitted variant string
	 * @param version
	 */
	public BaseLogin(String version) {
		this.version = version;
		this.ctrlBaseTab = new CtrlBaseTab();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Login is pressed
		if (this.version == null || this.version.equals("login")) {
			String pwd = new String(ViewManager.getInstance().getCoreBaseTab().getPwdPasswort().getPassword());
			String user = ViewManager.getInstance().getCoreBaseTab().getTxtUsername().getText();
			
			User checkUser = ctrlBaseTab.login(user, pwd);
			
			if (checkUser == null) {
				// currently here happens nothing
				
			// A organization member is logging in...
			} else if (checkUser.getClass_().equals("orga")) {
				this.callOrga();
				ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
				
			// A lecturer is logging in...
			} else if (checkUser.getClass_().equals("lecturer")) {
				this.callLecturer();
				ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
				
				// Check if there are counter proposals and display a popup if yes
				HashMap<String,String> filter = new HashMap<String,String>();
				filter.put("login", checkUser.getLogin_());
				for (RoomAllocation ra : AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter)) {
					if (ra.getApproved_().equals("counter")) {
						String orgaMsg = "";
						if (ra.getOrgaMessage_().length() > 1) {
							orgaMsg = "Folgende Nachricht wurde Ihnen von der Organisation dazu hinterlegt:<br /><span style='font-style:italic;'>" +
									ra.getOrgaMessage_().replace("\r", "<br />") + "</span><br /><br />";
						}
						QuestionDialog dialog = new QuestionDialog("Zu Ihrer Veranstaltung <strong>" +
								ra.getCourse_().getCourseAcronym_() + " / " + ra.getCourse_().getCourseKind_() + " im " + ra.getSemester_() +
								"</strong> konnte der Termin nicht gewährt werden. Die Verwaltung schlägt ihnen stattdessen folgenden Termin vor:<br /><br /><strong>" +
								ViewHelper.getDay(ra.getDay_()) + " von " +
								ViewHelper.getTime(ra.getTime_()) + " Uhr<br />" +
								ra.getRoom_().getRoomNumber_() + "<br /></strong> (Plätze: " + ra.getRoom_().getSeats_() +
								", PC-Plätze: " + ra.getRoom_().getPcseats_() +
								", Beamer: " + ra.getRoom_().getBeamer_() + 
								", Visualizer: " + ra.getRoom_().getVisualizer_() +
								", Overheads: " + ra.getRoom_().getOverheads_() +
								", Tafeln: " + ra.getRoom_().getChalkboards_() + 
								", Whiteboards: " + ra.getRoom_().getWhiteboards_() + ")<br /><br />" +
								orgaMsg +
								"Wollen Sie diesen Termin annehmen?<br /> " +
								"Wenn Sie sich noch nicht sicher sind, so verneinen Sie dies bitte. " +
								"Sie können dann einfach selbst wieder eine Raumanfrage stellen. Allerdings nur, wenn Ihnen bis dorthin niemand zuvor " +
								"kommt. Nehmen Sie den Termin jetzt an, so ist er sofort freigeben!", 
								"Gegenvorschlag!");
						StartTab startTab = ViewManager.getInstance().getLecturerStartTab();
						startTab.setRoomAllocation(ra);
						dialog.register(startTab);
						dialog.setVisible(true);
					}
				}
				
			}
			
		} else if (this.version.equals("logout")){
			ctrlBaseTab.logout();
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getContentPane().add(ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), "Start");
			ViewManager.getInstance().getCoreBaseTab().getPwdPasswort().setText("");
			ViewManager.getInstance().getCoreBaseTab().getTxtUsername().setText("");
			ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(false);
		}
	}
	
	/**
	 * Initiates the GUI (tabs, tables, etc.) for the organization 
	 */
	public void callOrga() {
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(true);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Start", null, ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumanfrage-Verwaltung", null, ViewManager.getInstance().getOrgaRquestTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(1);
		ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(false);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Nutzerverwaltung", null, ViewManager.getInstance().getOrgaUserTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlverwaltung", null, ViewManager.getInstance().getOrgaChairTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumverwaltung", null, ViewManager.getInstance().getOrgaRoomTab(), null);
	}
	
	/**
	 * Initiates the GUI (tabs, tables, etc.) for lecturer
	 */
	public void callLecturer() {
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(true);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Start", null, ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Dozenten Bereich", null, ViewManager.getInstance().getLecturerStartTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(1);
		ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(false);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Dozentenstundenplan", null, ViewManager.getInstance().getLecturerTimetableTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlplan", null, ViewManager.getInstance().getChairTimetableTab(), null);
	}
}