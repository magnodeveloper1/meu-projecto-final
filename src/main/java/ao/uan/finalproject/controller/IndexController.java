package ao.uan.finalproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import ao.uan.finalproject.domain.Utilizador;
import ao.uan.finalproject.repository.UtilizadorRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/login.html")
    public String login(Model view) {
        view.addAttribute("user", new Utilizador());
        return "/login.html";
    }

    @GetMapping("/logout.html")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    @PostMapping("/process/login")
    public String actionLogin(
            @ModelAttribute Utilizador utilizador, Model model, HttpSession session) {
        Optional<Utilizador> userLogado = utilizadorRepository.findByCodeNameAndPassword(
                utilizador.getCodeName(),
                utilizador.getPassword());

        if(!userLogado.isPresent()) {
            
        }

        session.setAttribute("userLogado", userLogado);

        return "redirect:/";
    }
}
