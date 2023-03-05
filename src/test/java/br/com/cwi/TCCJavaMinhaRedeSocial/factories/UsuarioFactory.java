package br.com.cwi.TCCJavaMinhaRedeSocial.factories;

import br.com.cwi.TCCJavaMinhaRedeSocial.security.domain.Usuario;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNomeCompleto("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        return usuario;
    }
}
