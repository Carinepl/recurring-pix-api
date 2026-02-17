package com.carinepl.recurringpixapi.infrastructure.repository;

import com.carinepl.recurringpixapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository para operações de banco de dados da entidade User.
 * 
 * Fornece métodos para buscar usuários por username e email,
 * essenciais para autenticação e validações de unicidade.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    /**
     * Busca um usuário pelo nome de usuário (username).
     * 
     * Utilizado durante o processo de autenticação para carregar
     * as credenciais do usuário.
     * 
     * @param username Nome de usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Busca um usuário pelo email.
     * 
     * @param email Email do usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Verifica se já existe um usuário com o username informado.
     * 
     * @param username Username a ser verificado
     * @return true se já existe um usuário com este username
     */
    boolean existsByUsername(String username);
    
    /**
     * Verifica se já existe um usuário com o email informado.
     * 
     * @param email Email a ser verificado
     * @return true se já existe um usuário com este email
     */
    boolean existsByEmail(String email);
}
