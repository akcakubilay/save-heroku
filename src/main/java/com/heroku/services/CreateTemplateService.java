package com.heroku.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.exceptions.TemplateNotFound;
import com.heroku.models.PdfTemplate;
import com.heroku.templates.Template1;
import com.heroku.templates.Template2;
import com.itextpdf.io.source.ByteArrayOutputStream;

@Service
public class CreateTemplateService {

	@Autowired
	PdfTemplateService pdfTemplateService;
	
	public ByteArrayOutputStream create(int templateId) throws IOException {
		
		PdfTemplate pdfTemplate = new PdfTemplate();
		ByteArrayOutputStream baos = null;
		switch (templateId) {
			case 1:
				baos = Template1.generateTemplate();
				break;
			case 2:
				baos = Template2.generateTemplate();
				break;
			default:
				break;
		}
		
		if(baos==null) {
			throw new TemplateNotFound();
		}
		else {
			pdfTemplate.setPdfTemplateData(baos.toByteArray());			
			pdfTemplateService.addPdfTemplate(pdfTemplate);
			baos.close();
			return baos;
		}
	}
}