package ao.uan.finalproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ao.uan.finalproject.domain.Avaliacao;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.repository.AvaliacaoRepository;
import ao.uan.finalproject.repository.ExercicioRepository;

@Controller
@RequestMapping
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;
    
    @GetMapping("/avaliacao/lista.html")
    public String screenAvaliacao(Model model) {
        List<Avaliacao> listAvaliacao = avaliacaoRepository.findAll();
        model.addAttribute("listAvaliacao", listAvaliacao);
        return "avaliacao/minhaavaliacao.html";
    }

     @GetMapping("/avaliacao/past.html")
     public String screenListPastAvalicao(Model model) {
        List<Avaliacao> listAvaliacao = avaliacaoRepository.findPastAvaliacao();
        model.addAttribute("listAvaliacao", listAvaliacao);
        return "avaliacao/past.html";
     }
    
    @GetMapping("/avaliacao/criar")
    public String screenCriarAvaliacao(Model model) {
        // List<Avaliacao> listAvaliacao = new ArrayList<>();
        List<Exercicio> listExercicio = exercicioRepository.findAll();
        Avaliacao avaliacao = new Avaliacao();
        model.addAttribute("avaliacao", avaliacao);
        model.addAttribute("listExercicio", listExercicio);
        return "avaliacao/criar.html";
    }

    @PostMapping("/avaliacao/criar/add")
    public String actionCriarAvaliacao(
        @ModelAttribute Avaliacao avaliacao,
        // @RequestParam(name = "exercicio") String[] exercicios
        @RequestParam(name = "data_da_avaliacao") String dataDaAvaliacao
    ) {
        avaliacao.setDataDeCriacao(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt = dateFormat.parse(dataDaAvaliacao.replace("T", " "));
            avaliacao.setDataDaAvaliacao(dt);
        } catch (ParseException e) {}
        avaliacaoRepository.save(avaliacao);
        return "index.html";
    }
}
