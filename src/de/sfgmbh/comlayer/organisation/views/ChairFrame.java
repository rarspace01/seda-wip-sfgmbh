package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.comlayer.organisation.controller.ChairFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.ChairFrameWin;

/**
 * dialog/Frame to create chairs
 * 
 * @author anna
 *
 */
public class ChairFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtChairname;
	private JTextField txtFaculty;
	private JTextField txtChairowner;
	private JTextField txtBuilding;
	private JTextField txtLevel;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ChairFrame() {
		initialize();
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChairFrame.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Lehrst\u00FChle hinzuf\u00FCgen");
		setBounds(100, 100, 282, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChairname = new JLabel("Lehrstuhlname:");
		lblChairname.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblChairname.setBounds(10, 125, 120, 14);
		contentPane.add(lblChairname);
		
		JLabel lblChairowner = new JLabel("Inhaber:");
		lblChairowner.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblChairowner.setBounds(10, 156, 120, 14);
		contentPane.add(lblChairowner);
		
		JLabel lblFaculty = new JLabel("Fakult\u00E4t:");
		lblFaculty.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblFaculty.setBounds(10, 183, 120, 14);
		contentPane.add(lblFaculty);
		
		JLabel lblBuilding = new JLabel("(Haupt-)Geb\u00E4ude:");
		lblBuilding.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblBuilding.setBounds(10, 210, 120, 14);
		contentPane.add(lblBuilding);
		
		JLabel lblLevel = new JLabel("(Haupt-)Stock:");
		lblLevel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLevel.setBounds(10, 241, 120, 14);
		contentPane.add(lblLevel);
		
		txtChairname = new JTextField();
		txtChairname.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtChairname.setBounds(113, 123, 116, 20);
		contentPane.add(txtChairname);
		txtChairname.setColumns(10);
		
		txtFaculty = new JTextField();
		txtFaculty.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFaculty.setColumns(10);
		txtFaculty.setBounds(113, 181, 116, 20);
		contentPane.add(txtFaculty);
		
		txtChairowner = new JTextField();
		txtChairowner.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtChairowner.setColumns(10);
		txtChairowner.setBounds(113, 154, 116, 20);
		contentPane.add(txtChairowner);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.setBounds(139, 293, 90, 28);
		btnSave.addActionListener(new ChairFrameBtns("save"));
		contentPane.add(btnSave);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(38, 293, 90, 28);
		btnAbbrechen.addActionListener(new ChairFrameBtns("cancle"));
		contentPane.add(btnAbbrechen);
		
		txtBuilding = new JTextField();
		txtBuilding.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBuilding.setColumns(10);
		txtBuilding.setBounds(113, 208, 116, 20);
		contentPane.add(txtBuilding);
		
		txtLevel = new JTextField();
		txtLevel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLevel.setColumns(10);
		txtLevel.setBounds(113, 239, 116, 20);
		contentPane.add(txtLevel);
		
		addWindowListener(new ChairFrameWin());
		
		JLabel lblMsg = new JLabel("<html><b>Fehlermeldung:</b><br>Der Lehrstuhl konnte nicht hinzugef\u00FCgt werden:<br> Sie haben keinen Lehrstuhl selektiert.<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie zur Eingabemaske f\u00FCr Lehrveranstaltungen.</html>");
		lblMsg.setBounds(10, 11, 230, 85);
		contentPane.add(lblMsg);
	}
}
