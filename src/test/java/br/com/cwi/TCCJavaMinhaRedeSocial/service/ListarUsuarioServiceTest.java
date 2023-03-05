package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.factories.UsuarioFactory;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BuscarInformacoesUsuarioService buscarInformacoesUsuarioService;

    @InjectMocks
    private ListarUsuariosService listarUsuariosService;

    @Test
    void deveProcurarUsuarioPorId() {

        Long id = 1L;
        Usuario usuario = UsuarioFactory.get();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioResponse usuarioResponse = listarUsuariosService.procurarPorId(id);

        assertEquals(id, usuarioResponse.getId());
        assertEquals(usuario.getNomeCompleto(), usuarioResponse.getNomeCompleto());
        assertEquals(usuario.getEmail(), usuarioResponse.getEmail());
        assertEquals(usuario.getApelido(), usuarioResponse.getApelido());
        assertEquals(usuario.getImagemPerfil(), usuarioResponse.getImagemPerfil());
    }


    @Test
    void deveListarUsuariosPorNomeOuEmail() {

        String termoBusca = "user";
        Pageable pageable = PageRequest.of(0, 10);
        Usuario usuario1 = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();
        usuario2.setId(2L);
        usuario2.setNomeCompleto("User Two");
        usuario2.setEmail("user2@test.com");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        Page<Usuario> usuariosPage = new PageImpl<>(usuarios, pageable, usuarios.size());

        when(usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCaseAndAtivo(termoBusca, termoBusca, true, pageable)).thenReturn(usuariosPage);

        Page<UsuarioResponse> usuarioResponsePage = listarUsuariosService.listarPorNomeOuEmail(termoBusca, pageable);

        assertEquals(usuariosPage.getTotalElements(), usuarioResponsePage.getTotalElements());
        assertEquals(usuariosPage.getTotalPages(), usuarioResponsePage.getTotalPages());
        assertEquals(usuariosPage.getNumber(), usuarioResponsePage.getNumber());
        assertEquals(usuariosPage.getSize(), usuarioResponsePage.getSize());
        assertEquals(usuariosPage.getContent().get(0).getNomeCompleto(), usuarioResponsePage.getContent().get(0).getNomeCompleto());
        assertEquals(usuariosPage.getContent().get(0).getEmail(), usuarioResponsePage.getContent().get(0).getEmail());
        assertEquals(usuariosPage.getContent().get(0).getApelido(), usuarioResponsePage.getContent().get(0).getApelido());
        assertEquals(usuariosPage.getContent().get(0).getImagemPerfil(), usuarioResponsePage.getContent().get(0).getImagemPerfil());
        assertEquals(usuariosPage.getContent().get(1).getNomeCompleto(), usuarioResponsePage.getContent().get(1).getNomeCompleto());
        assertEquals(usuariosPage.getContent().get(1).getEmail(), usuarioResponsePage.getContent().get(1).getEmail());
        assertEquals(usuariosPage.getContent().get(1).getApelido(), usuarioResponsePage.getContent().get(1).getApelido());
        assertEquals(usuariosPage.getContent().get(1).getImagemPerfil(), usuarioResponsePage.getContent().get(1).getImagemPerfil());
    }



}



