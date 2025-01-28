package br.com.ifba.webapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotação para indicar que esta classe é uma entidade JPA
@Entity
// Gera automaticamente os métodos getters, setters, equals, hashCode e toString
@Data
// Gera um construtor sem argumentos
@NoArgsConstructor
// Define o nome da tabela no banco de dados como "users"
@Table(name = "users")
public class User {

    // Define este campo como a chave primária da tabela
    @Id
    // Indica que o valor da chave primária será gerado automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define que este campo será uma coluna na tabela
    @Column
    private String name;

    // Define que este campo será uma coluna na tabela
    @Column
    private String username;

    // Define que este campo será uma coluna na tabela
    @Column
    private String email;

}