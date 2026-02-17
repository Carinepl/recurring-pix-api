package com.carinepl.recurringpixapi.infrastructure.repository;

import com.carinepl.recurringpixapi.domain.entity.Transaction;
import com.carinepl.recurringpixapi.domain.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository para operações de banco de dados da entidade Transaction.
 * 
 * Fornece métodos para buscar transações por assinatura, status,
 * ID externo (do gateway) e datas de criação.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    /**
     * Busca todas as transações de uma assinatura específica.
     * 
     * @param subscriptionId ID da assinatura
     * @return Lista de transações da assinatura
     */
    List<Transaction> findBySubscriptionId(UUID subscriptionId);
    
    /**
     * Busca transações por status.
     * 
     * @param status Status da transação (PENDING, PAID, FAILED, REFUNDED)
     * @return Lista de transações com o status especificado
     */
    List<Transaction> findByStatus(TransactionStatus status);
    
    /**
     * Busca uma transação pelo ID externo do gateway de pagamento.
     * 
     * Útil para processar webhooks e reconciliar pagamentos com o sistema externo.
     * 
     * @param externalId ID da transação no gateway de pagamento
     * @return Optional contendo a transação se encontrada
     */
    Optional<Transaction> findByExternalTransactionId(String externalId);
    
    /**
     * Busca transações com status específico criadas antes de uma data.
     * 
     * Útil para encontrar transações pendentes antigas que precisam ser verificadas
     * ou marcadas como falhas (timeout).
     * 
     * @param status Status da transação
     * @param date Data limite de criação
     * @return Lista de transações que atendem aos critérios
     */
    List<Transaction> findByStatusAndCreatedAtBefore(TransactionStatus status, LocalDateTime date);
    
    /**
     * Busca transações de uma assinatura específica com um status específico.
     * 
     * @param subscriptionId ID da assinatura
     * @param status Status da transação
     * @return Lista de transações que atendem aos critérios
     */
    List<Transaction> findBySubscriptionIdAndStatus(UUID subscriptionId, TransactionStatus status);
}
