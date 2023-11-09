package br.com.pcl.jc.service.impl;

import br.com.pcl.jc.dto.AuthRequestDTO;
import br.com.pcl.jc.dto.DocumentDTO;
import br.com.pcl.jc.dto.filter.FilterPersonDTO;
import br.com.pcl.jc.model.Person;
import br.com.pcl.jc.repository.PersonRepository;
import br.com.pcl.jc.service.PersonService;
import br.com.pcl.jc.util.CpfCnpjValidator;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired private PersonRepository personRepository;

    private CpfCnpjValidator validator;

    int countCompradorValido = 0;
    int countCompradorInvalido = 0;
    int countParticipanteValido = 0;
    int countParticipanteinValido = 0;

    public DocumentDTO validateDocument(){
        List<Person> persons = personRepository.findAll();


        CpfCnpjValidator cpfCnpjValidator =  new CpfCnpjValidator();


        persons.forEach(item -> {

            item.setDocumentNumber(item.getDocumentNumber().replaceAll("[^0-9]",""));
            item.setBuyerDocumentNumber(item.getBuyerDocumentNumber().replaceAll("[^0-9]",""));

            if(item.getDocumentNumber() != null) {
//              CPF PARTICIPANTE
                if(item.getDocumentNumber().length() == 11) {
                    if (!cpfCnpjValidator.isCpf(item.getDocumentNumber())) {
                        log.info("ID: {} | Nome: {} | Email: {} | CPF Participante Invalido: {}", item.getId(), item.getName(), item.getEmail(), item.getDocumentNumber());
                        countParticipanteinValido++;
                    } else {
                        countParticipanteValido++;
                    }
//              CNPJ PARTICIPANTE
                } else if(item.getDocumentNumber().length() > 11) {
                    if (!cpfCnpjValidator.isCnpj(item.getDocumentNumber())) {
                        log.info("ID: {} | Nome: {} | Email: {} | CNPJ Participante Invalido: {}", item.getId(), item.getName(), item.getEmail(), item.getDocumentNumber());
                        countParticipanteinValido++;
                    } else {
                        countParticipanteValido++;
                    }
                }
            }

            if(item.getBuyerDocumentNumber() != null) {
//              CPF COMPRADOR
                if(item.getBuyerDocumentNumber().length() == 11) {
                    if (!cpfCnpjValidator.isCpf(item.getBuyerDocumentNumber())) {
                        log.info("ID: {} | Nome: {} | Email: {} | CPF Comprador Invalido: {}", item.getId(), item.getBuyerName(), item.getBuyerEmail(), item.getBuyerDocumentNumber());
                        countCompradorInvalido++;
                    } else {
                        countCompradorValido++;
                    }
//              CNPJ COMPRADOR
                } else if(item.getBuyerDocumentNumber().length() > 11) {
                    if (!cpfCnpjValidator.isCnpj(item.getBuyerDocumentNumber())) {
                        log.info("ID: {} | Nome Comprador: {} | Email Comprador: {} | CPF Comprador Invalido: {}", item.getId(), item.getBuyerName(), item.getBuyerEmail(), item.getBuyerDocumentNumber());
                        countCompradorInvalido++;
                    } else {
                        countCompradorValido++;
                    }
                }
            }
        });
        log.info("Compradores Validos: {}", countCompradorValido);
        log.info("Compradores Invalidos: {}", countCompradorInvalido);
        log.info("Participantes Validos: {}", countParticipanteValido);
        log.info("Participantes Invalidos: {}", countParticipanteinValido);

        return null;
    }
//
//    public List<PersonDTO> getPersons(){
//        return personMapper.toDto(
//                personRepository.findAll());
//
//    }

    @Override
    public List<Person> getPersons(FilterPersonDTO filter){

        List<Person> list =
            personRepository.findAll(
                (Specification<Person>)
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();

                    if(filter.getDocumentNumber().isPresent()) {
                        predicates.add(
                                criteriaBuilder.equal(root.get("document_number"), filter.getDocumentNumber().get())
                        );
                    }
                    if(filter.getName().isPresent()) {
                        predicates.add(
                                criteriaBuilder.like(root.get("name"), "%"+filter.getName().get()+"%")
                        );
                    }
                    if(filter.getEmail().isPresent()) {
                        predicates.add(
                                criteriaBuilder.like(root.get("email"), "%"+filter.getEmail().get()+"%")
                        );
                    }
                    if(filter.getETicket().isPresent()) {
                        predicates.add(
                                criteriaBuilder.like(root.get("eTicket"), "%"+ filter.getETicket().get() + "%")
                        );
                    }

                    query.orderBy(criteriaBuilder.desc(root.get("id")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
            );
        return list;
    }

    public Person updateCheckin(Long personId){
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            person.get().setChecked(true);
            person.get().setCheckedBy("Administrador Paulo");
            person.get().setCheckedAt(LocalDateTime.now());
            return personRepository.save(person.get());
        }
        return null;
    }

}
