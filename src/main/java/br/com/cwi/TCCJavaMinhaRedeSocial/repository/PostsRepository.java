package br.com.cwi.TCCJavaMinhaRedeSocial.repository;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUsuarioId(Long usuarioId, Pageable pageable);

    List<Post> findByUsuarioId(Long usuarioId);

    List<Post> findAllByUsuarioIdIn(List<Long> idsDeAmigos);

    Page<Post> findAllByUsuarioIdIn(List<Long> idsDeAmigos,Pageable pageable);

    Page<Post> findAllByUsuarioIdInOrderByDataInclusaoDesc(List<Long> idsDeAmigos, Pageable pageable);
}
