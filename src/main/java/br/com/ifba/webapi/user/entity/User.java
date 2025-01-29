package br.com.ifba.webapi.user.entity;

import br.com.ifba.webapi.infrastrutucture.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Anotação para indicar que esta classe é uma entidade JPA
@Entity
// Gera automaticamente os métodos getters, setters, equals, hashCode e toString
@Data
// Gera um construtor sem argumentos
@NoArgsConstructor
// Gera um construtor com todos os argumentos
@AllArgsConstructor
// Define o nome da tabela no banco de dados como "users"
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends PersistenceEntity implements Serializable {

    // Define que este campo será uma coluna na tabela
    @Column
    private String name;

    // Define que este campo será uma coluna na tabela
    @Column
    private String username;

    // Define que este campo será uma coluna na tabela
    @Column
    private String email;

    // Define que este campo será uma coluna na tabela
    @Column
    private String password;

}