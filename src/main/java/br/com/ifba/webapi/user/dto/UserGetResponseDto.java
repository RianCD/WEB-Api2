package br.com.ifba.webapi.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente os métodos getter, setter, toString, equals e hashCode.
@AllArgsConstructor // Gera um construtor com todos os campos como argumentos.
@NoArgsConstructor // Gera um construtor sem argumentos.

public class UserGetResponseDto { // Classe DTO usada para representar os dados do usuário na resposta da API.

    @JsonProperty(value = "name")
    // Define o nome do campo na resposta JSON como "name".
    private String name;

    @JsonProperty(value = "username")
    // Define o nome do campo na resposta JSON como "username".
    private String username;

    @JsonProperty(value = "email")
    // Define o nome do campo na resposta JSON como "email".
    private String email;
}