package br.com.cwi.TCCJavaMinhaRedeSocial.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(STRING)
    private Visualizacao visualizacao;

    private String conteudoImagem;

    private String conteudoEscrito;

    @NotNull
    private LocalDateTime dataInclusao;

    private Long usuarioId;
}