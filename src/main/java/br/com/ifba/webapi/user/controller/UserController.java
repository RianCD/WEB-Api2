package br.com.ifba.webapi.user.controller;


import br.com.ifba.webapi.user.entity.User;
import br.com.ifba.webapi.user.service.UserService;
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

    // Mapeia a rota DELETE para excluir um recurso com base no ID fornecido
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        // Chama o serviço para excluir o recurso com o ID especificado
        userService.delete(id);
        // Retorna uma resposta com o status HTTP 204 (No Content), indicando que a exclusão foi realizada com sucesso
        return ResponseEntity.noContent().build();
    }

    // Mapeia a rota PUT para atualizar um recurso com base no ID fornecido
    @PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User user) {
        // Define o ID do objeto user como o ID recebido na URL
        user.setId(id);
        // Chama o serviço para atualizar o recurso com os dados fornecidos
        userService.update(user);
        // Retorna uma resposta com o status HTTP 204 (No Content), indicando que a atualização foi realizada com sucesso
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}