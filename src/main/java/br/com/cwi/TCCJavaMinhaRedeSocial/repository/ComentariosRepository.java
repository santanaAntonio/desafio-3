package br.com.cwi.TCCJavaMinhaRedeSocial.repository;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByIdPost(Long idPost);
}
