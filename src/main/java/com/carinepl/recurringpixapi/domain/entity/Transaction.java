package com.carinepl.recurringpixapi.domain.entity;

import com.carinepl.recurringpixapi.domain.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa uma Transação de Pagamento via PIX.
 * 
 * Registra todas as tentativas de pagamento, incluindo informações do PIX,
 * status da transação e integração com gateway de pagamento.
 * 
 * Fluxo típico:
 * 1. Transação criada com status PENDING
 * 2. Gateway gera código PIX e QR Code
 * 3. Cliente realiza o pagamento
 * 4. Webhook atualiza status para PAID e preenche paidAt
 */
@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    /**
     * Identificador único da transação (UUID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    /**
     * Assinatura relacionada a esta transação.
     * Relacionamento ManyToOne - muitas transações podem pertencer a uma assinatura.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;
    
    /**
     * Valor da transação.
     * Utiliza BigDecimal para precisão monetária.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    /**
     * Status atual da transação.
     * Possíveis valores: PENDING, PAID, FAILED, REFUNDED
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status;
    
    /**
     * Código PIX (copia e cola).
     * Gerado pelo gateway de pagamento.
     * Utilizado pelo cliente para realizar o pagamento.
     */
    @Column(length = 500)
    private String pixCode;
    
    /**
     * QR Code do PIX em formato Base64.
     * Opcional - permite pagamento via leitura de QR Code.
     */
    @Column(length = 1000)
    private String pixQrCode;
    
    /**
     * ID da transação no gateway de pagamento externo.
     * Utilizado para rastreamento e reconciliação com o sistema externo.
     */
    @Column(length = 100)
    private String externalTransactionId;
    
    /**
     * Data e hora em que o pagamento foi confirmado.
     * Null enquanto o pagamento estiver pendente.
     * Preenchida quando o webhook confirma o pagamento.
     */
    @Column
    private LocalDateTime paidAt;
    
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
