package gui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class StudProfil extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StudProfil() {
		setLayout(new MigLayout("", "[][][][][][][][][][][][][][]", "[]"));
		
		JLabel lblThisIsStundenplan = new JLabel("This is the Studi Profil JPanel!");
		add(lblThisIsStundenplan, "cell 13 0");

	}

}
