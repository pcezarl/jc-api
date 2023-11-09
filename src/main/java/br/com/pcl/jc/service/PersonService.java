package br.com.pcl.jc.service;

import br.com.pcl.jc.dto.AuthRequestDTO;
import br.com.pcl.jc.dto.DocumentDTO;
import br.com.pcl.jc.dto.PersonDTO;
import br.com.pcl.jc.dto.filter.FilterPersonDTO;
import br.com.pcl.jc.model.Person;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    DocumentDTO validateDocument();

//    List<PersonDTO> getPersons();

    List<Person> getPersons(FilterPersonDTO dto);

    Person updateCheckin(Long personId);

}
