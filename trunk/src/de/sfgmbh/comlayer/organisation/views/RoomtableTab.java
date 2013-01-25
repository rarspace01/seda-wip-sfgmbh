package de.sfgmbh.comlayer.organisation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RoomtableCmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RoomtableTabBtnPdf;

/**
 * Table for the room allocation overview on a specific room
 * 
 * @author denis
 * @author christian
 * 
 */
public class RoomtableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable timetableTable_;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester_;
	private JComboBox<String> comboBoxSemesterFilter_;
	private CmbboxFilterSemester comboBoxSemesterModel_ = new CmbboxFilterSemester(
			"select");
	private JLabel lblRoomplan;

	public RoomtableTab() {
		initialize();
	}

	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("",
				"[20px:20px,grow][131px][109px][129px][461px][grow][right]",
				"[][][383px][grow]"));

		lblRoomplan = new JLabel("<html><h3>Raumplan</h3></html>");
		add(lblRoomplan, "cell 1 0,aligny bottom");

		JLabel lblUniIcon = new JLabel();
		lblUniIcon
				.setIcon(new ImageIcon(
						BaseTab.class
								.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50, 50));
		add(lblUniIcon, "cell 6 0,alignx right,aligny top");

		scrollPane_ = new JScrollPane();
		scrollPane_
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane_, "cell 1 2 4 1,growx,aligny top");

		timetableTable_ = new JTable();

		scrollPane_.setViewportView(timetableTable_);

		timetableTable_.setBackground(Color.WHITE);
		timetableTable_.setModel(ViewManager.getInstance()
				.getOrgaRoomtableTableModel());

		// register table in the corresponding repostories
		AppModel.getInstance().getRepositoryRoomAllocation()
				.register(ViewManager.getInstance().getOrgaRoomTableModel());
		AppModel.getInstance().getRepositoryRoom()
				.register(ViewManager.getInstance().getOrgaRoomTableModel());

		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen
				.setToolTipText("<html>Hier können Sie den <br> unten stehenden<br>Wochenplan im <br> PDF Format ausdrucken<br>");
		btnPdfErzeugen.addActionListener(new RoomtableTabBtnPdf("pdfCreate"));
		add(btnPdfErzeugen, "cell 1 1,growx,aligny center");
		timetableTable_.getColumnModel().getColumn(0).setResizable(false);
		timetableTable_.getColumnModel().getColumn(0).setPreferredWidth(70);
		timetableTable_.getColumnModel().getColumn(0).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(0).setMaxWidth(105);
		timetableTable_.getColumnModel().getColumn(1).setResizable(true);
		timetableTable_.getColumnModel().getColumn(1).setPreferredWidth(50);
		timetableTable_.getColumnModel().getColumn(1).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(1).setMaxWidth(145);
		timetableTable_.getColumnModel().getColumn(2).setResizable(true);
		timetableTable_.getColumnModel().getColumn(2).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(2).setMaxWidth(145);
		timetableTable_.getColumnModel().getColumn(3).setPreferredWidth(50);
		timetableTable_.getColumnModel().getColumn(3).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(3).setMaxWidth(145);
		timetableTable_.getColumnModel().getColumn(4).setPreferredWidth(50);
		timetableTable_.getColumnModel().getColumn(4).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(4).setMaxWidth(145);
		timetableTable_.getColumnModel().getColumn(5).setMinWidth(50);
		timetableTable_.getColumnModel().getColumn(5).setMaxWidth(145);

		lblSemester_ = new JLabel("Semester:");

		comboBoxSemesterFilter_ = new JComboBox<String>();
		comboBoxSemesterFilter_.setModel(comboBoxSemesterModel_);
		comboBoxSemesterFilter_.addActionListener(new RoomtableCmbboxFilter());
		comboBoxSemesterFilter_.setEditable(true);
		comboBoxSemesterFilter_.setAutoscrolls(true);

		add(lblSemester_, "cell 3 1,alignx left,aligny center");
		add(comboBoxSemesterFilter_, "cell 3 1,alignx right,aligny center");
	}

	/**
	 * reloads the timetable for the selected room
	 */
	public void reloadRoomTable() {
		loadRoomTable(this.roomId_);
	}

	/**
	 * 
	 * @param roomId
	 */
	public void loadRoomTable(int roomId) {

		loadRoomTable(roomId, this.getComboBoxSemesterFilter()
				.getSelectedItem().toString());
	}

	/**
	 * loads the table depending on the roomId and semester
	 * 
	 * @param roomId
	 * @param semester
	 */
	public void loadRoomTable(int roomId, String semester) {

		this.roomId_ = roomId;
		IntfCtrlGenericTables genericTablesController = new CtrlGenericTables();

		// setting the filters
		HashMap<String, String> tableFilter = new HashMap<String, String>();
		// only show verified classes
		tableFilter.put("status", "accepted");
		tableFilter.put("courseenabled", "true");
		// setting roomid&semester
		tableFilter.put("roomid", "" + this.roomId_);
		tableFilter.put("semester", semester);
		// get Room allocations based on roomid + semester
		List<IntfRoomAllocation> roomAllocationList = AppModel.getInstance()
				.getRepositoryRoomAllocation().getByFilter(tableFilter);
		// calling the controller for reloading the gui
		genericTablesController.reloadTable(getStundenplanTable(),
				roomAllocationList, false, true);

		// Set the maximum size of the scroll pane (don't forget to add the
		// table header!)
		scrollPane_.setMaximumSize(new Dimension(32767, ((int) timetableTable_
				.getPreferredSize().getHeight() + 26)));
		this.updateUI();
	}

	/**
	 * 
	 * @return the timetableTable
	 */
	public JTable getStundenplanTable() {
		return timetableTable_;
	}

	/**
	 * 
	 * @return the scrollPane_
	 */
	public JScrollPane getScrollPane_() {
		return scrollPane_;
	}

	/**
	 * 
	 * @return the roomId_
	 */
	public int getRoomId_() {
		return roomId_;
	}

	/**
	 * 
	 * @return the comboBoxSemesterFilter
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter_;
	}

}
