package br.com.cwi.TCCJavaMinhaRedeSocial.controller.request;

import br.com.cwi.TCCJavaMinhaRedeSocial.domain.Visualizacao;
import lombok.*;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPostRequest {

    @NotNull
    @Enumerated(STRING)
    private Visualizacao visualizacao;

    private String conteudoImagem;

    private String conteudoEscrito;

    @NotNull
    private LocalDateTime dataInclusao;

    @NotNull
    private Long usuarioId;
}
