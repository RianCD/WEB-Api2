package br.com.ifba.webapi.repository;

import br.com.ifba.webapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interface é um componente de repositório Spring e será gerenciada pelo Spring
@Repository
// Interface que herda JpaRepository para fornecer métodos padrão de CRUD e mais funcionalidades
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository já inclui métodos padrão, como save, findAll, findById, delete, entre outros
}

