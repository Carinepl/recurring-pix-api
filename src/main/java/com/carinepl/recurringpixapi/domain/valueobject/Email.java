package com.carinepl.recurringpixapi.domain.valueobject;

import com.carinepl.recurringpixapi.domain.exception.BusinessException;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object que representa um endereço de email.
 * 
 * Implementa validação automática do formato de email usando expressões regulares.
 * É imutável - uma vez criado, não pode ser alterado.
 * Dois emails com o mesmo valor são considerados iguais.
 */
public class Email {
    
    // Padrão regex para validação de email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    private final String value;
    
    /**
     * Construtor que cria um Email válido.
     * 
     * @param value Endereço de email em formato string
     * @throws BusinessException se o email for inválido
     */
    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new BusinessException("Email não pode ser nulo ou vazio");
        }
        
        String trimmedEmail = value.trim().toLowerCase();
        
        if (!isValid(trimmedEmail)) {
            throw new BusinessException("Email inválido: " + value);
        }
        
        this.value = trimmedEmail;
    }
    
    /**
     * Valida o formato do email usando regex.
     * 
     * @param email Email a ser validado
     * @return true se o email é válido, false caso contrário
     */
    private boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Retorna o endereço de email.
     * 
     * @return Email em minúsculas
     */
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}
