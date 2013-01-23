package de.sfgmbh.comlayer.core.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import de.sfgmbh.applayer.core.controller.CtrlLiveTicker;
import de.sfgmbh.applayer.core.definitions.IntfCtrlLiveTicker;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * Panel for the live ticker
 * 
 * @author hannes
 *
 */
public class LiveTickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea txtHeader;
	private JTextPane txtTicker;
	private IntfCtrlLiveTicker ctrlLiverTicker = new CtrlLiveTicker();
	private List<IntfRoomAllocation> displayAllocations;
	private boolean isTooLong;
	private int cycles;
	
	/**
	 * Create the panel.
	 */
	public LiveTickerPanel() {
		setToolTipText("<html>Achtung, bald starten hier gesammelte Veranstaltungen</html>");
		setMaximumSize(new Dimension(140, 530));
		createContents();
		this.refresh();
		this.cycles = 0;
		ScrollTicker scrollTicker = new ScrollTicker();
		scrollTicker.execute();
	}
	
	/**
	 * Refresh the live ticker with current values
	 */
	public void refresh() {
		this.displayAllocations = this.ctrlLiverTicker.getTickerAllocations();
		
		// Set a fix top text message
		String textToSet = "<strong>Logindaten:</strong><br />" +
				"Doz // Doz <br />" +
				"Verw // Verw <br /><br/>";
		
		// Get the text for the room allocations
		String allocationText = this.getRoomAllocationText(this.displayAllocations);
		
		// Merge and set text
		textToSet = textToSet + allocationText;
		this.setTickerText(textToSet);
	}
	
	private void createContents() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
		setLayout(null);
		setBounds(0, 6, 140, 310);
		add(getTxtHeader());
		add(getTxtTicker());
	}
	/**
	 * 
	 * @return the txtHeader
	 */
	public JTextArea getTxtHeader() {
		if (txtHeader == null) {
			txtHeader = new JTextArea();
			txtHeader.setBorder(new LineBorder(SystemColor.activeCaption));
			txtHeader.setBackground(SystemColor.activeCaption);
			txtHeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtHeader.setEditable(false);
			txtHeader.setText(" Infos & nahe Termine:");
			txtHeader.setBounds(0, 0, 140, 23);
		}
		return txtHeader;
	}
	
	/**
	 * 
	 * @return the txtTicker
	 */
	public JTextPane getTxtTicker() {
		if (txtTicker == null) {
			txtTicker = new JTextPane();
			txtTicker.setDragEnabled(true);
			txtTicker.setOpaque(false);
			txtTicker.setEditable(false);
			txtTicker.setContentType("text/html");
			txtTicker.setBounds(5, 21, 130, 285);
			txtTicker.setText("<div style=\"font-family: Tahoma, Calibri, monospace; font-size: 11pt;\"></div>");
		}
		return txtTicker;
	}
	
	/**
	 * Set a ticker text which may be HTML-formated but doesn't need the initial style (font-family and size is already set)
	 * @param text - as text/html
	 */
	public void setTickerText(String text) {
		this.getTxtTicker().setText("<div style=\"font-family: Tahoma, Calibri, monospace; font-size: 11pt;\">" +
				text +
				"</div>");
		
		// Adjust the height
		Double heightDouble = this.getTxtTicker().getPreferredSize().getHeight();
		int height = heightDouble.intValue();
		int x = this.getTxtTicker().getBounds().x;
		int y = this.getTxtTicker().getBounds().y;
		int width = this.getTxtTicker().getBounds().width;
		this.getTxtTicker().setBounds(x, y, width, height);
		if (height > 325) {
			this.isTooLong = true;
		} else {
			this.isTooLong = false;
		}
	}
	
	private String getRoomAllocationText (List<IntfRoomAllocation> roomAllocations) {
		String returnString = "";
		
		// Build the string for all allocations
		for (IntfRoomAllocation ra : roomAllocations) {
			returnString = 
					returnString + ViewHelper.getTime(ra.getTime_()) + " Uhr<br />" +
					"Raum: " + ra.getRoom_().getRoomNumber_() + "<br />" +
					"<strong>" + ra.getCourse_().getCourseAcronym_() + "</strong> (" + ra.getCourse_().getCourseKind_() + ")<br /><br />";
 		}
		
		/*
		for (int i = 0; i < 10; i++) {
			returnString = returnString + "Test Test<br><strong>Test</strong><br><br>";
		}
		*/
		
		return returnString;
	}
	
	private void scroll() {
		// Get current size and scroll by one pixel if the text is too long
		if (this.isTooLong) {
			int x = this.getTxtTicker().getBounds().x;
			int y = this.getTxtTicker().getBounds().y;
			int width = this.getTxtTicker().getBounds().width;
			int height = this.getTxtTicker().getBounds().height;
			if (-y == height-30) {
				// Start from this low with a new scroll up
				y = 490;
			}
			this.getTxtTicker().setBounds(x, y-1, width, height);
		}
	}
	
	/**
	 * Swing worker to scroll in background
	 *
	 * @author hannes
	 */
	class ScrollTicker extends SwingWorker<Object, Object> {
		
		protected Object doInBackground() throws Exception {
			while (true) {
				try {
					Thread.sleep(110);
				} catch (Exception e) {
					e.printStackTrace();
				}
				LiveTickerPanel.this.scroll();
				// refresh the ticker every two minutes (1091 cycles as one cycle takes 110 ms)
				LiveTickerPanel.this.cycles++;
				if (LiveTickerPanel.this.cycles > 1091) {
					LiveTickerPanel.this.cycles = 0;
					LiveTickerPanel.this.refresh();
				}
			}
		}
	}
	
	public class JTextPaneAntiAliasing extends JTextPane {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics graphics) {
			Graphics2D graphics2D = (Graphics2D) graphics;
		    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		    super.paintComponent(graphics2D);
		}
	}
}
