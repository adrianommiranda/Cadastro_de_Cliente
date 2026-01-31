package com.miranda.Cadastro_de_Cliente.exception;
/*
    * Exceção lançada quando uma operação viola uma regra de negócio do sistema.
    * Exemplo de uso: tentar cadastrar um cliente com nome ou URL já existente.
    * Resulta em HTTP 409 Conflict (tratado pelo GlobalExceptionHandler).
*/ 

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String mensage){
        super(mensage);
    }
}
