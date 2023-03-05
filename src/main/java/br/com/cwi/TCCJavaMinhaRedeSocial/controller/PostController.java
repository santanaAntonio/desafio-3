package br.com.cwi.TCCJavaMinhaRedeSocial.controller;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ValidaPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/posts")
public class PostController {

    private final ValidaPostService validaPostService;

    public PostController(ValidaPostService validaPostService) {
        this.validaPostService = validaPostService;
    }

    @GetMapping("/{id}")
    public Page<Post> todosOsPosts(@PathVariable Long id, Pageable pageable) {
        return validaPostService.getPosts(id, pageable);
    }

    @PostMapping("/{id}")
    public void adicionarPost(@PathVariable Long id, @RequestBody InserirPostRequest request) {
        validaPostService.inserirPost(id, request);
    }

    @PostMapping("/{idPost}/curtir/{idUsuario}")
    public void curtirPost(@PathVariable Long idPost, @PathVariable Long idUsuario) {
        validaPostService.curtirPost(idPost, idUsuario);
    }

    @PostMapping("/{idPost}/descurtir/{idUsuario}")
    public void descurtirPost(@PathVariable Long idPost, @PathVariable Long idUsuario) {
        validaPostService.descurtirPost(idPost, idUsuario);
    }

    @GetMapping("/{id}/curtidas")
    public int getQuantidadeCurtidas(@PathVariable Long id) {
        return validaPostService.curtidasRealizadas(id);
    }
}
