package de.sfgmbh.comlayer.core.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.applayer.core.definitions.IntfCtrlBaseTab;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.lecturer.views.StartTab;

/**
 * Action when the login button is clicked or enter is pressed
 * 
 * @author hannes
 * 
 */
public class BaseLogin implements ActionListener {

	private IntfCtrlBaseTab ctrlBaseTab_;
	private String version_;

	/**
	 * Creates a default BaseBtnLogin object
	 */
	public BaseLogin() {
		this.ctrlBaseTab_ = new CtrlBaseTab();
	}

	/**
	 * Creates a BaseBtnLogin object based on a submitted variant string<br>
	 * Supported variants are "login" and "logout".
	 * 
	 * @param variant
	 */
	public BaseLogin(String variant) {
		this.version_ = variant;
		this.ctrlBaseTab_ = new CtrlBaseTab();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Login is pressed
		// Setting cursor for any Component:
		ViewManager.getInstance().getCoreBaseTab()
				.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (this.version_ == null || this.version_.equals("login")) {
			try {

				String pwd = new String(ViewManager.getInstance()
						.getCoreBaseTab().getPwdPasswort().getPassword());
				String user = ViewManager.getInstance().getCoreBaseTab()
						.getTxtUsername().getText();

				IntfUser checkUser = ctrlBaseTab_.login(user, pwd);

				if (checkUser == null) {
					// currently here happens nothing

					// A organization member is logging in...
				} else if (checkUser.getClass_().equals("orga")) {
					this.callOrga();
					ViewManager.getInstance().getCoreBaseTab().getPanelLogin()
							.setVisible(false);
					ViewManager.getInstance().getCoreBaseTab().getPanelLogout()
							.setVisible(true);

					// A lecturer is logging in...
				} else if (checkUser.getClass_().equals("lecturer")) {
					this.callLecturer();
					ViewManager.getInstance().getCoreBaseTab().getPanelLogin()
							.setVisible(false);
					ViewManager.getInstance().getCoreBaseTab().getPanelLogout()
							.setVisible(true);

					// Check if there are counter or denied proposals and
					// display a popup if yes
					HashMap<String, String> filter = new HashMap<String, String>();
					filter.put("login", checkUser.getLogin_());
					List<IntfRoomAllocation> roomAllocationsForLogin = AppModel
							.getInstance().getRepositoryRoomAllocation()
							.getByFilter(filter);

					// Check the counter proposals
					for (IntfRoomAllocation roomAllocation : roomAllocationsForLogin) {
						if (roomAllocation.getApproved_().equals("counter")) {
							String orgaMsg = "";
							if (roomAllocation.getOrgaMessage_().length() > 1) {
								orgaMsg = "Folgende Nachricht wurde Ihnen von der Verwaltung dazu hinterlegt:<br /><span style='font-style:italic;'>"
										+ roomAllocation.getOrgaMessage_()
												.replace("\r", "<br />")
										+ "</span><br /><br />";
							}
							QuestionDialog dialog = new QuestionDialog(
									"Zu Ihrer Veranstaltung <strong>"
											+ roomAllocation.getCourse_()
													.getCourseAcronym_()
											+ " / "
											+ roomAllocation.getCourse_()
													.getCourseKind_()
											+ " im "
											+ roomAllocation.getSemester_()
											+ "</strong> konnte der Termin nicht gewährt werden. Die Verwaltung schlägt Ihnen stattdessen folgenden Termin vor:<br /><br /><strong>"
											+ ViewHelper.getDay(roomAllocation
													.getDay_())
											+ " von "
											+ ViewHelper.getTime(roomAllocation
													.getTime_())
											+ " Uhr<br />"
											+ roomAllocation.getRoom_()
													.getRoomNumber_()
											+ "<br /></strong> (Plätze: "
											+ roomAllocation.getRoom_()
													.getSeats_()
											+ ", PC-Plätze: "
											+ roomAllocation.getRoom_()
													.getPcseats_()
											+ ", Beamer: "
											+ roomAllocation.getRoom_()
													.getBeamer_()
											+ ", Visualizer: "
											+ roomAllocation.getRoom_()
													.getVisualizer_()
											+ ", Overheads: "
											+ roomAllocation.getRoom_()
													.getOverheads_()
											+ ", Tafeln: "
											+ roomAllocation.getRoom_()
													.getChalkboards_()
											+ ", Whiteboards: "
											+ roomAllocation.getRoom_()
													.getWhiteboards_()
											+ ")<br /><br />"
											+ orgaMsg
											+ "Wollen Sie diesen Termin annehmen?<br /> "
											+ "Wenn Sie sich noch nicht sicher sind, so verneinen Sie dies bitte. "
											+ "Sie können dann einfach selbst wieder eine Raumanfrage stellen. Allerdings nur, wenn Ihnen bis dorthin niemand zuvor "
											+ "kommt. Nehmen Sie den Termin jetzt an, so ist er sofort freigeben!",
									"Gegenvorschlag!");
							StartTab startTab = ViewManager.getInstance()
									.getLecturerStartTab();
							startTab.setRoomAllocation(roomAllocation);
							dialog.register(startTab);
							dialog.setVisible(true);
						}
					}

					// Check the denied allocations
					IntfCtrlStartTab ctrlStartTab = new CtrlStartTab();
					boolean hasUnseenDenied = false;
					String message = "Leider konnte ein oder mehrerer Ihrer gewünschten Veranstaltungstermine nicht genehmigt werden.<br />"
							+ "Dies betrifft folgende Veranstaltung(en):<br /><br />";
					for (IntfRoomAllocation roomAllocation : roomAllocationsForLogin) {
						if (roomAllocation.getApproved_().equals("denied")
								&& roomAllocation.getComment_().contains(
										"denied_at_")) {
							hasUnseenDenied = true;
							ctrlStartTab.hasBeenSeenAllocation(roomAllocation);

							message = message
									+ roomAllocation.getCourse_()
											.getCourseAcronym_()
									+ " in "
									+ roomAllocation.getRoom_()
											.getRoomNumber_()
									+ " am "
									+ ViewHelper.getDay(roomAllocation
											.getDay_())
									+ " von "
									+ ViewHelper.getTime(roomAllocation
											.getTime_()) + "Uhr<br />";
						}
					}
					if (hasUnseenDenied) {
						AppModel.getInstance()
								.getExceptionHandler()
								.setNewException(message,
										"Veranstaltungstermine abgelehnt!",
										"info");
					}
				}
			} catch (Exception ex) {
				AppModel.getInstance()
						.getExceptionHandler()
						.setNewException(
								"Leider hat mit dem Login etwas nicht geklappt.<br /><br />Fehler:<br />"
										+ ex.toString(), "Fehler!", "error");
			} finally {
				ViewManager.getInstance().getCoreBaseTab()
						.setCursor(Cursor.getDefaultCursor());
			}

		} else if (this.version_.equals("logout")) {
			try {

				ctrlBaseTab_.logout();
				BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
				baseTab.getMainTabbedContainerPane().removeAll();
				baseTab.getMainTabbedContainerPane().setVisible(false);
				baseTab.getContentPane().add(baseTab.getStartScreenPanel(),
						"Start");
				baseTab.getPwdPasswort().setText("");
				baseTab.getTxtUsername().setText("");
				baseTab.getStartScreenPanel().setVisible(true);
				baseTab.getComboBoxLecturerFilter().setSelectedItem("<alle>");
				ViewManager.getInstance().getCoreBaseTableModel().change();
				baseTab.getPanelLogin().setVisible(true);
				baseTab.getPanelLogout().setVisible(false);
			} catch (Exception ex) {
				AppModel.getInstance()
						.getExceptionHandler()
						.setNewException(
								"Leider hat mit dem Logout etwas nicht geklappt.<br /><br />Fehler:<br />"
										+ ex.toString(), "Fehler!", "error");
			} finally {
				ViewManager.getInstance().getCoreBaseTab()
						.setCursor(Cursor.getDefaultCursor());
			}
		}
		// Restore the Cursor
		ViewManager.getInstance().getCoreBaseTab()
				.setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * Initiates the GUI (tabs, tables, etc.) for the organization
	 */
	public void callOrga() {
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
		baseTab.getMainTabbedContainerPane().setVisible(true);
		baseTab.getMainTabbedContainerPane().removeAll();
		baseTab.getMainTabbedContainerPane().addTab("Start", null,
				baseTab.getStartScreenPanel(), null);
		baseTab.getMainTabbedContainerPane().addTab("Raumanfragenverwaltung",
				null, ViewManager.getInstance().getOrgaRquestTab(), null);
		ViewManager.getInstance().getOrgaRequestTableModel().change();
		baseTab.getMainTabbedContainerPane().setSelectedIndex(1);
		baseTab.getStartScreenPanel().setVisible(false);
		baseTab.getMainTabbedContainerPane().addTab("Nutzerverwaltung", null,
				ViewManager.getInstance().getOrgaUserTab(), null);
		ViewManager.getInstance().getOrgaUserTableModel().change();
		baseTab.getMainTabbedContainerPane().addTab("Lehrstuhlverwaltung",
				null, ViewManager.getInstance().getOrgaChairTab(), null);
		ViewManager.getInstance().getOrgaChairTableModel().change();
		baseTab.getMainTabbedContainerPane().addTab("Raumverwaltung", null,
				ViewManager.getInstance().getOrgaRoomTab(), null);
		ViewManager.getInstance().getOrgaRoomtableTableModel().change();
	}

	/**
	 * Initiates the GUI (tabs, tables, etc.) for lecturer
	 */
	public void callLecturer() {
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
		baseTab.getMainTabbedContainerPane().setVisible(true);
		baseTab.getMainTabbedContainerPane().removeAll();
		baseTab.getMainTabbedContainerPane().addTab("Start", null,
				baseTab.getStartScreenPanel(), null);
		baseTab.getMainTabbedContainerPane().addTab("Dozenten Übersicht", null,
				ViewManager.getInstance().getLecturerStartTab(), null);
		ViewManager.getInstance().getLecturerStartTabTableTop().change();
		ViewManager.getInstance().getLecturerStartTabTableBottom().change();
		baseTab.getMainTabbedContainerPane().setSelectedIndex(1);
		baseTab.getStartScreenPanel().setVisible(false);
		baseTab.getMainTabbedContainerPane()
				.addTab("Dozentenstundenplan", null,
						ViewManager.getInstance().getLecturerTimetableTab(),
						null);
		baseTab.getMainTabbedContainerPane().addTab("Lehrstuhlplan", null,
				ViewManager.getInstance().getChairTimetableTab(), null);
	}
}