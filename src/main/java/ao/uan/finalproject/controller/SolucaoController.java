package ao.uan.finalproject.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.repository.SolucaoRepository;

@Controller
public class SolucaoController {

    @Autowired
    private SolucaoRepository solucaoRepository;

    @GetMapping("/solucao/list.html")
    public String index(Model model) {
        List<Solucao> listSolucao = solucaoRepository.findAllOrderDesc();
        model.addAttribute("listSolucao", listSolucao);
        return "solucao/list.html";
    }

    @GetMapping("/solucao/{code}")
    public String viewSolucao(
        @PathVariable(name = "code") Long code, Model model) {
            if(Objects.isNull(code)) {
                // Se não existir informações
            }

            Optional<Solucao> solucao = solucaoRepository.findById(code);

            if(!solucao.isPresent()){
                // se não existir solucao com tal Id
            }

            model.addAttribute("solucao", solucao.get());

        return "solucao/visualizar.html";
    }

    // Solução de todos, tempo real (com hanking por pessoa)
}
