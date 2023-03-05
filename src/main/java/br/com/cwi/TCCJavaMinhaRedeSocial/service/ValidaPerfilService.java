package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaPerfilService {

    private final ListarUsuariosService listarUsuariosService;

    public ValidaPerfilService(ListarUsuariosService listarUsuariosService) {
        this.listarUsuariosService = listarUsuariosService;
    }

    public Page<UsuarioResponse> listarPorNomeOuEmail(String text, Pageable pageable) {
        if (text.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O parâmetro 'text' é obrigatório.");
        }
        return listarUsuariosService.listarPorNomeOuEmail(text, pageable);
    }

    public UsuarioResponse atualizarPerfil(Long id, AtualizarUsuarioRequest request) {
        UsuarioResponse usuario = listarUsuariosService.atualizarPerfil(id, request);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        return usuario;
    }
}
