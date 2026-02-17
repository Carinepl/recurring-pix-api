package com.carinepl.recurringpixapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa um Usuário do sistema (Autenticação).
 * 
 * Gerencia credenciais de login e controle de acesso.
 * Utilizado para autenticação JWT e autorização baseada em roles.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    /**
     * Identificador único do usuário (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Nome de usuário para login (único no sistema).
     */
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    
    /**
     * Senha criptografada (usando BCrypt).
     * NUNCA armazenar senhas em texto puro!
     */
    @Column(nullable = false, length = 255)
    private String password;
    
    /**
     * Email do usuário (único no sistema).
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    /**
     * Papel/função do usuário no sistema.
     * Exemplos: ROLE_USER, ROLE_ADMIN
     * Utilizado para controle de acesso (autorização).
     */
    @Column(nullable = false, length = 20)
    private String role;
    
    /**
     * Indica se a conta está ativa.
     * Contas desativadas não podem fazer login.
     */
    @Column(nullable = false)
    private Boolean enabled = true;
    
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
