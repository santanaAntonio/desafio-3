package br.com.cwi.TCCJavaMinhaRedeSocial.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "amigos")
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_amigo")
    private Long idAmigo;

    @Enumerated(STRING)
    @Column(nullable = false)
    private PedidoAmizade status;

    public Amigo(Long idUsuario, Long idAmigo, PedidoAmizade status) {
        this.idUsuario = idUsuario;
        this.idAmigo = idAmigo;
        this.status = status;
    }
}
