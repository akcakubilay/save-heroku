package com.heroku.templates;

import java.io.IOException;

import com.heroku.services.MyCellRenderer;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class Template1 {
	public static ByteArrayOutputStream generateTemplate() throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
		Document doc = new Document(pdfDoc);

		Paragraph header = new Paragraph("TEMPLATE 1")
				.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA))
				.setFontSize(12)
				.setBold();
				
		
		float x = pdfDoc.getDefaultPageSize().getWidth() /2 ;
		float y = pdfDoc.getDefaultPageSize().getTop() - 20 ;

		doc.showTextAligned(header, x, y, TextAlignment.CENTER);

		@SuppressWarnings("deprecation")
		Table table = new Table(2);
		Cell cell;
		cell = new Cell().add("Customer Name:");
		table.addCell(cell);
		cell = new Cell();
		cell.setNextRenderer(new MyCellRenderer(cell, "CustomerName"));
		table.addCell(cell);
		cell = new Cell().add("Contract Start Date");
		table.addCell(cell);
		cell = new Cell();
		cell.setNextRenderer(new MyCellRenderer(cell, "ContractStartDate"));
		table.addCell(cell);
		doc.add(table);

		doc.close();
		
		return baos;
	}
}
