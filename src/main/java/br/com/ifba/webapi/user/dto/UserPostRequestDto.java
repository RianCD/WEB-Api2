package br.com.ifba.webapi.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente os métodos getter, setter, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os campos como argumentos.

public class UserPostRequestDto { // Classe DTO usada para representar os dados recebidos ao criar um usuário.

    @JsonProperty(value = "name")
    // Define o nome do campo na requisição JSON como "name".
    private String name;

    @JsonProperty(value = "username")
    // Define o nome do campo na requisição JSON como "username".
    private String username;

    @JsonProperty(value = "email")
    // Define o nome do campo na requisição JSON como "email".
    private String email;

    @JsonProperty(value = "password")
    // Define o nome do campo na requisição JSON como "password".
    private String password;
}

