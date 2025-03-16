package ao.uan.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;

public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {

    List<EntradaSaida> findByExercicio(Exercicio exercicio);
    
}
