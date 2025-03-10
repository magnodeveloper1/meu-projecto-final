package ao.uan.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("exercicio/criar/2")
    public String screenCriar2(
        @RequestParam(name = "source") String encriptInfo
    ) {

        Object[] values = new Object[8];

        try {
        } catch(Exception ex) {}

        return "exercicio/criar.html";
    }
}
