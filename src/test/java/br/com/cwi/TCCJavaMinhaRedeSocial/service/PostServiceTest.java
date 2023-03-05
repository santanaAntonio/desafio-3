package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import br.com.cwi.TCCJavaMinhaRedeSocial.factories.PostFactory;
import br.com.cwi.TCCJavaMinhaRedeSocial.factories.UsuarioFactory;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.AmigosRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.ComentariosRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.CurtidaRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.PostsRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private AmigosRepository amigosRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private BuscarAmigosService buscarAmigosService;

    @Mock
    private ValidaPostService validaPostService;

    @Mock
    private ComentariosRepository comentariosRepository;

    @Mock
    private ComentariosService comentariosService;
    @Mock
    private BuscarInformacoesUsuarioService buscarInformacoesUsuarioService;

    @InjectMocks
    private PostService postService;

    @Captor
    private ArgumentCaptor<Post> postCaptor;

    @Test
    @DisplayName("Deve fazer uma nova postagem")
    void deveFazerUmaNovaPostagem() {
        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        Long id = post.getId();

        InserirPostRequest request = InserirPostRequest
                .builder()
                .conteudoEscrito("Meu Primeiro Post")
                .build();


        postService.inserirPost(usuario.getId(), request);
        verify(postsRepository).save(postCaptor.capture());

        assertEquals(request.getConteudoEscrito(), postCaptor.getValue().getConteudoEscrito());
    }
}

