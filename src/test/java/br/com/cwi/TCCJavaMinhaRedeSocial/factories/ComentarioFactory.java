package br.com.cwi.TCCJavaMinhaRedeSocial.factories;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;

public class ComentarioFactory {

    public static Comentario get() {
        Comentario comentario = new Comentario();
        comentario.setId(SimpleFactory.getRandomLong());
        comentario.setComentario("Comentario de Teste");
        comentario.setIdPost(SimpleFactory.getRandomLong());
        comentario.setIdUsuario(SimpleFactory.getRandomLong());
        return comentario;
    }

    public static InserirComentarioRequest getInserirRequest() {
        InserirComentarioRequest request = new InserirComentarioRequest();
        request.setComentario("Comentario de Teste");
        request.setIdUsuario(SimpleFactory.getRandomLong());
        return request;
    }
}
