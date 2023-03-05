package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaAmigosService {

    private final BuscarAmigosService buscarAmigosService;

    public ValidaAmigosService(BuscarAmigosService buscarAmigosService) {
        this.buscarAmigosService = buscarAmigosService;
    }

    public void validarSolicitacao(Long idUsuario, Long idAmigo) {
        if (buscarAmigosService.saoAmigos(idUsuario, idAmigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário e amigo já são amigos");
        }

        if (buscarAmigosService.solicitacaoEnviada(idUsuario, idAmigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitação de amizade já enviada");
        }

        if (buscarAmigosService.solicitacaoRecebida(idUsuario, idAmigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já recebeu solicitação de amizade deste amigo");
        }
    }

    public void validarAceitacao(Long idUsuario, Long idAmigo) {
        if (!buscarAmigosService.solicitacaoRecebida(idUsuario, idAmigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe solicitação de amizade pendente para este usuário e amigo");
        }
    }

    public void validarRemocao(Long idUsuario, Long idAmigo) {
        if (buscarAmigosService.saoAmigos(idUsuario, idAmigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário e amigo não são amigos");
        }
    }
}
