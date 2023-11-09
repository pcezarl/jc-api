package br.com.pcl.jc.mapper;

import br.com.pcl.jc.dto.PersonDTO;
import br.com.pcl.jc.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends EntityMapper<PersonDTO, Person>{}
