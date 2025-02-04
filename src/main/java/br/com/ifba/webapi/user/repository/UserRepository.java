package br.com.ifba.webapi.user.repository;

import br.com.ifba.webapi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Indica que esta interface é um componente de repositório Spring e será gerenciada pelo Spring
@Repository
// Interface que herda JpaRepository para fornecer métodos padrão de CRUD e mais funcionalidades
public interface UserRepository extends JpaRepository<User, Long> {

    // Consulta personalizada usando JPQL para buscar um usuário pelo nome de usuário (username)
    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);

    // Método derivado do nome que busca um usuário pelo nome de usuário e senha
    Optional<User> findByUsernameAndPassword(String name, String password);
}