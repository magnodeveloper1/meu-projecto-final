package ao.uan.finalproject.controller;

import java.util.Base64;
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

import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.repository.EntradaSaidaRepository;
import ao.uan.finalproject.repository.ExercicioRepository;

@Controller
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;
    
    @GetMapping("/exercicio")
    public String screenList(Model model) {

        List<Exercicio> exercicios = exercicioRepository.findAll();
        model.addAttribute("listExercicio", exercicios);

        return "exercicio/list.html";
    }

    @GetMapping("exercicio/criar")
    public String screenCriar(Model model) {
        EntradaSaida entradaSaida = new EntradaSaida();

        model.addAttribute("entradaSaida", entradaSaida);
        return "exercicio/criar.html";
    }

    @GetMapping("exercicio/view/{id}")
    public String screenVerExercicio(Model model, @PathVariable(name = "id") Long idExercicio) {
        
        if(idExercicio != null) {
            Optional<Exercicio> exercicio = exercicioRepository.findById(idExercicio);

            if(!exercicio.isEmpty()) {
                Exercicio exerc = exercicio.get();
                exerc.setEntradaSaida(entradaSaidaRepository.findByExercicio());
                model.addAttribute("exercicio", );
                System.out.println("quantidades de entradas = " + exercicio.get().getEntradaSaida().size());
                return "exercicio/visualizar.html";
            }
        }

        List<Exercicio> exercicios = exercicioRepository.findAll();
        model.addAttribute("listExercicio", exercicios);
        return "exercicio/list.html";
    }

    @PostMapping("exercicio/criar/add")
    public String actionCriar(
        @ModelAttribute EntradaSaida entradaSaida
    ) {

        if(entradaSaida != null) {
            Exercicio exercicio = entradaSaida.getExercicio();

            if(exercicio != null) {

                exercicio.setAtivo(true);
                exercicioRepository.save(exercicio);

                if(Objects.isNull(exercicio.getId())) {
                    System.out.println("N~ao Guardou Info.");
                } else {
                    entradaSaidaRepository.save(entradaSaida);
                }
            }
        } else {
            System.out.println("Vazio!!");
        }

        return "exercicio/criar.html";
    }
}
