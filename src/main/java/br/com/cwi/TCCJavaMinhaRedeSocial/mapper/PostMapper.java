package br.com.cwi.TCCJavaMinhaRedeSocial.mapper;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;

import java.time.LocalDateTime;

public class PostMapper {
    public static Post toResponse(InserirPostRequest request) {
        return Post.builder()
                .conteudoEscrito(request.getConteudoEscrito())
                .conteudoImagem(request.getConteudoImagem())
                .dataInclusao(LocalDateTime.now())
                .visualizacao(request.getVisualizacao())
                .build();
    }
}
