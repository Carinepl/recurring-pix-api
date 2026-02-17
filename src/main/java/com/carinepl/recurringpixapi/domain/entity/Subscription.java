package com.carinepl.recurringpixapi.domain.entity;

import com.carinepl.recurringpixapi.domain.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidade que representa uma Assinatura de um cliente a um plano.
 * 
 * Gerencia o ciclo de vida das assinaturas, incluindo status, datas de cobrança
 * e relacionamento com transações de pagamento.
 * 
 * Uma assinatura conecta um Cliente a um Plano e mantém o histórico de cobranças.
 */
@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    
    /**
     * Identificador único da assinatura (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Cliente que possui esta assinatura.
     * Relacionamento ManyToOne - muitas assinaturas podem pertencer a um cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    /**
     * Plano contratado nesta assinatura.
     * Relacionamento ManyToOne - muitas assinaturas podem usar o mesmo plano.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
    
    /**
     * Status atual da assinatura.
     * Possíveis valores: ACTIVE, PAUSED, CANCELLED, EXPIRED
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubscriptionStatus status;
    
    /**
     * Data de início da assinatura.
     */
    @Column(nullable = false)
    private LocalDate startDate;
    
    /**
     * Data da próxima cobrança.
     * Calculada automaticamente com base no ciclo de cobrança do plano.
     * Atualizada após cada pagamento bem-sucedido.
     */
    @Column(nullable = false)
    private LocalDate nextBillingDate;
    
    /**
     * Data de término da assinatura.
     * Null enquanto a assinatura estiver ativa.
     * Preenchida quando cancelada ou expirada.
     */
    @Column
    private LocalDate endDate;
    
    /**
     * Lista de transações relacionadas a esta assinatura.
     * Relacionamento OneToMany - uma assinatura pode ter várias transações.
     */
    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita loops infinitos na serialização JSON
    private List<Transaction> transactions = new ArrayList<>();
    
    /**
     * Data e hora de criação do registro.
     * Preenchida automaticamente pelo Hibernate.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Data e hora da última atualização do registro.
     * Atualizada automaticamente pelo Hibernate.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
