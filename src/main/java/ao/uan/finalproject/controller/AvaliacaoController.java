package ao.uan.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    
    @GetMapping("/")
    public String screenAvaliacao() {
        return "";
    }
}
