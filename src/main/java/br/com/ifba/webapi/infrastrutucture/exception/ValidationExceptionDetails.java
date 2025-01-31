package br.com.ifba.webapi.infrastrutucture.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// Anotação do Lombok que gera automaticamente os métodos getters, setters, toString, equals e hashCode
@Data
// Anotação do Lombok que gera um construtor com todos os argumentos
@AllArgsConstructor
// Anotação do Lombok que implementa o padrão Builder para a classe
@Builder
public class ValidationExceptionDetails {
    // Campo que armazena o timestamp (data e hora) em que a exceção ocorreu
    private LocalDateTime timestamp;

    // Campo que armazena o status HTTP associado à exceção (por exemplo, 400 para Bad Request)
    private int status;

    // Campo que armazena um título descritivo para a exceção
    private String title;

    // Campo que armazena detalhes adicionais sobre a exceção
    private String details;

    // Campo que armazena uma mensagem técnica para desenvolvedores, geralmente o nome da classe da exceção
    private String developerMessage;

    // Campo que armazena os nomes dos campos que falharam na validação, concatenados em uma única string
    private String fields;

    // Campo que armazena as mensagens de erro associadas aos campos, concatenadas em uma única string
    private String fieldsMessage;
}