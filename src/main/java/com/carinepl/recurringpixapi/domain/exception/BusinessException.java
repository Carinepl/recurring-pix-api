package com.carinepl.recurringpixapi.domain.exception;

/**
 * Exceção customizada para violações de regras de negócio.
 * 
 * Utilizada quando uma operação viola uma regra do domínio, como:
 * - Tentativa de cancelar uma assinatura já cancelada
 * - CPF ou email inválidos
 * - Operação não permitida no estado atual da entidade
 * - Valores monetários negativos
 * 
 * Esta exceção geralmente resultará em uma resposta HTTP 400 (Bad Request) ou 422 (Unprocessable Entity).
 */
public class BusinessException extends RuntimeException {
    
    /**
     * Construtor que cria a exceção com uma mensagem descritiva da regra violada.
     * 
     * @param message Mensagem explicando qual regra de negócio foi violada
     */
    public BusinessException(String message) {
        super(message);
    }
}
