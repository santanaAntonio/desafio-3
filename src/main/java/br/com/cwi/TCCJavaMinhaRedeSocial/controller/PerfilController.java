package br.com.cwi.TCCJavaMinhaRedeSocial.controller;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ListarUsuariosService;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ValidaPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private final ValidaPerfilService validaPerfilService;

    public PerfilController(ValidaPerfilService validaPerfilService) {
        this.validaPerfilService = validaPerfilService;
    }

    @GetMapping("/pesquisa")
    public Page<UsuarioResponse> listarPorNomeOuEmail(@RequestParam String text, Pageable pageable) {
        return validaPerfilService.listarPorNomeOuEmail(text, pageable);
    }

    @GetMapping("/{id}")
    public UsuarioResponse listarPorId(@PathVariable Long id) {
        return listarUsuariosService.procurarPorId(id);
    }

    @PostMapping("/{id}/atualizar")
    public UsuarioResponse atualizarPerfil(@PathVariable Long id,@RequestBody AtualizarUsuarioRequest request) {
        return validaPerfilService.atualizarPerfil(id,request);
    }
}
