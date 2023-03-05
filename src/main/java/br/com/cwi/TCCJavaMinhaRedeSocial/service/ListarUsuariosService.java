package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.AtualizarUsuarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.mapper.UsuarioMapper;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ListarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponse> listarPorNomeOuEmail(String recebido, Pageable pageable) {

        return usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCaseAndAtivo(recebido, recebido, true, pageable)
                .map(UsuarioMapper::toResponse);
    }

    public UsuarioResponse procurarPorId(Long id) {
        Usuario usuarioEncontrado = usuarioRepository.findById(id).get();
        return UsuarioMapper.toResponse(usuarioEncontrado);
    }

    public UsuarioResponse atualizarPerfil(Long id, AtualizarUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id).get();

        if(Objects.nonNull(request.getApelido())){
        usuario.setApelido(request.getApelido());
        }
        if(Objects.nonNull(request.getImagemPerfil())){
            usuario.setImagemPerfil(request.getImagemPerfil());
        }
        if(Objects.nonNull(request.getNomeCompleto())){
            usuario.setNomeCompleto(request.getNomeCompleto());
        }
        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }
}
