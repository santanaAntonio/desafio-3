package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirComentarioRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Comentario;
import br.com.cwi.TCCJavaMinhaRedeSocial.mapper.ComentarioMapper;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    public List<Comentario> getComentarios(Long id) {
        return comentariosRepository.findAllByIdPost(id);
    }

    public void inserirComentario(Long id, InserirComentarioRequest request) {

        Comentario comentario = ComentarioMapper.toEntity(request);
        comentario.setIdPost(id);

        comentariosRepository.save(comentario);
    }
}
