package com.carinepl.recurringpixapi.domain.valueobject;

import com.carinepl.recurringpixapi.domain.exception.BusinessException;

import java.util.Objects;

/**
 * Value Object que representa um CPF (Cadastro de Pessoa Física).
 * 
 * Implementa validação automática do CPF através do algoritmo de dígitos verificadores.
 * É imutável - uma vez criado, não pode ser alterado.
 * Dois CPFs com o mesmo valor são considerados iguais.
 */
public class CPF {
    
    private final String value;
    
    /**
     * Construtor que cria um CPF válido.
     * 
     * @param value CPF em formato de string (apenas números, 11 dígitos)
     * @throws BusinessException se o CPF for inválido
     */
    public CPF(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessException("CPF não pode ser nulo ou vazio");
        }
        
        // Remove caracteres não numéricos
        String cleanCpf = value.replaceAll("[^0-9]", "");
        
        if (!isValid(cleanCpf)) {
            throw new BusinessException("CPF inválido: " + value);
        }
        
        this.value = cleanCpf;
    }
    
    /**
     * Valida um CPF usando o algoritmo de dígitos verificadores.
     * 
     * @param cpf CPF apenas com números (11 dígitos)
     * @return true se o CPF é válido, false caso contrário
     */
    private boolean isValid(String cpf) {
        // CPF deve ter exatamente 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            // Calcula o primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) {
                firstDigit = 0;
            }
            
            // Verifica o primeiro dígito verificador
            if (firstDigit != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }
            
            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) {
                secondDigit = 0;
            }
            
            // Verifica o segundo dígito verificador
            return secondDigit == Character.getNumericValue(cpf.charAt(10));
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Retorna o CPF sem formatação (apenas números).
     * 
     * @return CPF com 11 dígitos numéricos
     */
    public String getValue() {
        return value;
    }
    
    /**
     * Retorna o CPF formatado (XXX.XXX.XXX-XX).
     * 
     * @return CPF formatado
     */
    public String getFormatted() {
        return value.substring(0, 3) + "." +
               value.substring(3, 6) + "." +
               value.substring(6, 9) + "-" +
               value.substring(9, 11);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(value, cpf.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return getFormatted();
    }
}
