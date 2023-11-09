package br.com.pcl.jc.repository;

import br.com.pcl.jc.model.Login;
import br.com.pcl.jc.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Person> {
    Login findByUsernameAndPassword(String username, String password);
}
