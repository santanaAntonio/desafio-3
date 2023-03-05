package br.com.cwi.TCCJavaMinhaRedeSocial.factories;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Amigo;
import br.com.cwi.TCCJavaMinhaRedeSocial.domain.PedidoAmizade;

public class AmigosFactory {

    public static Amigo get() {
        Amigo amigo = new Amigo();
        amigo.setId(SimpleFactory.getRandomLong());
        amigo.setStatus(PedidoAmizade.ACEITO);
        amigo.setIdAmigo(SimpleFactory.getRandomLong());
        amigo.setIdUsuario(SimpleFactory.getRandomLong());
        return amigo;
    }
}
