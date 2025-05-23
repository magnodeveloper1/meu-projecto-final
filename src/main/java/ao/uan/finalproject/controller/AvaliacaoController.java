package ao.uan.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ao.uan.finalproject.domain.Avaliacao;
import ao.uan.finalproject.repository.AvaliacaoRepository;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    
    @GetMapping("/")
    public String screenAvaliacao() {
        List<Avaliacao> listAvaliacao = new ArrayList<>();
        return "minhaavaliacao.html";
    }
}
