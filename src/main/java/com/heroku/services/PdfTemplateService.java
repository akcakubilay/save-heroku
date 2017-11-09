package com.heroku.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.models.PdfTemplate;
import com.heroku.repositories.PdfTemplateRepository;

/**
 * Service class for PdfForm entity. Responsible for business logic of PdfForm related operations.
 * Injects PdfForm repository to perform necessary database operations via it.
 */
@Service
public class PdfTemplateService {
	
	@Autowired
	PdfTemplateRepository pdfTemplateRepository;
	
	public void addPdfTemplate(PdfTemplate pdfTemplate) {
		pdfTemplateRepository.save(pdfTemplate);
	}
	
	public Optional<PdfTemplate> getPdfTemplateById(int id) {
		return pdfTemplateRepository.findById(id);
	}

}
