package com.carinepl.recurringpixapi.infrastructure.repository;

import com.carinepl.recurringpixapi.domain.entity.Plan;
import com.carinepl.recurringpixapi.domain.enums.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository para operações de banco de dados da entidade Plan.
 * 
 * Fornece métodos para buscar planos por diferentes critérios,
 * incluindo filtros por status ativo e ciclo de cobrança.
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, UUID> {
    
    /**
     * Busca todos os planos que estão ativos para contratação.
     * 
     * @return Lista de planos ativos
     */
    List<Plan> findByActiveTrue();
    
    /**
     * Busca um plano pelo nome (case insensitive).
     * 
     * @param name Nome do plano
     * @return Optional contendo o plano se encontrado
     */
    Optional<Plan> findByNameIgnoreCase(String name);
    
    /**
     * Busca planos por ciclo de cobrança.
     * 
     * @param billingCycle Ciclo de cobrança (MONTHLY, QUARTERLY, YEARLY)
     * @return Lista de planos com o ciclo especificado
     */
    List<Plan> findByBillingCycle(BillingCycle billingCycle);
    
    /**
     * Busca planos ativos por ciclo de cobrança.
     * 
     * @param billingCycle Ciclo de cobrança
     * @return Lista de planos ativos com o ciclo especificado
     */
    List<Plan> findByActiveTrueAndBillingCycle(BillingCycle billingCycle);
}
