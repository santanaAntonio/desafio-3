package br.com.cwi.TCCJavaMinhaRedeSocial.repository;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Amigo;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.PedidoAmizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmigosRepository extends JpaRepository<Amigo, Long> {

    List<Amigo> findByIdUsuarioAndStatus(Long idUsuario, PedidoAmizade pedidoAmizade);

    Amigo findByIdUsuarioAndIdAmigoAndStatus(Long idUsuario, Long idAmigo, PedidoAmizade pedidoAmizade);

    List<Amigo> findByIdUsuarioOrIdAmigoAndStatus(Long idUsuario, Long idAmigo, PedidoAmizade status);

    List<Amigo> findByIdAmigoAndStatus(Long idAmigo, PedidoAmizade solicitado);

    boolean existsByIdUsuarioAndIdAmigoAndStatus(Long idUsuario, Long idAmigo, PedidoAmizade solicitado);
}
