package ao.uan.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import ao.uan.finalproject.domain.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @NativeQuery("SELECT * FROM public.avaliacao WHERE extract(hour from current_timestamp - data_da_avaliacao) > CAST(duracao_minutos/60 AS float)")
    List<Avaliacao> findPastAvaliacao();
    
}
