package br.com.cwi.TCCJavaMinhaRedeSocial.controller.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirComentarioRequest {

    @NotNull
    private String comentario;

    @NotNull
    private Long idUsuario;
}
