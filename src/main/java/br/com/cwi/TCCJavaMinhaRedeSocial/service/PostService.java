package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.controller.request.InserirPostRequest;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Curtida;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Post;
import br.com.cwi.TCCJavaMinhaRedeSocial.mapper.PostMapper;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.AmigosRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.CurtidaRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.PostsRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.mapper.UsuarioMapper;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private AmigosRepository amigosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostsRepository postsRepository;


    @Autowired
    private CurtidaRepository curtidaRepository;



    @Autowired
    private BuscarAmigosService buscarAmigosService;

    @Autowired
    private BuscarInformacoesUsuarioService buscarInformacoesUsuarioService;

    public Page<Post> getPosts(Long id, Pageable pageable) {
        //Page<Post> meusPosts = postsRepository.findByUsuarioId(id, pageable);
        Usuario usuario = usuarioRepository.findById(id).get();
        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(usuario);

        List<UsuarioResponse> listaDeAmigos =  buscarAmigosService.getAmigos(id);

        listaDeAmigos.add(usuarioResponse);

        List<Post> todosOsPosts = postsRepository.findAll();

        List<Long> idsDeAmigos = listaDeAmigos.stream().map(amigo -> amigo.getId()).collect(Collectors.toList());
        List<Post> postsDeAmigos = postsRepository.findAllByUsuarioIdIn(idsDeAmigos);




        return postsRepository.findAllByUsuarioIdInOrderByDataInclusaoDesc(idsDeAmigos, pageable);
    }

    public void inserirPost(Long id, InserirPostRequest request) {

        Post post = PostMapper.toResponse(request);

        post.setUsuarioId(id);

        postsRepository.save(post);
    }

    public void curtirPost(Long idPost, Long idUsuario) {

        Curtida curtida = new Curtida();
        curtida.setIdPost(idPost);
        curtida.setIdUsuario(idUsuario);

        curtidaRepository.save(curtida);
    }

    public int curtidasRealizadas(Long id) {
        List<Curtida> todasCurtidasPost = curtidaRepository.findAllByIdPost(id);
        return todasCurtidasPost.size();
    }

    public void descurtirPost(Long idPost, Long idUsuario) {

      Curtida curtidaRealizadaAnteriormente = curtidaRepository.findByIdPostAndIdUsuario( idPost, idUsuario);

      curtidaRepository.delete(curtidaRealizadaAnteriormente);
    }
}
