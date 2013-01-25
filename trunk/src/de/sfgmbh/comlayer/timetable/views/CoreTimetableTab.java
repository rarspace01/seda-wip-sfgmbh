package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.timetable.controller.CoreTimetableTabBtnPdf;

/**
 * Tab for the Students to let them view their colelcted Tiemtable
 * 
 * @author anna - guiparts
 * @author denis - methods
 * 
 */
public class CoreTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable timetableTable_;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester_;
	private List<IntfRoomAllocation> roomAllocList_ = new ArrayList<IntfRoomAllocation>();
	private JLabel lblvaluesemester_;
	private JButton btnTimetableReset_;

	public CoreTimetableTab() {
		initialize();
	}

	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[20px:20px,grow][131px][50px][92.00px][461px][20px:20px,grow][]", "[top][grow,shrink 0][grow]"));

		btnTimetableReset_ = new JButton("Stundenplan zurücksetzen");
		btnTimetableReset_.setToolTipText("<html>Klicken Sie hier<br> um alle Lehrveranstaltungen aus <br> der Sammlung zu löschen <br>und um einen neuen Stundenplan <br> zu generieren</html>");
		btnTimetableReset_
				.addActionListener(new CoreTimetableTabBtnPdf("reset"));
		add(btnTimetableReset_, "cell 4 0,aligny center");

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
		scrollPane_.setBorder(null);
		add(scrollPane_, "cell 1 1 4 1,growx,aligny top");

		timetableTable_ = new JTable();

		scrollPane_.setViewportView(timetableTable_);

		timetableTable_.setBackground(Color.WHITE);
		timetableTable_.setModel(ViewManager.getInstance()
				.getCoreTimetableTabTable());

		AppModel.getInstance().getRepositoryRoomAllocation()
				.register(ViewManager.getInstance().getCoreTimetableTabTable());

		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.setToolTipText("<html>Hier können Sie den <br> unten stehenden<br>Wochenplan im <br> PDF Format ausdrucken<br>");
		btnPdfErzeugen
				.addActionListener(new CoreTimetableTabBtnPdf("pdfCreate"));
		add(btnPdfErzeugen, "cell 1 0,growx,aligny center");
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

		add(lblSemester_, "flowx,cell 3 0,alignx left,aligny center");

		lblvaluesemester_ = new JLabel("#valueSemester#");
		add(lblvaluesemester_, "cell 3 0,aligny center");

		reloadRoomTable();
	}

	/**
	 * reloads the timetable corresponding to the selection on the basetab
	 * 
	 * @author denis
	 */
	public void reloadRoomTable() {
		// controller for table methods
		IntfCtrlGenericTables genericTablesController = new CtrlGenericTables();
		// set the Semester label
		this.lblvaluesemester_.setText(ViewManager.getInstance()
				.getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem()
				.toString());
		// get the room allocations from the temprary storage
		List<IntfRoomAllocation> roomAllocationList = this.roomAllocList_;
		// call the reload method
		genericTablesController.reloadTable(getStundenplanTable(),
				roomAllocationList, true, true);

		// Set the maximum size of the scroll pane (don't forget to add the
		// table header!)
		scrollPane_.setMaximumSize(new Dimension(32767, ((int) timetableTable_
				.getPreferredSize().getHeight() + 26)));
		this.updateUI();
	}

	/**
	 * adds an room allocation to he table, stores it locally
	 * 
	 * @author denis
	 * @param roomallocations
	 *            - {@link RoomAllocation}
	 */
	public void addAllocation(List<RoomAllocation> roomallocations) {

		for (int i = 0; i < roomallocations.size(); i++) {
			if (!roomAllocList_.contains(roomallocations.get(i))) {
				this.roomAllocList_.add(roomallocations.get(i));
			}
		}
		reloadRoomTable();
	}

	/**
	 * resets the collectedAllocations
	 * 
	 * @author denis
	 */
	public void resetPlan() {
		roomAllocList_.clear();
		reloadRoomTable();
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
	 * @return scrollPane_
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

}
