package br.com.elo.aplicacao.formulario;
import jakarta.validation.constraints.*;
import java.nio.charset.StandardCharsets;
// -------------------------------------------------------------------------------------
public class CadastroFormulario {
    @NotBlank(message = "Informe seu nome.")
    @Size(min = 2, max = 80, message = "Use de 2 a 80 caracteres no nome.")
    private String nome;
    @NotBlank(message = "Informe seu email.")
    @Email(message = "Informe um email válido.")
    @Size(max = 254, message = "Email muito longo.")
    private String email;
    @NotBlank(message = "Informe uma senha.")
    @Size(min = 12, max = 72, message = "A senha deve ter de 12 a 72 caracteres.")
    private String senha;
    @NotBlank(message = "Confirme sua senha.")
    private String confirmacao;
    // -------------------------------------------------------------------------------------
    @AssertTrue(message = "As senhas precisam ser iguais.")
    public boolean isSenhasIguais() { return senha != null && senha.equals(confirmacao); }
    @AssertTrue(message = "A senha deve ocupar no máximo 72 bytes. Evite excesso de acentos e emojis.")
    public boolean isTamanhoSeguro() { return senha == null || senha.getBytes(StandardCharsets.UTF_8).length <= 72; }
    // -------------------------------------------------------------------------------------
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome == null ? null : nome.strip(); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email == null ? null : email.strip(); }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getConfirmacao() { return confirmacao; }
    public void setConfirmacao(String confirmacao) { this.confirmacao = confirmacao; }
}
