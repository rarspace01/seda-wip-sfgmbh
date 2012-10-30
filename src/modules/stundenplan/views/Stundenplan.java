package modules.stundenplan.views;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class Stundenplan extends JPanel {

	private static final long serialVersionUID = 1L;

	public Stundenplan() {
		setLayout(new MigLayout("", "[][][][][][][][][][][][][][]", "[]"));
		
		JLabel lblThisIsStundenplan = new JLabel("This is the Stundenplan JPanel!");
		add(lblThisIsStundenplan, "cell 13 0");

	}

}
