package br.com.elo.aplicacao.controlador;
import jakarta.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.com.elo.aplicacao.formulario.CadastroFormulario;
import br.com.elo.aplicacao.servico.UsuarioServico;
// -------------------------------------------------------------------------------------
@Controller
public class ContasControlador {
    private final UsuarioServico usuarios;
    public ContasControlador(UsuarioServico usuarios) { this.usuarios = usuarios; }
    // -------------------------------------------------------------------------------------
    @GetMapping("/") public String raiz() { return "redirect:/login"; }
    @GetMapping("/login") public String login() { return "aplicacao/login"; }
    @GetMapping("/cadastro") public String cadastro(Model model) {
        model.addAttribute("cadastro", new CadastroFormulario());
        return "aplicacao/cadastro";
    }
    // -------------------------------------------------------------------------------------
    @PostMapping("/cadastro")
    public String cadastrar(@Valid @ModelAttribute("cadastro") CadastroFormulario cadastro, BindingResult erros) {
        if (erros.hasErrors()) return "aplicacao/cadastro";
        try { usuarios.cadastrar(cadastro); }
        catch (DuplicateKeyException e) {
            erros.reject("cadastro", "Não foi possível cadastrar com esses dados. Confira o email ou entre na sua conta.");
            return "aplicacao/cadastro";
        }
        return "redirect:/login?cadastrado";
    }
}
