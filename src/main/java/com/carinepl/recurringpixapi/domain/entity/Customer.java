package com.carinepl.recurringpixapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidade que representa um Cliente/Usuário do sistema.
 * 
 * Armazena os dados cadastrais dos clientes que contratam planos de assinatura.
 * Um cliente pode ter várias assinaturas ao longo do tempo.
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    /**
     * Identificador único do cliente (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Nome completo do cliente.
     */
    @Column(nullable = false, length = 100)
    private String name;
    
    /**
     * Email do cliente (único no sistema).
     * Utilizado para contato e login.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    /**
     * CPF do cliente (único no sistema).
     * Armazenado apenas com números (11 dígitos).
     */
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    /**
     * Telefone do cliente com DDD.
     */
    @Column(length = 15)
    private String phone;
    
    /**
     * Lista de assinaturas do cliente.
     * Relacionamento OneToMany - um cliente pode ter várias assinaturas.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
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
