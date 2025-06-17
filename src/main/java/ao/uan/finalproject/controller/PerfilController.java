package ao.uan.finalproject.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ao.uan.finalproject.domain.Departamento;
import ao.uan.finalproject.domain.Turma;
import ao.uan.finalproject.domain.Utilizador;
import ao.uan.finalproject.repository.DepartamentoRepository;
import ao.uan.finalproject.repository.TurmaRepository;
import ao.uan.finalproject.repository.UtilizadorRepository;
import ao.uan.finalproject.util.Constants;

@Controller
public class PerfilController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @GetMapping("/turma/listar.html")
    public String turmaListar(Model model) {
        List<Turma> turmas = turmaRepository.findAll();
        model.addAttribute("turmaList", turmas);
        return "perfil/turna-list.html";
    }

    @GetMapping("/turma/criar")
    public String viewCriarTurma(Model model) {
        model.addAttribute("turma", new Turma());
        return "perfil/turma-criar.html";
    }

    @PostMapping("/turma/criar/add")
    public String actionCriarTurma(@ModelAttribute Turma turma, Model model) {
        if (!Objects.isNull(turma)) {
            turma.setDataCriacao(new Date());
            turmaRepository.save(turma);
        }
        return "perfil/turma-criar.html";
    }

    @GetMapping("/departamento/listar.html")
    public String departamentoListar(Model model) {
        List<Departamento> departamentos = departamentoRepository.findAll();
        model.addAttribute("depaList", departamentos);
        return "perfil/depa-list.html";
    }

    @GetMapping("/departamento/criar")
    public String viewCriarDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "perfil/depa-criar.html";
    }

    @PostMapping("/departamento/criar/add")
    public String actionCriarDepartamento(@ModelAttribute Departamento departamento, Model model) {
        if (!Objects.isNull(departamento)) {
            departamento.setDateCriacao(new Date());
            departamentoRepository.save(departamento);
        }
        return "perfil/depa-list.html";
    }

    @GetMapping("/config/users")
    public String userListar(Model model) {
        List<Utilizador> utilizadors = utilizadorRepository.findAll();
        model.addAttribute("userList", utilizadors);
        return "perfil/user-list.html";
    }

    @GetMapping("/user/criar")
    public String viewCriarUser(Model model) {

        List<Turma> listTurma = turmaRepository.findAll();

        model.addAttribute("utilizador", new Utilizador());
        model.addAttribute("listTurma", listTurma);
        return "perfil/user-criar.html";
    }

    @PostMapping("/user/criar/add")
    public String actionCriarUser(@ModelAttribute Utilizador utilizador, Model model) {
        if (!Objects.isNull(utilizador)) {
            utilizador.setCodeName(null);
            utilizador.setDateCriacao(new Date());
            utilizador.setPassword(Constants.DEFAULT_PASSWORD);

            double aleatorio = Math.random();
            int convertAl = (int) (aleatorio*10);
            int convertFirstName = (int) (aleatorio*utilizador.getPrimeiroNome().length());
            String vv = String.valueOf(utilizador.getDateCriacao().getTime());

            String codeName = utilizador.getPrimeiroNome().charAt(convertFirstName)
                    + utilizador.getUltimoNome()
                    + vv.indexOf(convertAl);

            utilizador.setCodeName(codeName);
            utilizadorRepository.save(utilizador);
        }
        return "perfil/user-list.html";
    }
}
