package br.com.elo.configuracao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
// -------------------------------------------------------------------------------------
@ControllerAdvice
public class TemaConfiguracao {
    private final String tema;
    public TemaConfiguracao(@Value("${app.tema}") String tema) {
        if (!tema.matches("[a-z0-9_]+") || !new ClassPathResource("static/temas/" + tema + "/tema.css").exists()) {
            throw new IllegalArgumentException("Tema inexistente ou inválido.");
        }
        this.tema = tema;
    }
    // -------------------------------------------------------------------------------------
    @ModelAttribute("tema")
    public String tema() { return tema; }
}
