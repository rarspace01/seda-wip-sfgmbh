package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.ChairTabBtnsControl;
import de.sfgmbh.comlayer.organisation.controller.ChairTabCmbboxFilter;

/**
 * 
 * @author anna
 *
 */
public class ChairTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldChairname_;
	private JTable chairOrgaTable_;
	private JLabel lblChairname_;
	private JScrollPane organisationTableScrollPane_;
	private JPanel buttonPanel_;
	private JButton btnAdd_;
	private JButton btnDelete_;
	private JPanel uniIconPanel_;
	private JLabel lblUniIcon_;
	private TableRowSorter<TableModel> rowSorter_;

	/**
	 * Create the frame.
	 */
	public ChairTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 50));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow][grow][grow][100px:100px:100px]", "[][][grow][bottom]"));
		
		JLabel lblChairorganisation = new JLabel("Lehrstuhlverwaltung");
		lblChairorganisation.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblChairorganisation, "cell 0 0,alignx center,aligny bottom");
		
		lblChairname_ = new JLabel("Lehrstuhlname:");
		add(lblChairname_, "cell 2 0,aligny bottom");
		
		textFieldChairname_ = new JTextField();
		textFieldChairname_.setToolTipText("<html>Sie können die Liste der <br> Lehrstühle über diesen<br>Filter begrenzen</html>");
		textFieldChairname_.addActionListener(new ChairTabCmbboxFilter());
		
		uniIconPanel_ = new JPanel();
		add(uniIconPanel_, "cell 6 0,alignx right,aligny top");
		lblUniIcon_ = new JLabel();
		lblUniIcon_.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon_.setMaximumSize(new Dimension(50,50));
		uniIconPanel_.add(lblUniIcon_);
		textFieldChairname_.setColumns(10);
		add(textFieldChairname_, "cell 2 1,growx");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow");
		
		organisationTableScrollPane_ = new JScrollPane();
		organisationTableScrollPane_.setMinimumSize(new Dimension(400, 25));
		add(organisationTableScrollPane_, "flowx,cell 2 2 4 1,grow");
		
		chairOrgaTable_ = new JTable();
		chairOrgaTable_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		chairOrgaTable_.setModel(ViewManager.getInstance().getOrgaChairTableModel());
		chairOrgaTable_.setShowVerticalLines(false);
		chairOrgaTable_.getColumnModel().removeColumn(chairOrgaTable_.getColumn("Hidden"));
		chairOrgaTable_.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane_.setViewportView(chairOrgaTable_);
		
		//Enable table sorting for the model
		rowSorter_ = new TableRowSorter<TableModel>();
		chairOrgaTable_.setRowSorter(rowSorter_);
		rowSorter_.setModel(ViewManager.getInstance().getOrgaChairTableModel());
		rowSorter_.sort();
		
		buttonPanel_ = new JPanel();
		buttonPanel_.setLayout(null);
		buttonPanel_.setMinimumSize(new Dimension(80, 10));
		buttonPanel_.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel_, "cell 6 2,grow");
		
		btnAdd_ = new JButton("hinzufügen");
		btnAdd_.setToolTipText("<html>Klicken Sie hier <br>um einen neuen Lehrstuhl anzulegen</html>");
		btnAdd_.addActionListener(new ChairTabBtnsControl("add"));
		btnAdd_.setBounds(6, 11, 88, 23);
		buttonPanel_.add(btnAdd_);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.setToolTipText("<html>Selektieren Sie einen Lehrstuhl <br> und klicken Sie hier um dessen Eigenschaften zu ändern</html>");
		btnBearbeiten.addActionListener(new ChairTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel_.add(btnBearbeiten);
		
		btnDelete_ = new JButton("l\u00F6schen");
		btnDelete_.setToolTipText("<html>Selektieren Sie einen Lehrstuhl<br> um ihn zu löschen</html>");
		btnDelete_.addActionListener(new ChairTabBtnsControl("delete"));
		btnDelete_.setBounds(6, 79, 88, 23);
		buttonPanel_.add(btnDelete_);
				
	}
	/**
	 * @return the textFieldChairname
	 */
	public JTextField getTextFieldChairname() {
		return textFieldChairname_;
	}
	/**
	 * @return the chairOrgaTable
	 */
	public JTable getChairOrgaTable() {
		return chairOrgaTable_;
	}
	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter_;
	}
	/**
	 * @param rowSorter the rowSorter to set
	 */
	public void setRowSorter(TableRowSorter<TableModel> rowSorter) {
		this.rowSorter_ = rowSorter;
	}
	
	
}