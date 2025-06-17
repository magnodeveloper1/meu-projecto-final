package ao.uan.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    List<Utilizador> findByCodeName(String codeName);

    Optional<Utilizador> findByCodeNameAndPassword(String codeName, String password);
}
