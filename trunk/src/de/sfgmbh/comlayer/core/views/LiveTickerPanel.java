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
import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * Panel for the live ticker
 * 
 * @author hannes
 *
 */
public class LiveTickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea txtHeader_;
	private JTextPane txtTicker_;
	private IntfCtrlLiveTicker ctrlLiverTicker_ = new CtrlLiveTicker();
	private List<IntfRoomAllocation> displayAllocations_;
	private boolean isTooLong_;
	private ScrollTicker scrollTicker_ = new ScrollTicker();
	
	/**
	 * Create the panel.
	 */
	public LiveTickerPanel() {
		setToolTipText("<html>Achtung, bald starten hier gesammelte Veranstaltungen</html>");
		setMaximumSize(new Dimension(140, 530));
		createContents();
		this.refresh();
		scrollTicker_.execute();
	}
	
	/**
	 * Refresh the live ticker with current values
	 */
	public synchronized void refresh() {
		try {
			this.displayAllocations_ = this.ctrlLiverTicker_.getTickerAllocations();
		} catch (Exception e) {
			// This would be a good place for logging - but normally nothing should happen here
		}
		
		try {
			// Set a fix top text message
			String textToSet = "<strong>Logindaten:</strong><br />" +
					"Doz // Doz <br />" +
					"Verw // Verw <br /><br/>";
			
			// Get the text for the room allocations
			String allocationText = this.getRoomAllocationText(this.displayAllocations_);
			
			// Merge and set text
			textToSet = textToSet + allocationText;
			this.setTickerText(textToSet);
		} catch (Exception e) {
			// This would be a good place for logging - but normally nothing should happen here
		}
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
		if (txtHeader_ == null) {
			txtHeader_ = new JTextArea();
			txtHeader_.setBorder(new LineBorder(SystemColor.activeCaption));
			txtHeader_.setBackground(SystemColor.activeCaption);
			txtHeader_.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtHeader_.setEditable(false);
			txtHeader_.setText(" Infos & nahe Termine:");
			txtHeader_.setBounds(0, 0, 140, 23);
		}
		return txtHeader_;
	}
	
	/**
	 * 
	 * @return the txtTicker
	 */
	public JTextPane getTxtTicker() {
		if (txtTicker_ == null) {
			txtTicker_ = new JTextPane();
			txtTicker_.setDragEnabled(true);
			txtTicker_.setOpaque(false);
			txtTicker_.setEditable(false);
			txtTicker_.setContentType("text/html");
			txtTicker_.setBounds(5, 21, 130, 285);
			txtTicker_.setText("<div style=\"font-family: Tahoma, Calibri, monospace; font-size: 11pt;\"></div>");
		}
		return txtTicker_;
	}
	
	/**
	 * Set a ticker text which may be HTML-formated but doesn't need the initial style (font-family and size is already set)
	 * @param text - as text/html
	 */
	public synchronized void setTickerText(String text) {
		try {
			this.remove(getTxtTicker());
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
				this.isTooLong_ = true;
			} else {
				this.isTooLong_ = false;
			}
			this.add(getTxtTicker());
		} catch (Exception e) {
			// This would be a good place for logging - but normally nothing should happen here
		}
	}
	
	private String getRoomAllocationText (List<IntfRoomAllocation> roomAllocations) {
		String returnString = "";
		
		// Build the string for all allocations
		for (IntfRoomAllocation roomAllocation : roomAllocations) {
			returnString = 
					returnString + ViewHelper.getTime(roomAllocation.getTime_()) + " Uhr<br />" +
					"Raum: " + roomAllocation.getRoom_().getRoomNumber_() + "<br />" +
					"<strong>" + roomAllocation.getCourse_().getCourseAcronym_() + "</strong> (" + roomAllocation.getCourse_().getCourseKind_() + ")<br /><br />";
 		}
		
		return returnString;
	}
	
	private synchronized void scroll() {
		// Get current size and scroll by one pixel if the text is too long
		if (this.isTooLong_) {
			try {
				int x = this.getTxtTicker().getBounds().x;
				int y = this.getTxtTicker().getBounds().y;
				int width = this.getTxtTicker().getBounds().width;
				int height = this.getTxtTicker().getBounds().height;
				if (-y == height-30) {
					// Start from this low with a new scroll up
					y = 490;
				}
				this.getTxtTicker().setBounds(x, y-1, width, height);
			} catch (Exception e) {
				// This would be a good place for logging - but normally nothing should happen here
			}
		}
	}
	
	/**
	 * Swing worker to scroll in background
	 *
	 * @author hannes
	 */
	class ScrollTicker extends SwingWorker<Object, Object> {
		
		protected Object doInBackground() throws Exception {
			// Wait a short time (3s) after the live ticker start before the scrolling starts
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				
			}

			int cycles = 0;
			
			// Start the loop
			while (true) {
				try {
					Thread.sleep(110);
				} catch (Exception e) {
					
				}
				// 545.5 cycles are 60 seconds (with 110ms per cycle)
				if (cycles < 200) {
					LiveTickerPanel.this.scroll();
					cycles++;
				} else {
					LiveTickerPanel.this.refresh();
					cycles = 0;
				}
					
			}
		}
	}
	
	/**
	 * Anti-aliasing for the text
	 * Makes it a very little bit smoother while scrolling up.
	 *
	 * @author hannes
	 */
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
