package br.com.elo.aplicacao.servico;
import java.util.Locale;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.elo.aplicacao.formulario.CadastroFormulario;
import br.com.elo.aplicacao.modelo.*;
import br.com.elo.aplicacao.repositorio.UsuarioRepositorio;
// -------------------------------------------------------------------------------------
@Service
public class UsuarioServico implements UserDetailsService {
    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder encoder;
    public UsuarioServico(UsuarioRepositorio repositorio, PasswordEncoder encoder) {
        this.repositorio = repositorio; this.encoder = encoder;
    }
    // -------------------------------------------------------------------------------------
    public void cadastrar(CadastroFormulario formulario) {
        repositorio.insert(new Usuario(formulario.getNome(), normalizar(formulario.getEmail()),
                encoder.encode(formulario.getSenha()), Perfil.ALUNO));
    }
    // -------------------------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByEmail(normalizar(email))
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas."));
        return User.withUsername(usuario.getEmail()).password(usuario.getSenha())
                .roles(usuario.getPerfil().name()).build();
    }
    private String normalizar(String email) { return email.strip().toLowerCase(Locale.ROOT); }
}
