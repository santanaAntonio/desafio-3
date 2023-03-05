package br.com.cwi.TCCJavaMinhaRedeSocial.controller;

import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.BuscarAmigosService;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ValidaAmigosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amigos")
public class AmigosController {

    @Autowired
    private BuscarAmigosService buscarAmigosService;

    @Autowired
    private ValidaAmigosService validaAmigosService;

    @GetMapping("/{id}")
    public List<UsuarioResponse> getAmigos(@PathVariable Long id) {
        return buscarAmigosService.getAmigos(id);
    }

    @GetMapping("/{id}/procurar/{nomeOuEmail}")
    public List<UsuarioResponse> getAmigosPorNomeOuEmail(@PathVariable Long id,@PathVariable String nomeOuEmail) {
        return buscarAmigosService.getAmigosProcurado(id,nomeOuEmail);
    }

    @GetMapping("/{id}/solicitacoes")
    public List<UsuarioResponse> getSolicitacoes(@PathVariable Long id) {
        return buscarAmigosService.getSolicitacoes(id);
    }

    @PostMapping("/{idUsuario}/solicitar/{idAmigo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void solicitarAmizade(@PathVariable Long idUsuario,@PathVariable Long idAmigo) {
        validaAmigosService.validarSolicitacao(idUsuario, idAmigo);
        buscarAmigosService.solicitarAmizade(idUsuario,idAmigo);
    }

    @PostMapping("/{idUsuario}/aceitar/{idAmigo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void aceitarAmizade(@PathVariable Long idUsuario,@PathVariable Long idAmigo) {
        validaAmigosService.validarAceitacao(idUsuario, idAmigo);
        buscarAmigosService.aceitarSolicitacao(idUsuario,idAmigo);
    }

    @PostMapping("/{idUsuario}/remover/{idAmigo}")
    @ResponseStatus(HttpStatus.OK)
    public void removerAmizade(@PathVariable Long idUsuario,@PathVariable Long idAmigo) {
        validaAmigosService.validarRemocao(idUsuario, idAmigo);
        buscarAmigosService.removerAmizade(idUsuario,idAmigo);
    }
}
