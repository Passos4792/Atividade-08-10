package br.com.elo.aplicacao.controlador;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// -------------------------------------------------------------------------------------
@Controller
public class PaginasControlador {
    @GetMapping("/inicio") public String inicio() { return "aplicacao/inicio"; }
    @GetMapping("/aluno") public String aluno(Model model) { return pagina(model, "Aluno"); }
    @GetMapping("/professor") public String professor(Model model) { return pagina(model, "Professor"); }
    @GetMapping("/administrador") public String administrador(Model model) { return pagina(model, "Administrador"); }
    // -------------------------------------------------------------------------------------
    private String pagina(Model model, String perfil) {
        model.addAttribute("perfil", perfil);
        return "aplicacao/perfil";
    }
    @RequestMapping("/acesso-negado") @ResponseStatus(HttpStatus.FORBIDDEN)
    public String negado() { return "error/403"; }
}
