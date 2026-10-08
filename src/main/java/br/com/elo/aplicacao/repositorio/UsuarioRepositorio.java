package br.com.elo.aplicacao.repositorio;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.elo.aplicacao.modelo.Usuario;
// -------------------------------------------------------------------------------------
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
