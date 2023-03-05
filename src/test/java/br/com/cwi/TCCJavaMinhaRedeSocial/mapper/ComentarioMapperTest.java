package br.com.cwi.TCCJavaMinhaRedeSocial.mapper;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;
import br.com.cwi.TCCJavaMinhaRedeSocial.factories.ComentarioFactory;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.ComentariosRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.service.ComentariosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



@ExtendWith(MockitoExtension.class)
class ComentarioMapperTest {



    @Mock
    private ComentariosRepository comentariosRepository;

    @Mock
    private ComentariosService comentariosService;


    @Test
    @DisplayName("Deve retornar o entity com as informações obtidas do request")
    void deveRetornarEntityComInfo() {

        InserirComentarioRequest request = ComentarioFactory.getInserirRequest();

        Comentario comentario = ComentarioMapper.toEntity(request);

        assertEquals(comentario.getIdUsuario(), request.getIdUsuario());
        assertEquals(comentario.getComentario(), request.getComentario());
    }


    @Test
    @DisplayName("Deve mapear uma requisição de inserção de comentário para a entidade Comentario")
    void deveMapearUmaRequisicaoDeInsercaoDeComentarioParaEntidadeComentario() {

        InserirComentarioRequest request = InserirComentarioRequest.builder()
                .idUsuario(1L)
                .comentario("Meu primeiro comentário")
                .build();

        Comentario comentario = ComentarioMapper.toEntity(request);

        assertEquals(request.getIdUsuario(), comentario.getIdUsuario());
        assertEquals(request.getComentario(), comentario.getComentario());
    }

    @Test
    @DisplayName("Deve mapear uma requisição de inserção de comentário sem ID de usuário para a entidade Comentario")
    void deveMapearUmaRequisicaoDeInsercaoDeComentarioSemIdUsuarioParaEntidadeComentario() {

        InserirComentarioRequest request = InserirComentarioRequest.builder()
                .comentario("Meu primeiro comentário")
                .build();

        Comentario comentario = ComentarioMapper.toEntity(request);

        assertNull(comentario.getIdUsuario());

        assertEquals(request.getComentario(), comentario.getComentario());
    }
}
