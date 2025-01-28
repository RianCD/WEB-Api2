package br.com.ifba.webapi.controller;


import br.com.ifba.webapi.entity.User;
import br.com.ifba.webapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Indica que esta classe é um controlador REST
@RestController
// Define o caminho base para os endpoints deste controlador como "/users"
@RequestMapping(path = "/users")
// Gera um construtor com argumentos obrigatórios para campos finais (final)
@RequiredArgsConstructor
public class UserController {
    // Injeta a dependência de UserService automaticamente pelo construtor
    private final UserService userService;

    // Define o endpoint POST em "/users/save" para salvar um usuário
    // Especifica que a requisição deve consumir JSON
    @PostMapping(path = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody User user) {
        // Retorna a resposta com o status HTTP 201 (Created) e o objeto salvo
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    // Define o endpoint GET em "/users/findall" para buscar todos os usuários
    // Especifica que a resposta será no formato JSON
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        // Retorna a resposta com o status HTTP 200 (OK) e a lista de usuários
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }
}
