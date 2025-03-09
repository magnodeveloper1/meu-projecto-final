package ao.uan.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExercicioController {
    
    @GetMapping("/exercicio")
    public String screenList() {
        return "exercicio/list.html";
    }

    @GetMapping("exercicio/criar")
    public String screenCriar() {
        return "exercicio/criar.html";
    }
}
