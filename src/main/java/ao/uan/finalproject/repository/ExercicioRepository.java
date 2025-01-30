package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.Exercicio;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    
}
