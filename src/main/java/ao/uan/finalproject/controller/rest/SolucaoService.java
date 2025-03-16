package ao.uan.finalproject.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.repository.SolucaoRepository;
import jakarta.transaction.Transactional;

@Service
public class SolucaoService {

    private final SolucaoRepository solucaoRepository;

    @Autowired
    public SolucaoService(SolucaoRepository solucaoRepository) {
        this.solucaoRepository = solucaoRepository;
    }

    @Transactional
    public boolean saveAndUpdateSolucao(Solucao solucao) {
        // Salvar novamente a entidade após a manipulação
        return solucaoRepository.save(solucao) != null;
    }
}
