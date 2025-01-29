package br.com.ifba.webapi.infrastrutucture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice // Indica que esta classe é um manipulador global de exceções para a API REST.
public class ApiExceptionHandler extends ResponseEntityExceptionHandler { // Classe que trata exceções e herda de ResponseEntityExceptionHandler.

//    @Value(value = "${server.erro.include-exception}")
    //private boolean printStacktrace; // Configuração comentada para decidir se a stack trace será incluída na resposta.

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
        logger.error(messageError, businessException); // Registra o erro no log.

        return buildErrorMessage( // Chama o método para construir a mensagem de erro.
                businessException,
                messageError,
                HttpStatus.INTERNAL_SERVER_ERROR, // Define o status HTTP da resposta como INTERNAL_SERVER_ERROR (500).
                request);
    }
}
