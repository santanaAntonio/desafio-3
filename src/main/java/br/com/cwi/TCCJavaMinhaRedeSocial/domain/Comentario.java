package br.com.cwi.TCCJavaMinhaRedeSocial.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private Long idPost;

    @NotNull
    private Long idUsuario;

    @NotNull
    private String comentario;
}
