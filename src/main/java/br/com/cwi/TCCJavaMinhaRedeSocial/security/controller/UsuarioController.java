package br.com.cwi.TCCJavaMinhaRedeSocial.security.controller;

import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.request.UsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.service.IncluirUsuarioService;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService service;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public UsuarioResponse incluir(@RequestBody UsuarioRequest request) {
        return service.incluir(request);
    }


}
