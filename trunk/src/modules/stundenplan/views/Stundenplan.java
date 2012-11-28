package modules.stundenplan.views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import services.Bootstrap;
import java.awt.Color;
import javax.swing.JButton;

public class Stundenplan extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable StundenplanTable;
	public Stundenplan() {
		setAutoscrolls(true);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 51, 817, 234);
		add(scrollPane);
		
		StundenplanTable = new JTable();
		
		scrollPane.setViewportView(StundenplanTable);
		
	
		StundenplanTable.setBackground(Color.WHITE);
		StundenplanTable.setModel(Bootstrap.serviceManager.getDozStundenplanTableModel());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.setBounds(21, 17, 131, 23);
		add(btnPdfErzeugen);
		StundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		StundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(0).setMaxWidth(200);
		StundenplanTable.getColumnModel().getColumn(1).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMaxWidth(200);
		StundenplanTable.getColumnModel().getColumn(2).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(2).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(2).setMaxWidth(200);
		StundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMaxWidth(200);
		StundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMaxWidth(200);
		StundenplanTable.getColumnModel().getColumn(5).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(5).setMaxWidth(200);

	}

}
