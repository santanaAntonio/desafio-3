package br.com.cwi.TCCJavaMinhaRedeSocial.service;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Amigo;
import br.com.cwi.TCCJavaMinhaRedeSocial.repository.AmigosRepository;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.mapper.UsuarioMapper;
import br.com.cwi.TCCJavaMinhaRedeSocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.cwi.TCCJavaMinhaRedeSocial.domain.PedidoAmizade.ACEITO;
import static br.com.cwi.TCCJavaMinhaRedeSocial.domain.PedidoAmizade.SOLICITADO;

@Service
public class BuscarAmigosService {

    @Autowired
    private AmigosRepository amigosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarInformacoesUsuarioService buscarInformacoesUsuarioService;

    public Usuario buscarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario;
    }

    public boolean saoAmigos(Long idUsuario, Long idAmigo) {
        Usuario usuario = buscarUsuario(idUsuario);
        Usuario amigo = buscarUsuario(idAmigo);

        if (!getAmigos(usuario.getId()).contains(amigo)) {
            return false;
        }
        return true;
    }

    public boolean solicitacaoEnviada(Long idUsuario, Long idAmigo) {
        return amigosRepository.existsByIdUsuarioAndIdAmigoAndStatus(idUsuario, idAmigo, SOLICITADO) || amigosRepository.existsByIdUsuarioAndIdAmigoAndStatus(idAmigo, idUsuario, SOLICITADO);
    }

    public boolean solicitacaoRecebida(Long idUsuario, Long idAmigo) {
        if (Objects.isNull(buscarUsuario(idUsuario)) || Objects.isNull(buscarUsuario(idAmigo))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        if (solicitacaoEnviada(idAmigo, idUsuario)) {
            return true;
        }
        return false;
    }

    public List<UsuarioResponse> getAmigos(Long id) {

        List<Amigo> listaDeAmigos = amigosRepository.findByIdUsuarioOrIdAmigoAndStatus(id, id, ACEITO);

        listaDeAmigos = listaDeAmigos.stream().filter(amigo -> amigo.getStatus().equals(ACEITO)).collect(Collectors.toList());

        List<Usuario> listaAmigosMapeada = listaDeAmigos.stream()
                .map(amigo -> buscarInformacoesUsuarioService.buscarUsuario(amigo.getIdAmigo()))
                .collect(Collectors.toList());

        List<Usuario> listaAmigosMapeada2 = listaDeAmigos.stream()
                .map(amigo -> buscarInformacoesUsuarioService.buscarUsuario(amigo.getIdUsuario()))
                .collect(Collectors.toList());

        listaAmigosMapeada.addAll(listaAmigosMapeada2);

        return listaAmigosMapeada.stream()
                .filter(usuario -> !usuario.getId().equals(id))
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponse>  getAmigosProcurado(Long id, String nomeOuEmail) {

        List<Amigo> listaDeAmigos = amigosRepository.findByIdUsuarioOrIdAmigoAndStatus(id, id, ACEITO);

        List<Usuario> listaAmigosMapeada = listaDeAmigos.stream()
                .map(amigo -> buscarInformacoesUsuarioService.buscarUsuario(amigo.getIdAmigo()))
                .collect(Collectors.toList());

        List<Usuario> listaAmigosMapeada2 = listaDeAmigos.stream()
                .map(amigo -> buscarInformacoesUsuarioService.buscarUsuario(amigo.getIdUsuario()))
                .collect(Collectors.toList());

        listaAmigosMapeada.addAll(listaAmigosMapeada2);

        List<Usuario> amigosFiltrados = listaAmigosMapeada.stream()
                .filter(usuario -> !usuario.getId().equals(id))
                .filter(usuario -> nomeOuEmail == null ||
                        usuario.getNomeCompleto().toLowerCase().contains(nomeOuEmail.toLowerCase()) ||
                        usuario.getEmail().toLowerCase().contains(nomeOuEmail.toLowerCase()))
                .collect(Collectors.toList());

        return amigosFiltrados.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }


    public List<UsuarioResponse> getSolicitacoes(Long id) {

        List<Amigo> listaDeAmigos = amigosRepository.findByIdAmigoAndStatus(id, SOLICITADO);

        List<Usuario> listaAmigosMapeada = listaDeAmigos.stream()
                .map(amigo -> buscarInformacoesUsuarioService.buscarUsuario(amigo.getIdUsuario()))
                .collect(Collectors.toList());

        return listaAmigosMapeada.stream().map(UsuarioMapper::toResponse).collect(Collectors.toList());
    }

    public void solicitarAmizade(Long idUsuario, Long idAmigo) {

        Amigo novaSolicitacao = new Amigo(idUsuario,idAmigo,SOLICITADO);
        amigosRepository.save(novaSolicitacao);
    }

    public void aceitarSolicitacao(Long idUsuario, Long idAmigo) {

        Amigo solicitacaoAmizade = amigosRepository
                .findByIdUsuarioAndIdAmigoAndStatus(idAmigo,idUsuario, SOLICITADO);

        solicitacaoAmizade.setStatus(ACEITO);

        amigosRepository.save(solicitacaoAmizade);
    }

    public void removerAmizade(Long idUsuario, Long idAmigo) {
        Amigo amizadeExistente = amigosRepository
                .findByIdUsuarioAndIdAmigoAndStatus(idUsuario, idAmigo, ACEITO);

        if(Objects.isNull(amizadeExistente)){
            amizadeExistente = amigosRepository
                    .findByIdUsuarioAndIdAmigoAndStatus(idAmigo, idUsuario, ACEITO);
        }

        amigosRepository.delete(amizadeExistente);
    }
}
