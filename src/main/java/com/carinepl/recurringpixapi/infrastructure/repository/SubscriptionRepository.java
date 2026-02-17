package com.carinepl.recurringpixapi.infrastructure.repository;

import com.carinepl.recurringpixapi.domain.entity.Subscription;
import com.carinepl.recurringpixapi.domain.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Repository para operações de banco de dados da entidade Subscription.
 * 
 * Fornece métodos para buscar assinaturas por cliente, status,
 * e datas de cobrança (útil para jobs automáticos de cobrança).
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    
    /**
     * Busca todas as assinaturas de um cliente específico.
     * 
     * @param customerId ID do cliente
     * @return Lista de assinaturas do cliente
     */
    List<Subscription> findByCustomerId(UUID customerId);
    
    /**
     * Busca assinaturas por status.
     * 
     * @param status Status da assinatura (ACTIVE, PAUSED, CANCELLED, EXPIRED)
     * @return Lista de assinaturas com o status especificado
     */
    List<Subscription> findByStatus(SubscriptionStatus status);
    
    /**
     * Busca assinaturas de um cliente específico com um status específico.
     * 
     * @param customerId ID do cliente
     * @param status Status da assinatura
     * @return Lista de assinaturas que atendem aos critérios
     */
    List<Subscription> findByCustomerIdAndStatus(UUID customerId, SubscriptionStatus status);
    
    /**
     * Busca assinaturas com próxima data de cobrança anterior à data informada.
     * 
     * Útil para jobs automáticos que processam cobranças vencidas.
     * Por exemplo: buscar todas as assinaturas que deveriam ter sido cobradas até hoje.
     * 
     * @param date Data limite para próxima cobrança
     * @return Lista de assinaturas com cobrança pendente
     */
    List<Subscription> findByNextBillingDateBefore(LocalDate date);
    
    /**
     * Busca assinaturas ativas com próxima data de cobrança anterior ou igual à data informada.
     * 
     * Útil para processar apenas assinaturas ativas que precisam ser cobradas.
     * 
     * @param date Data limite para próxima cobrança
     * @return Lista de assinaturas ativas com cobrança pendente
     */
    List<Subscription> findByStatusAndNextBillingDateLessThanEqual(SubscriptionStatus status, LocalDate date);
}
