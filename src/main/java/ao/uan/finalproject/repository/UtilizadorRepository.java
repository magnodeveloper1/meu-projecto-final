package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ao.uan.finalproject.domain.Utilizador;

@RepositoryRestResource(collectionResourceRel = "utilizador", path = "utilizador")
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

}
