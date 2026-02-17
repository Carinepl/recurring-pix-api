package com.carinepl.recurringpixapi.domain.enums;

/**
 * Enum que representa os possíveis status de uma assinatura no sistema.
 * 
 * Define o ciclo de vida de uma assinatura e as transições válidas entre estados:
 * - ACTIVE pode transitar para PAUSED, CANCELLED ou EXPIRED
 * - PAUSED pode transitar para ACTIVE ou CANCELLED
 * - CANCELLED e EXPIRED são estados finais (não podem ser reativadas)
 */
public enum SubscriptionStatus {
    
    /**
     * Assinatura ativa e funcionando normalmente.
     * Pagamentos estão sendo processados conforme o ciclo de cobrança.
     */
    ACTIVE,
    
    /**
     * Assinatura temporariamente pausada pelo cliente.
     * Não serão realizadas cobranças enquanto pausada.
     * Pode ser reativada pelo cliente.
     */
    PAUSED,
    
    /**
     * Assinatura cancelada pelo cliente.
     * Estado final - não pode ser reativada.
     * Cliente precisa criar uma nova assinatura se desejar voltar.
     */
    CANCELLED,
    
    /**
     * Assinatura expirou por falta de pagamento.
     * Estado final - não pode ser reativada.
     * Cliente precisa criar uma nova assinatura se desejar voltar.
     */
    EXPIRED
}
