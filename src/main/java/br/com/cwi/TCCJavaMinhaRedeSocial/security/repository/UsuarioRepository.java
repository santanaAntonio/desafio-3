package br.com.cwi.TCCJavaMinhaRedeSocial.security.repository;

import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCaseAndAtivo(String nomeCompleto, String email, boolean ativo, Pageable pageable);

}
