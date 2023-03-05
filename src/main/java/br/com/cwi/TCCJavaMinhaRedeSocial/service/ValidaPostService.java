package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ValidaPostService {


    @Autowired
    private PostService postService;

    public Page<Post> getPosts(Long id, Pageable pageable) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "ID inválido");
        }
        return postService.getPosts(id, pageable);
    }

    public void inserirPost(Long id, InserirPostRequest request) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY,"ID inválido");
        }
        postService.inserirPost(id, request);
    }

    public void curtirPost(Long idPost, Long idUsuario) {
        if (idPost == null || idPost <= 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "ID do Post inválido");
        }
        if (idUsuario == null || idUsuario <= 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY,"ID do usuário inválido");
        }
        postService.curtirPost(idPost, idUsuario);
    }

    public void descurtirPost(Long idPost, Long idUsuario) {
        if (idPost == null || idPost <= 0) {
            throw new IllegalArgumentException("ID do post inválido");
        }
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido");
        }
       postService.descurtirPost(idPost, idUsuario);
    }

    public int curtidasRealizadas(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY,"ID inválido");
        }
        return postService.curtidasRealizadas(id);
    }
}
