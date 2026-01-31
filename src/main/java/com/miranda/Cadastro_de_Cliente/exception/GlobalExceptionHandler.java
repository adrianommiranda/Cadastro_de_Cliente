package com.miranda.Cadastro_de_Cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Tratador Global de Exceções da aplicação.
 * 
 * @ControllerAdvice: Intercepta exceções lançadas por qualquer Controller
 * e permite tratá-las de forma centralizada, retornando respostas JSON padronizadas.
 * 
 * Benefícios:
 * - Controllers ficam limpos (sem try-catch)
 * - Respostas de erro padronizadas em toda a aplicação
 * - Facilita manutenção e adição de novos tratamentos
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de entidade não encontrada.
     * HTTP 404 - Not Found
     */
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handleEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException ex, 
            WebRequest request) {
        
        return buildErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            request
        );
    }

    /**
     * Trata exceções de regra de negócio (duplicidades, validações de negócio).
     * HTTP 409 - Conflict
     */
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, Object>> handleRegraNegocio(
            RegraNegocioException ex, 
            WebRequest request) {
        
        return buildErrorResponse(
            HttpStatus.CONFLICT,  
            ex.getMessage(), 
            request
        );
    }

    /**
     * Trata exceções de validação do Bean Validation (@Valid, @NotBlank, etc.).
     * HTTP 400 - Bad Request
     * 
     * Retorna lista detalhada dos campos com erro.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        
        // Coleta todos os erros de validação: campo -> mensagem
        Map<String, String> errosValidacao = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errosValidacao.put(error.getField(), error.getDefaultMessage())
        );

        // Monta resposta com lista de erros
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Um ou mais campos possuem valores inválidos.");
        body.put("erros", errosValidacao);
        body.put("path", extractPath(request));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Trata exceções genéricas não previstas.
     * HTTP 500 - Internal Server Error
     * 
     * IMPORTANTE: Em produção, não exponha detalhes técnicos do erro.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, 
            WebRequest request) {
        
        // Loga a exceção no console para debug (importante para desenvolvimento)
        ex.printStackTrace();
        
        return buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.", 
            request
        );
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Constrói uma resposta de erro padronizada.
     * Usar LinkedHashMap mantém a ordem dos campos no JSON.
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status, 
            String message, 
            WebRequest request) {
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", extractPath(request));

        return ResponseEntity.status(status).body(body);
    }

    /**
     * Extrai o path da requisição de forma limpa.
     */
    private String extractPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}

