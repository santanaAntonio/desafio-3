package br.com.cwi.TCCJavaMinhaRedeSocial.repository;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    List<Curtida> findAllByIdPost(Long idPost);

    Curtida findByIdPostAndIdUsuario(Long idPost, Long idUsuario);
}
