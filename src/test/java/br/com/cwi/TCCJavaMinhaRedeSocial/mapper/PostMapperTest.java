package br.com.cwi.TCCJavaMinhaRedeSocial.mapper;


import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Visualizacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PostMapperTest {



    @Test
    public void toResponse_shouldMapCorrectly() {

        InserirPostRequest request = new InserirPostRequest();
        request.setConteudoEscrito("Lorem ipsum dolor sit amet");
        request.setConteudoImagem("https://example.com/image.jpg");
        request.setVisualizacao(Visualizacao.PUBLICO);

        Post post = PostMapper.toResponse(request);

        assertNotNull(post);
        assertEquals(request.getConteudoEscrito(), post.getConteudoEscrito());
        assertEquals(request.getConteudoImagem(), post.getConteudoImagem());
        assertEquals(request.getVisualizacao(), post.getVisualizacao());
        assertNotNull(post.getDataInclusao());

    }

    @Test
    void deveConverterParaToResponse() {
        InserirPostRequest request = new InserirPostRequest();
        request.setConteudoEscrito("Este é um conteúdo escrito.");
        request.setConteudoImagem("https://exemplo.com/imagem.jpg");
        request.setVisualizacao(Visualizacao.PUBLICO);

        Post expectedPost = Post.builder()
                .conteudoEscrito("Este é um conteúdo escrito.")
                .conteudoImagem("https://exemplo.com/imagem.jpg")
                .visualizacao(Visualizacao.PUBLICO)
                .dataInclusao(LocalDateTime.now())
                .build();

        Post actualPost = PostMapper.toResponse(request);

        assertEquals(expectedPost.getConteudoEscrito(), actualPost.getConteudoEscrito());
    }
}
