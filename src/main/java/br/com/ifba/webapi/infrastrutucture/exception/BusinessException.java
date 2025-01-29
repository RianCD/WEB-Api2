package br.com.ifba.webapi.infrastrutucture.exception;


import java.io.Serial;

public class BusinessException extends RuntimeException {
    // Classe de exceção personalizada que estende RuntimeException,
    // utilizada para representar erros de negócio na aplicação.

    @Serial
    private static final long serialVersionUID = 1L;
    // Identificador único para a serialização da classe.

    public BusinessException() {
        super();
    }
    // Construtor padrão sem argumentos.

    public BusinessException(final String message) {
        super(message);
    }
    // Construtor que recebe uma mensagem de erro e a repassa para a superclasse.

    public BusinessException(final Throwable cause) {
        super(cause);
    }
    // Construtor que recebe uma causa (outra exceção) e a repassa para a superclasse.

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
    // Construtor que recebe uma mensagem de erro e uma causa, repassando ambas para a superclasse.
}

