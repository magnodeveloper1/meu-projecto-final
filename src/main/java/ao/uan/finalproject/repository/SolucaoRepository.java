package ao.uan.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import ao.uan.finalproject.domain.Solucao;

public interface SolucaoRepository extends JpaRepository<Solucao, Long> {
    
    @NativeQuery("SELECT * FROM solucao WHERE resposta = 'Na Fila' limit 1")
    Optional<Solucao> solucaoNaoTratada();

    @NativeQuery("SELECT * FROM solucao ORDER BY id DESC")
    List<Solucao> findAllOrderDesc();
}
