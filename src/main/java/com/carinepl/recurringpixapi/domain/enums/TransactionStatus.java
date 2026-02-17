package com.carinepl.recurringpixapi.domain.enums;

/**
 * Enum que representa os possíveis status de uma transação PIX.
 * 
 * Fluxo típico de uma transação:
 * 1. Criada com status PENDING
 * 2. Gateway de pagamento gera o código PIX
 * 3. Cliente realiza o pagamento
 * 4. Webhook do gateway atualiza para PAID
 * 5. Em caso de problemas, pode ser marcada como FAILED
 * 6. Se necessário estorno, pode ser marcada como REFUNDED
 */
public enum TransactionStatus {
    
    /**
     * Transação criada e aguardando pagamento.
     * Código PIX já foi gerado e está disponível para o cliente.
     */
    PENDING,
    
    /**
     * Pagamento confirmado pelo gateway.
     * A transação foi finalizada com sucesso.
     */
    PAID,
    
    /**
     * Pagamento falhou ou foi rejeitado.
     * Pode ocorrer por timeout, saldo insuficiente, ou problemas técnicos.
     */
    FAILED,
    
    /**
     * Pagamento foi estornado/reembolsado ao cliente.
     * Ocorre em casos de cancelamento, erro ou solicitação de reembolso.
     */
    REFUNDED
}
