package br.com.pcl.jc.controller;

import br.com.pcl.jc.dto.AuthRequestDTO;
import br.com.pcl.jc.dto.DocumentDTO;
import br.com.pcl.jc.dto.PersonDTO;
import br.com.pcl.jc.dto.filter.FilterPersonDTO;
import br.com.pcl.jc.model.Person;
import br.com.pcl.jc.service.LoginService;
import br.com.pcl.jc.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin("http://localhost:8080/")
public class PersonController {


    @Autowired private PersonService personService;
    @Autowired private LoginService loginService;

    @PatchMapping("/persons/{person_id}")
    @ResponseBody
    public ResponseEntity<Person> updateCheckin(@PathVariable(value="person_id") Long personId) {
        return new ResponseEntity<>(personService.updateCheckin(personId), HttpStatus.OK);
    }


    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> getPersons(FilterPersonDTO personDTO) {
        if(personDTO != null) {
            return new ResponseEntity<>(personService.getPersons(personDTO), HttpStatus.OK);
        }
        return null;
//        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }
    @GetMapping(path = "/validate")
    public ResponseEntity<DocumentDTO> getDocumentList() {
        return new ResponseEntity<>(personService.validateDocument(), HttpStatus.OK);
    }


}
