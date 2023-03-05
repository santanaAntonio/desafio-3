package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidaPerfilServiceTest {

    @Mock
    private ListarUsuariosService listarUsuariosService;

    @InjectMocks
    private ValidaPerfilService validaPerfilService;

    @Test
    void atualizarPerfil_deveRetornarUsuarioAtualizado() {
        Long id = 1L;


        AtualizarUsuarioRequest request = new AtualizarUsuarioRequest();
        UsuarioResponse expectedResponse = new UsuarioResponse();

        when(listarUsuariosService.atualizarPerfil(id, request)).thenReturn(expectedResponse);

        UsuarioResponse response = validaPerfilService.atualizarPerfil(id, request);

        assertEquals(expectedResponse, response);
    }

    @Test
    void atualizarPerfil_deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        Long id = 1L;
        AtualizarUsuarioRequest request = new AtualizarUsuarioRequest();

        when(listarUsuariosService.atualizarPerfil(id, request)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> validaPerfilService.atualizarPerfil(id, request),
                "Usuário não encontrado.");
    }



    @Test
    void listarPorNomeOuEmail_deveRetornarListaDeUsuarios() {
        String text = "John";
        Pageable pageable = Pageable.unpaged();
        Page<UsuarioResponse> expectedResponse = new PageImpl<>(Collections.singletonList(new UsuarioResponse()));

        when(listarUsuariosService.listarPorNomeOuEmail(text, pageable)).thenReturn(expectedResponse);

        Page<UsuarioResponse> response = validaPerfilService.listarPorNomeOuEmail(text, pageable);

        assertEquals(expectedResponse, response);
    }

    @Test
    void listarPorNomeOuEmail_deveLancarExcecaoQuandoTextoForVazio() {
        String text = "";
        Pageable pageable = Pageable.unpaged();

        assertThrows(ResponseStatusException.class, () -> validaPerfilService.listarPorNomeOuEmail(text, pageable),
                "O parâmetro 'text' é obrigatório.");
    }

}
