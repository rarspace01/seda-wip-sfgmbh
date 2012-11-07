package modules.dozenten.views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import services.Bootstrap;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class DozentenLehrstuhlplanTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable LehrstuhlStundenplanTable;


	/**
	 * Create the panel.
	 */
	public DozentenLehrstuhlplanTab() {
		setAutoscrolls(true);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 51, 854, 214);
		add(scrollPane);
		
		LehrstuhlStundenplanTable = new JTable();
		
		scrollPane.setViewportView(LehrstuhlStundenplanTable);
		
	
		LehrstuhlStundenplanTable.setBackground(Color.WHITE);
		LehrstuhlStundenplanTable.setModel(Bootstrap.serviceManager.getDozStundenplanTableModel());
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.setBounds(389, 17, 112, 23);
		add(btnPdfErzeugen);
		
		JLabel lblWochenplanFrDen = new JLabel("<html><h3>Wochenplan f\u00FCr den Lehrstuhl SEDA</h3></html>");
		lblWochenplanFrDen.setFont(new Font("Arial", Font.PLAIN, 11));
		lblWochenplanFrDen.setBounds(20, 26, 359, 14);
		add(lblWochenplanFrDen);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(0).setMaxWidth(200);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setResizable(false);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(1).setMaxWidth(200);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setResizable(false);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMinWidth(75);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(2).setMaxWidth(200);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMinWidth(80);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(3).setMaxWidth(200);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMinWidth(70);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(4).setMaxWidth(200);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMinWidth(70);
		LehrstuhlStundenplanTable.getColumnModel().getColumn(5).setMaxWidth(200);

	}
}
