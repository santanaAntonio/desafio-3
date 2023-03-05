package br.com.cwi.TCCJavaMinhaRedeSocial.factories;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Visualizacao;

import java.time.LocalDateTime;

public class PostFactory {

    public static Post get() {
        Post post = new Post();
        post.setId(SimpleFactory.getRandomLong());
        post.setVisualizacao(Visualizacao.PUBLICO);
        post.setDataInclusao(LocalDateTime.now());
        post.setUsuarioId(SimpleFactory.getRandomLong());
        return post;
    }
}
