package br.com.ifba.webapi.infrastrutucture.exception;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice // Indica que esta classe é um manipulador global de exceções para a API REST.
public class ApiExceptionHandler /*extends ResponseEntityExceptionHandler*/ { // Classe que trata exceções e herda de ResponseEntityExceptionHandler.

//    @Value(value = "${server.erro.include-exception}")
    //private boolean printStacktrace; // Configuração comentada para decidir se a stack trace será incluída na resposta.

    // Define um método para tratar exceções do tipo MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        // Obtém a lista de erros de campo (FieldError) da exceção
        List<FieldError> fieldErrors = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors();

        // Concatena os nomes dos campos que falharam na validação em uma única string
        String fields = fieldErrors
                .stream()
                .map(FieldError::getField) // Mapeia cada FieldError para o nome do campo
                .collect(Collectors.joining()); // Junta os nomes dos campos em uma única string

        // Concatena as mensagens de erro associadas aos campos em uma única string
        String fieldsMessage = fieldErrors
                .stream()
                .map(FieldError::getDefaultMessage) // Mapeia cada FieldError para a mensagem de erro padrão
                .collect(Collectors.joining()); // Junta as mensagens de erro em uma única string

        // Retorna uma ResponseEntity com os detalhes da exceção de validação e o status HTTP BAD_REQUEST
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now()) // Define o timestamp atual
                        .status(HttpStatus.BAD_REQUEST.value()) // Define o status HTTP como 400 (Bad Request)
                        .title("Bad Request Exception, Campos fields") // Define um título para a exceção
                        .details("Check the error fields") // Adiciona detalhes sobre o erro
                        .developerMessage(methodArgumentNotValidException.getClass().getName()) // Adiciona a mensagem do desenvolvedor (nome da classe da exceção)
                        .fields(fields) // Adiciona os nomes dos campos que falharam na validação
                        .fieldsMessage(fieldsMessage) // Adiciona as mensagens de erro dos campos
                        .build(), HttpStatus.BAD_REQUEST); // Constrói o objeto ValidationExceptionDetails e define o status HTTP
    }

    private ResponseEntity<Object> buildErrorMessage( // Método para construir a mensagem de erro.
                                                      final Exception exception, final String message, final HttpStatus httpStatus, final WebRequest request
    ) {
        // Mapa contendo os detalhes da resposta de erro.
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now()); // Data e hora do erro.
        errorDetails.put("status", httpStatus.value()); // Código de status HTTP.
        errorDetails.put("error", httpStatus.getReasonPhrase()); // Descrição do status HTTP.
        errorDetails.put("message", message); // Mensagem específica do erro.
        errorDetails.put("path", request.getDescription(false)); // Caminho da requisição que gerou o erro.
        errorDetails.put("exception", exception.getClass().getSimpleName()); // Nome da exceção capturada.

        // Retorna a resposta com o status HTTP e os detalhes do erro.
        return ResponseEntity.status(httpStatus).body(errorDetails);
    }

    @ExceptionHandler(BusinessException.class) // Indica que este método trata exceções do tipo BusinessException.
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Define o código de status HTTP para BAD_REQUEST (400).
    public ResponseEntity<Object> handleBusinessException(
            final BusinessException businessException, // Exceção capturada.
            final WebRequest request){ // Detalhes da requisição que gerou a exceção.

        final String messageError = businessException.getMessage(); // Obtém a mensagem da exceção.
        Logger logger = null;
        logger.error(messageError, businessException); // Registra o erro no log.

        return buildErrorMessage( // Chama o método para construir a mensagem de erro.
                businessException,
                messageError,
                HttpStatus.INTERNAL_SERVER_ERROR, // Define o status HTTP da resposta como INTERNAL_SERVER_ERROR (500).
                request);
    }
}