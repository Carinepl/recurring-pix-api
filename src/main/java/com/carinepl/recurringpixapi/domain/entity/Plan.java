package com.carinepl.recurringpixapi.domain.entity;

import com.carinepl.recurringpixapi.domain.enums.BillingCycle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidade que representa um Plano de Assinatura.
 * 
 * Define os tipos de planos disponíveis para contratação pelos clientes,
 * com seus respectivos preços e ciclos de cobrança.
 */
@Entity
@Table(name = "plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    
    /**
     * Identificador único do plano (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Nome do plano (ex: "Premium Mensal", "Básico Anual").
     */
    @Column(nullable = false, length = 50)
    private String name;
    
    /**
     * Descrição detalhada do plano e seus benefícios.
     */
    @Column(length = 255)
    private String description;
    
    /**
     * Valor do plano.
     * Utiliza BigDecimal para precisão monetária (evita erros de arredondamento).
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    /**
     * Periodicidade da cobrança (MONTHLY, QUARTERLY, YEARLY).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BillingCycle billingCycle;
    
    /**
     * Indica se o plano está ativo para novas contratações.
     * Planos inativos não aparecem para novos clientes, mas continuam válidos
     * para assinaturas existentes.
     */
    @Column(nullable = false)
    private Boolean active = true;
    
    /**
     * Lista de assinaturas vinculadas a este plano.
     * Relacionamento OneToMany - um plano pode ter várias assinaturas.
     */
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    @JsonIgnore // Evita loops infinitos na serialização JSON
    private List<Subscription> subscriptions = new ArrayList<>();
    
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
