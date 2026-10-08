package br.com.elo.aplicacao.modelo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
// -------------------------------------------------------------------------------------
@Document("usuarios")
public class Usuario {
    @Id private String id;
    private String nome;
    @Indexed(unique = true) private String email;
    private String senha;
    private Perfil perfil;
    // -------------------------------------------------------------------------------------
    public Usuario() { }
    public Usuario(String nome, String email, String senha, Perfil perfil) {
        this.nome = nome; this.email = email; this.senha = senha; this.perfil = perfil;
    }
    // -------------------------------------------------------------------------------------
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public Perfil getPerfil() { return perfil; }
}
