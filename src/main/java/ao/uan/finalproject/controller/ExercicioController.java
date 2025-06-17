package ao.uan.finalproject.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.domain.Utilizador;
import ao.uan.finalproject.repository.EntradaSaidaRepository;
import ao.uan.finalproject.repository.ExercicioRepository;
import ao.uan.finalproject.repository.SolucaoRepository;
import ao.uan.finalproject.repository.UtilizadorRepository;

@Controller
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Autowired
    private SolucaoRepository solucaoRepository;

    @Autowired
    private UtilizadorRepository userRepo;

    @GetMapping("/exercicio/listar.html")
    public String screenList(Model model) {

        List<Exercicio> exercicios = exercicioRepository.findAll();
        model.addAttribute("listExercicio", exercicios);

        return "exercicio/list.html";
    }

    @GetMapping("exercicio/criar")
    public String screenCriar(Model model) {
        Exercicio exercicio = new Exercicio();

        model.addAttribute("exercicio", exercicio);
        return "exercicio/criar.html";
    }

    @GetMapping("exercicio/view/{id}")
    public String screenVerExercicio(Model model,
            @PathVariable(name = "id") Long idExercicio
    // @RequestParam(name = "avaliacao") String idAvaliacao
    ) {

        if (idExercicio != null) {

            Optional<Exercicio> exercicio = exercicioRepository.findById(idExercicio);

            if (!exercicio.isEmpty()) {
                Exercicio exerc = exercicio.get();
                exerc.setEntradaSaida(entradaSaidaRepository.findByExercicio(exerc));
                model.addAttribute("exercicio", exerc);
                model.addAttribute("solucao", new Solucao());
                return "exercicio/visualizar.html";
            }
        }

        List<Exercicio> exercicios = exercicioRepository.findAll();
        model.addAttribute("listExercicio", exercicios);
        return "exercicio/list.html";
    }

    @PostMapping("exercicio/criar/add")
    public String actionCriar(
            @ModelAttribute Exercicio exercicio,
            @RequestParam("txtInputOutput") MultipartFile file) {

        if (exercicio != null) {

            exercicio.setAtivo(true);
            exercicioRepository.save(exercicio);

            if (Objects.isNull(exercicio.getId())) {
                System.out.println("N~ao Guardou Info.");
            } else {

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        EntradaSaida inout = new EntradaSaida();

                        if(line.trim().isEmpty()) {
                            continue;
                        }
                        
                        String[] vls = line.split(";");
                        inout.setExercicio(exercicio);
                        inout.setEntrada(vls[0].trim());
                        inout.setSaida(vls[1]);
                        entradaSaidaRepository.save(inout);
                    }
                } catch (IOException e) {
                    System.out.println("Impossível ler arquivo ... " + e.getMessage());
                }
            }

        } else {
            System.out.println("Vazio!!");
        }

        return "exercicio/criar.html";
    }

    @PostMapping("exercicio/solucao/add")
    public String actionCriarSolucao(
            @ModelAttribute Solucao solucao, Model model,
            @RequestParam(name = "idExercicio") Long idExercicio) {

        if (solucao != null && idExercicio != null) {
            solucao.setResposta("Na Fila");
            Exercicio ex = exercicioRepository.findById(idExercicio).get();
            solucao.setDataSubmissao(new Date());
            solucao.setExercicio(ex);
            solucaoRepository.save(solucao);
        }

        List<Exercicio> exercicios = exercicioRepository.findAll();
        model.addAttribute("listExercicio", exercicios);

        return "exercicio/list.html";
    }
}
