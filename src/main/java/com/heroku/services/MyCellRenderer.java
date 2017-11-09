package com.heroku.services;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;

public class MyCellRenderer extends CellRenderer {

	protected String fieldName;

	public MyCellRenderer(Cell modelElement, String fieldName) {
		super(modelElement);
		this.fieldName = fieldName;
	}

	@Override
	public void draw(DrawContext drawContext) {
		super.draw(drawContext);
		PdfTextFormField field =
				PdfFormField.createText(drawContext.getDocument(), getOccupiedAreaBBox(), fieldName, "");
		PdfAcroForm form = PdfAcroForm.getAcroForm(drawContext.getDocument(), true);
		form.addField(field);
	}
}