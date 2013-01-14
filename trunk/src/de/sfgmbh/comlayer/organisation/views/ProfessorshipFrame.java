package de.sfgmbh.comlayer.organisation.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.sfgmbh.comlayer.organisation.controller.ProfessorshipFrameBtns;
import de.sfgmbh.comlayer.organisation.controller.ProfessorshipFrameWin;

public class ProfessorshipFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLehrstuhlname;
	private JTextField txtFak;
	private JTextField txtInhaber;
	private JTextField txtGebaude;
	private JTextField txtStock;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public ProfessorshipFrame() {
		initialize();
	}
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorshipFrame.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("Lehrst\u00FChle hinzuf\u00FCgen");
		setBounds(100, 100, 282, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNutzerkennung = new JLabel("Lehrstuhlname:");
		lblNutzerkennung.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerkennung.setBounds(10, 125, 120, 14);
		contentPane.add(lblNutzerkennung);
		
		JLabel lblEmail = new JLabel("Inhaber:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(10, 156, 120, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPasswort = new JLabel("Fakult\u00E4t:");
		lblPasswort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPasswort.setBounds(10, 183, 120, 14);
		contentPane.add(lblPasswort);
		
		JLabel lblNutzerklasse = new JLabel("(Haupt-)Geb\u00E4ude:");
		lblNutzerklasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNutzerklasse.setBounds(10, 210, 120, 14);
		contentPane.add(lblNutzerklasse);
		
		JLabel lblLehrstuhl = new JLabel("(Haupt-)Stock:");
		lblLehrstuhl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLehrstuhl.setBounds(10, 241, 120, 14);
		contentPane.add(lblLehrstuhl);
		
		txtLehrstuhlname = new JTextField();
		txtLehrstuhlname.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLehrstuhlname.setBounds(113, 123, 116, 20);
		contentPane.add(txtLehrstuhlname);
		txtLehrstuhlname.setColumns(10);
		
		txtFak = new JTextField();
		txtFak.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFak.setColumns(10);
		txtFak.setBounds(113, 181, 116, 20);
		contentPane.add(txtFak);
		
		txtInhaber = new JTextField();
		txtInhaber.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtInhaber.setColumns(10);
		txtInhaber.setBounds(113, 154, 116, 20);
		contentPane.add(txtInhaber);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(139, 293, 90, 28);
		btnSpeichern.addActionListener(new ProfessorshipFrameBtns("save"));
		contentPane.add(btnSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(38, 293, 90, 28);
		btnAbbrechen.addActionListener(new ProfessorshipFrameBtns("cancle"));
		contentPane.add(btnAbbrechen);
		
		txtGebaude = new JTextField();
		txtGebaude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtGebaude.setColumns(10);
		txtGebaude.setBounds(113, 208, 116, 20);
		contentPane.add(txtGebaude);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtStock.setColumns(10);
		txtStock.setBounds(113, 239, 116, 20);
		contentPane.add(txtStock);
		
		addWindowListener(new ProfessorshipFrameWin());
		
		JLabel lblmsg = new JLabel("<html><b>Fehlermeldung:</b><br>Der Lehrstuhl konnte nicht hinzugef\u00FCgt werden:<br> Sie haben keinen Lehrstuhl selektiert.<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie zur Eingabemaske f\u00FCr Lehrveranstaltungen.</html>");
		lblmsg.setBounds(10, 11, 230, 85);
		contentPane.add(lblmsg);
	}
}
