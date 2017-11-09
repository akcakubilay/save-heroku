package com.heroku.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.heroku.models.PdfTemplate;


/**
 * Repository class for PdfTemplate entity which extends CrudRepository interface from Spring Data JPA. Responsible for database read/write operations. 
 * The implementation needed for basic CRUD operations provided by CrudRepository in runtime.
 */
@Repository
public interface PdfTemplateRepository extends CrudRepository<PdfTemplate, Integer> {

}
