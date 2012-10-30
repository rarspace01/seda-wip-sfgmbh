package modules.dozenten.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.dozenten.controller.LVMaskeWindow;
import java.awt.Dimension;

public class DozentenLVMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DozentenLVMaskeWindow() {
		setBounds(100, 100, 662, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblHierSollteDie = new JLabel("Hier sollte die Maske zum hinzuf\u00FCgen bzw. bearbeiten bestimmte Lehrveranstaltungen sein!");
		lblHierSollteDie.setMinimumSize(new Dimension(437, 30));
		lblHierSollteDie.setMaximumSize(new Dimension(437, 30));
		contentPane.add(lblHierSollteDie, BorderLayout.CENTER);
		
		addWindowListener(new LVMaskeWindow());
	}

}
