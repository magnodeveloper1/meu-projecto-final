package ao.uan.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.uan.finalproject.domain.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
}
