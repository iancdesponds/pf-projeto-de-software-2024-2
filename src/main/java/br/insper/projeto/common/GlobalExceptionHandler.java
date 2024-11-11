package br.insper.projeto.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Erro> handleRuntimeException(RuntimeException ex) {
        Erro erro = new Erro(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Manipulador de erros adicionais (exemplo para erros de permissão negada)
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Erro> handleSecurityException(SecurityException ex) {
        Erro erro = new Erro("Acesso negado", HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Erro> handleException(Exception ex) {
        Erro erro = new Erro("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
