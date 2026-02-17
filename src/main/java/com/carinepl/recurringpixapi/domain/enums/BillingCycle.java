package com.carinepl.recurringpixapi.domain.enums;

/**
 * Enum que representa os ciclos de cobrança disponíveis para planos de assinatura.
 * 
 * Cada ciclo possui uma duração específica em dias que determina a periodicidade
 * de cobrança das assinaturas.
 */
public enum BillingCycle {
    
    /**
     * Ciclo de cobrança mensal - renovação a cada 30 dias
     */
    MONTHLY(30, "Mensal"),
    
    /**
     * Ciclo de cobrança trimestral - renovação a cada 90 dias
     */
    QUARTERLY(90, "Trimestral"),
    
    /**
     * Ciclo de cobrança anual - renovação a cada 365 dias
     */
    YEARLY(365, "Anual");
    
    private final int days;
    private final String description;
    
    /**
     * Construtor do enum BillingCycle.
     * 
     * @param days Número de dias do ciclo de cobrança
     * @param description Descrição amigável do ciclo
     */
    BillingCycle(int days, String description) {
        this.days = days;
        this.description = description;
    }
    
    /**
     * Retorna o número de dias do ciclo de cobrança.
     * 
     * @return Quantidade de dias do ciclo
     */
    public int getDays() {
        return days;
    }
    
    /**
     * Retorna a descrição amigável do ciclo de cobrança.
     * 
     * @return Descrição do ciclo
     */
    public String getDescription() {
        return description;
    }
}
