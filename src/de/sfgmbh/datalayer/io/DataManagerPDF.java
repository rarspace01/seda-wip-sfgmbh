package de.sfgmbh.datalayer.io;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JComponent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * class for PDF management
 * @author denis
 *
 */
public class DataManagerPDF implements IntfDataManagerPDF {

	private String filename_="";
	private PdfWriter writer_ = null;
	private Document document_ = null;
	
	public DataManagerPDF(String filename) {
		
		filename_=filename;
		
		document_ = new Document(PageSize.A4.rotate());
		try {
			writer_ = PdfWriter.getInstance(document_,
					new FileOutputStream(filename_));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document_.open();
		
		//adding meta data
		document_.addAuthor("Univis 2.0");
		document_.addTitle("Univis PDF export");
		document_.addSubject("autogenerated PDF from Univis 2.0");
		document_.addCreator("Univis 2.0 - iText lib 5.3.5");
		
		//document.close();
		
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.io.IntfDataManagerPDF#addContent(java.lang.String, javax.swing.JComponent)
	 */
	@Override
	public void addContent(String paragraphtitle, JComponent component){
		//document.open();
		
		
		//document.setPageSize(new Rectangle(urx, ury, rotation));
		writer_.getPageNumber();
		if(document_.getPageNumber()>0){
			document_.newPage();
		}
		
		// Paragraph mit Schriftdefinition, kann auch global definiert sein
		Paragraph p = new Paragraph(paragraphtitle, new Font(
				Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));

		try {
			document_.add(p);
			
			// get the buffered image, Graphics2D doesnt support absolute positioning
			BufferedImage im = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB);
			component.paint(im.getGraphics());
			// (PDF)Image != (Java)Image - no type conversion supported 
			java.awt.Image image = im.getScaledInstance(component.getWidth(), component.getHeight(), java.awt.Image.SCALE_SMOOTH);
			
			Image imagepdf = Image.getInstance(image, null);
			// set absolute position - we have no other option?
			imagepdf.setAbsolutePosition(20f, (500f-((float)component.getHeight())));
			
			document_.add(imagepdf);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
				
		//g = new PdfGraphics2D(contentByte, component.getWidth(), component.getHeight()
//		component.printAll(g);
//		g.dispose();
		
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.io.IntfDataManagerPDF#close()
	 */
	@Override
	public void close(){
		document_.close();
	}
	
	
}
