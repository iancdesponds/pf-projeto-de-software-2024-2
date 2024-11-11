package br.insper.projeto.common;

import java.time.LocalDateTime;

public class Erro {

    private String mensagem;
    private LocalDateTime data;
    private Integer codigo;

    public Erro(String mensagem, Integer codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
        this.data = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
