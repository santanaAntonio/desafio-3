package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Service
public class ValidaComentarioService {

    public void validarInserirComentario(@Valid InserirComentarioRequest request) {
        if (request.getComentario() == null || request.getComentario().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O texto do comentário não pode ser vazio.");
        }
    }
}
