package modules.dozenten.views;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class DozentenLehrstuhlplanTab extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public DozentenLehrstuhlplanTab() {
		setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel lblThisIsThe = new JLabel("This is the Dozenten Lehrstuhlplan Tab!");
		add(lblThisIsThe, "cell 0 0");

	}

}
