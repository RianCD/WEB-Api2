package br.com.ifba.webapi.client;

import br.com.ifba.webapi.user.dto.UserPostRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service // Indica que esta classe é um serviço gerenciado pelo Spring.
@Slf4j // O uso da anotação @Slf4j permite a utilização do logger sem precisar instanciá-lo manualmente. Caso precise de outro tipo de logger, verifique as opções disponíveis no Lombok.
public class SpringClient { // Esta classe define um cliente Spring WebClient para interagir com uma API REST.
    public static void main(String[] args) {
        // Criação de um WebClient para fazer requisições HTTP para a API.
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/users") // Define a URL base da API.
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE) // Define o cabeçalho padrão como JSON.
                .build();

        // Realiza uma requisição GET para buscar todos os usuários cadastrados.
        String getResponse = webClient.get()
                .uri("/findall") // Define o endpoint a ser acessado.
                .retrieve() // Obtém a resposta da requisição.
                .bodyToMono(String.class) // Converte o corpo da resposta para String.
                .block(); // Torna a chamada síncrona, bloqueando a execução até receber a resposta.

        log.info(getResponse); // Registra a resposta no log.

        // Criação de um objeto DTO para enviar dados na requisição POST.
        UserPostRequestDto usuarioPostRequestDto = new UserPostRequestDto();
        usuarioPostRequestDto.setEmail("email@email.com"); // Define o e-mail do usuário.
        usuarioPostRequestDto.setPassword("password"); // Define a senha do usuário.
        usuarioPostRequestDto.setUsername("username"); // Define o nome de usuário.
        usuarioPostRequestDto.setName("name"); // Define o nome completo do usuário.

        // Realiza uma requisição POST para salvar um novo usuário na API.
        String postResponse = webClient.post()
                .uri("/save") // Define o endpoint para salvar o usuário.
                .body(Mono.just(usuarioPostRequestDto), UserPostRequestDto.class) // Define o corpo da requisição como um objeto JSON.
                .retrieve() // Obtém a resposta da requisição.
                .bodyToMono(String.class) // Converte o corpo da resposta para String.
                .block(); // Torna a chamada síncrona.

        log.info(postResponse); // Registra a resposta no log.
    }
}

