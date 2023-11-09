package br.com.pcl.jc.service.impl;

import br.com.pcl.jc.dto.AuthRequestDTO;
import br.com.pcl.jc.dto.DocumentDTO;
import br.com.pcl.jc.dto.filter.FilterPersonDTO;
import br.com.pcl.jc.model.Login;
import br.com.pcl.jc.model.Person;
import br.com.pcl.jc.repository.LoginRepository;
import br.com.pcl.jc.repository.PersonRepository;
import br.com.pcl.jc.service.LoginService;
import br.com.pcl.jc.service.PersonService;
import br.com.pcl.jc.service.dto.AuthResponseDTO;
import br.com.pcl.jc.util.CpfCnpjValidator;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired private LoginRepository loginRepository;

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO requestDTO) {

        Login login = loginRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword());
        if (login != null) {
            return AuthResponseDTO.builder().name(login.getName()).username(login.getUsername()).role(login.getRole()).build();
        }
    return new AuthResponseDTO();
    }
}
