package com.carinepl.recurringpixapi.infrastructure.repository;

import com.carinepl.recurringpixapi.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository para operações de banco de dados da entidade Customer.
 * 
 * Estende JpaRepository que fornece métodos CRUD prontos:
 * - save(Customer): Salvar/atualizar cliente
 * - findById(UUID): Buscar por ID
 * - findAll(): Listar todos
 * - deleteById(UUID): Deletar por ID
 * - existsById(UUID): Verificar se existe
 * 
 * Métodos customizados são implementados automaticamente pelo Spring Data JPA
 * baseado nos nomes dos métodos (Query Methods).
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    
    /**
     * Busca um cliente pelo email.
     * 
     * @param email Email do cliente
     * @return Optional contendo o cliente se encontrado
     */
    Optional<Customer> findByEmail(String email);
    
    /**
     * Busca um cliente pelo CPF.
     * 
     * @param cpf CPF do cliente (apenas números, 11 dígitos)
     * @return Optional contendo o cliente se encontrado
     */
    Optional<Customer> findByCpf(String cpf);
    
    /**
     * Verifica se já existe um cliente com o email informado.
     * 
     * @param email Email a ser verificado
     * @return true se já existe um cliente com este email
     */
    boolean existsByEmail(String email);
    
    /**
     * Verifica se já existe um cliente com o CPF informado.
     * 
     * @param cpf CPF a ser verificado
     * @return true se já existe um cliente com este CPF
     */
    boolean existsByCpf(String cpf);
}
