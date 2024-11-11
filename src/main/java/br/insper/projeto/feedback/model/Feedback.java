package br.insper.projeto.feedback.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "feedbacks")
public class Feedback {

    @Id
    private String id;
    private String conteudo;
    private String usuarioId;
    private String resposta;
}
