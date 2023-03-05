package br.com.cwi.TCCJavaMinhaRedeSocial.controller;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private UsuarioAutenticadoService service;

    @Autowired
    private ComentariosService comentariosService;

    @GetMapping("/{id}")
    public List<Comentario> getComentarios(@PathVariable Long id) {
        return comentariosService.getComentarios(id);
    }

    @PostMapping("/{id}/comentar")
    public void inserirComentario(@PathVariable Long id, @RequestBody InserirComentarioRequest request) {
        comentariosService.inserirComentario(id,request);
    }
}
