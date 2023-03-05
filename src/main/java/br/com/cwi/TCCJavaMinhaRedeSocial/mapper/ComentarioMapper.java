package br.com.cwi.TCCJavaMinhaRedeSocial.mapper;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;

public class ComentarioMapper {
    public static Comentario toEntity(InserirComentarioRequest request) {
        return Comentario.builder()
                .comentario(request.getComentario())
                .idUsuario(request.getIdUsuario())
                .build();
    }
}
