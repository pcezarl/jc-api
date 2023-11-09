package br.com.pcl.jc.service;

import br.com.pcl.jc.dto.AuthRequestDTO;
import br.com.pcl.jc.dto.DocumentDTO;
import br.com.pcl.jc.dto.filter.FilterPersonDTO;
import br.com.pcl.jc.model.Person;
import br.com.pcl.jc.service.dto.AuthResponseDTO;

import java.util.List;

public interface LoginService {
    AuthResponseDTO authenticate(AuthRequestDTO requestDTO);
}
