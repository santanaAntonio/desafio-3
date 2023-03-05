package br.com.cwi.TCCJavaMinhaRedeSocial.security.mapper;

import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.request.UsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNomeCompleto(entity.getNomeCompleto());
        response.setEmail(entity.getEmail());
        response.setDataNascimento(entity.getDataNascimento());
        response.setApelido(entity.getApelido());
        response.setImagemPerfil(entity.getImagemPerfil());
        return response;
    }
}
