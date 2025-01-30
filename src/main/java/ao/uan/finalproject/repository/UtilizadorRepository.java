package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ao.uan.finalproject.domain.Utilizador;
import ao.uan.finalproject.dto.projection.noPassword;
import java.util.List;


@RepositoryRestResource(
    collectionResourceRel = "utilizador", 
    path = "utilizador", 
    excerptProjection = noPassword.class)
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    List<Utilizador> findByCodeName(String codeName);
}
