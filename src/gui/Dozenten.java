package gui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class Dozenten extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Dozenten() {
		setLayout(new MigLayout("", "[][][][][][][][][][][][][][]", "[]"));
		
		JLabel lblThisIsThe = new JLabel("This is the Dozenten JPanel!");
		add(lblThisIsThe, "cell 13 0");

	}

}
