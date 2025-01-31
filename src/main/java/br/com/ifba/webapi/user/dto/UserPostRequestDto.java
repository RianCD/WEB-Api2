package br.com.ifba.webapi.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente os métodos getter, setter, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os campos como argumentos.
public class UserPostRequestDto { // Classe DTO usada para representar os dados recebidos ao criar um usuário.

    @JsonProperty(value = "name")
    // Define o nome do campo na requisição JSON como "name".
    // Anotação que valida se o campo não está em branco (não pode ser nulo, vazio ou apenas espaços em branco)
    @NotBlank(message = "The name is required")
    private String name;

    @JsonProperty(value = "username")
    // Define o nome do campo na requisição JSON como "username".
    // Anotação que valida se o campo não está em branco (não pode ser nulo, vazio ou apenas espaços em branco)
    @NotBlank(message = "The username is required")
// Anotação que valida o tamanho (comprimento) do campo
// Define que o campo deve ter no mínimo 4 caracteres e no máximo 30 caracteres
// Se a validação falhar, a mensagem "At least 4 caracteres and maximum 30" será retornada
    @Size(min = 4, max = 30, message = "At least 4 caracteres and maximum 30")
    private String username;

    @JsonProperty(value = "email")
    // Define o nome do campo na requisição JSON como "email".
    @Email(message = "Invalid Email")
    private String email;

    @JsonProperty(value = "password")
    // Define o nome do campo na requisição JSON como "password".
    // Anotação que valida se o campo não está em branco (não pode ser nulo, vazio ou apenas espaços em branco)
    @NotBlank(message = "The password is required")
    private String password;
}

