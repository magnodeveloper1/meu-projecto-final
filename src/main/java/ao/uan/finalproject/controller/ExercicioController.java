package ao.uan.finalproject.controller;

import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String screenList() {
        return "exercicio/list.html";
    }

    @GetMapping("exercicio/criar")
    public String screenCriar(Model model) {

        Exercicio exercicio = new Exercicio();
        EntradaSaida entradaSaida = new EntradaSaida();

        model.addAttribute("exercicio", exercicio);
        model.addAttribute("entradaSaida", entradaSaida);
        return "exercicio/criar.html";
    }

    @PostMapping("exercicio/criar/add")
    public String screenCriar2(
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
