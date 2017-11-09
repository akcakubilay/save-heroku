package com.heroku.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heroku.models.PdfTemplate;
import com.heroku.services.CreateTemplateService;
import com.heroku.services.PdfTemplateService;
import com.itextpdf.io.codec.Base64.InputStream;
import com.itextpdf.io.source.ByteArrayOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("template")
public class TemplateController {

	
	private static final String INPUT_ZIP_FILE = System.getProperty("user.home")+"\\test.zip";
    private static final String OUTPUT_FOLDER = System.getProperty("user.home")+"\\herokutest";
    
	@Autowired
	CreateTemplateService createTemplateService;
	
	@Autowired
	PdfTemplateService pdfTemplateService;

	@PostMapping(value = "/pdf/upload",consumes = {"multipart/form-data"})
	@ResponseStatus(HttpStatus.OK)
	public void uploadPDFTemplate(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		log.info("Request for upload pdf endpoint received.");
		if (file.isEmpty()) {
			log.error("File is not provided in request.");
		} 
		if (name.length() == 0) {
			log.error("File name is not provided in request.");
		} else {
			try {				
				pdfTemplateService.addPdfTemplate(PdfTemplate.builder().name(name).pdfTemplateData(file.getBytes()).build());
			} catch (IOException e) {
				log.error("Error while reading the file. " + e.getMessage());
			}
		}
	}
	
	@GetMapping("/pdftemplate/{templateId}")
	public ResponseEntity<byte[]> generatePDFTemplate(@PathVariable int templateId) throws IOException {
			
			ByteArrayOutputStream baos = createTemplateService.create(templateId);
			HttpHeaders responseHeaders  = new HttpHeaders();
			responseHeaders.set("charset", "utf-8");
		    responseHeaders.setContentType(MediaType.APPLICATION_PDF);
		    responseHeaders.setContentLength(baos.size());
		    responseHeaders.set("Content-Disposition", "attachment; filename=\"template" + templateId + "\".pdf");
		    return new ResponseEntity<byte[]>(baos.toByteArray(), responseHeaders, HttpStatus.OK);		    
	}
	
	@GetMapping("/")
	public void Unzipt() throws IOException {
		
		unZipIt(pdfTemplateService.getPdfTemplateById(1).get().getPdfTemplateData(),OUTPUT_FOLDER);
	}
	
	@RequestMapping(value="/getfile")
	public void getLogFile(HttpSession session,HttpServletResponse response) throws Exception {
	    try {
	        String filePathToBeServed = "asd";
	        File fileToDownload = new File(filePathToBeServed);
	        FileInputStream inputStream = new FileInputStream(OUTPUT_FOLDER+"\\test.txt");
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename=test.txt"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	        e.printStackTrace();
	    }

	}
	
	
	 /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public static void unZipIt(byte[] zipFile, String outputFolder){

     byte[] buffer = new byte[1024];

     try{

    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	ByteArrayInputStream bis = new ByteArrayInputStream(zipFile);
    	
    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(bis);
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();

    	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);

           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	System.out.println("Done");

    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
	
	
	/*
	@GetMapping("/byte")
	public String generatePDFTemplateByteArray() throws IOException {
			
			return CreateTemplate.create().toString();   
	}
	*/
}
