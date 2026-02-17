package com.carinepl.recurringpixapi.domain.valueobject;

import com.carinepl.recurringpixapi.domain.exception.BusinessException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Value Object que representa um valor monetário.
 * 
 * Encapsula um valor em dinheiro (BigDecimal) com validações e operações seguras.
 * É imutável - todas as operações retornam novos objetos Money.
 * Garante que valores monetários não sejam negativos.
 */
public class Money {
    
    private final BigDecimal amount;
    
    /**
     * Construtor que cria um valor monetário.
     * 
     * @param amount Valor em BigDecimal
     * @throws BusinessException se o valor for nulo ou negativo
     */
    public Money(BigDecimal amount) {
        if (amount == null) {
            throw new BusinessException("Valor monetário não pode ser nulo");
        }
        
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Valor monetário não pode ser negativo: " + amount);
        }
        
        // Arredonda para 2 casas decimais (padrão monetário)
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Construtor auxiliar que aceita valores double.
     * 
     * @param amount Valor em double
     */
    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }
    
    /**
     * Construtor auxiliar que aceita valores String.
     * 
     * @param amount Valor em String (ex: "99.90")
     */
    public Money(String amount) {
        this(new BigDecimal(amount));
    }
    
    /**
     * Adiciona outro valor monetário a este.
     * 
     * @param other Valor a ser adicionado
     * @return Novo objeto Money com a soma
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }
    
    /**
     * Subtrai outro valor monetário deste.
     * 
     * @param other Valor a ser subtraído
     * @return Novo objeto Money com a subtração
     * @throws BusinessException se o resultado for negativo
     */
    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }
    
    /**
     * Multiplica este valor por um fator.
     * 
     * @param factor Fator de multiplicação
     * @return Novo objeto Money com o resultado
     */
    public Money multiply(double factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }
    
    /**
     * Multiplica este valor por um BigDecimal.
     * 
     * @param factor Fator de multiplicação
     * @return Novo objeto Money com o resultado
     */
    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(factor));
    }
    
    /**
     * Retorna o valor como BigDecimal.
     * 
     * @return Valor monetário em BigDecimal
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * Retorna o valor formatado como moeda brasileira (R$ X.XXX,XX).
     * 
     * @return String formatada com o valor monetário
     */
    public String getFormatted() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(amount);
    }
    
    /**
     * Verifica se este valor é maior que outro.
     * 
     * @param other Valor a ser comparado
     * @return true se este valor é maior
     */
    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }
    
    /**
     * Verifica se este valor é menor que outro.
     * 
     * @param other Valor a ser comparado
     * @return true se este valor é menor
     */
    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }
    
    /**
     * Verifica se este valor é zero.
     * 
     * @return true se o valor é zero
     */
    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
    
    @Override
    public String toString() {
        return getFormatted();
    }
}
