package br.com.ifba.webapi.infrastrutucture.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

// Indica que esta classe é uma superclasse mapeada, podendo ser herdada por outras entidades JPA
@MappedSuperclass
// Anotação do Lombok para gerar automaticamente getters, setters, equals, hashCode e toString
@Data
public class PersistenceEntity {

    // Define este campo como a chave primária para as entidades que herdam esta classe
    @Id
    // Configura a geração automática do valor da chave primária usando a estratégia de auto-incremento do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

