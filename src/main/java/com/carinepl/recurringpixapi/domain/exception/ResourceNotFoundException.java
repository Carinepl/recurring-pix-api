package com.carinepl.recurringpixapi.domain.exception;

/**
 * Exceção customizada lançada quando um recurso não é encontrado no sistema.
 * 
 * Utilizada em operações de busca que não retornam resultados, como:
 * - Cliente não encontrado pelo ID
 * - Plano inexistente
 * - Assinatura não localizada
 * - Transação não encontrada
 * 
 * Esta exceção geralmente resultará em uma resposta HTTP 404 (Not Found).
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Construtor que cria a exceção com uma mensagem descritiva.
     * 
     * @param message Mensagem explicando qual recurso não foi encontrado
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
