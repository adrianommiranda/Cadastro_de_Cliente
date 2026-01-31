package com.miranda.Cadastro_de_Cliente.exception;

/**
 * Exceção lançada quando um recurso (entidade) não é encontrado no sistema.
 * 
 * Exemplo de uso: buscar um cliente por ID que não existe.
 * Resulta em HTTP 404 Not Found (tratado pelo GlobalExceptionHandler).
 */
public class EntidadeNaoEncontradaException extends RuntimeException {
    
    // Construtor que recebe a mensagem de erro
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
