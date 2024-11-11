package br.insper.projeto.feedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private String papel;
}
