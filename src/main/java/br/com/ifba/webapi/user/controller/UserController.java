package br.com.ifba.webapi.user.controller;


import br.com.ifba.webapi.infrastrutucture.mapper.ObjectMapperUtil;
import br.com.ifba.webapi.user.dto.UserGetResponseDto;
import br.com.ifba.webapi.user.dto.UserPostRequestDto;
import br.com.ifba.webapi.user.entity.User;
import br.com.ifba.webapi.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // Indica que esta classe é um controlador REST
@RequestMapping(path = "/users") // Define o caminho base para os endpoints deste controlador como "/users"
@RequiredArgsConstructor // Gera um construtor com argumentos obrigatórios para campos finais (final)
public class UserController {
    // Injeta a dependência de UserService automaticamente pelo construtor
    private final UserService userService;

    private final ObjectMapperUtil objectMapperUtil;

    // Define o endpoint POST em "/users/save" para salvar um usuário
    // Especifica que a requisição deve consumir JSON
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UserPostRequestDto userPostRequestDto) {
        // @RequestBody: Indica que o corpo (body) da requisição HTTP será mapeado para o objeto userPostRequestDto.
        // @Valid: Ativa a validação do objeto userPostRequestDto com base nas anotações de validação presentes na classe UserPostRequestDto.
        // Retorna a resposta com o status HTTP 201 (Created) e o objeto salvo
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(userService.save(
                        (objectMapperUtil.map(userPostRequestDto, User.class))),
                        UserGetResponseDto.class));
    }

    // Define o endpoint GET em "/users/findall" para buscar todos os usuários
    // Especifica que a resposta será no formato JSON
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(Pageable pageable) {
        // Retorna a resposta com o status HTTP 200 (OK) e a lista de usuários
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userService.findAll(pageable)
                        .map(c -> objectMapperUtil.map(c, UserGetResponseDto.class)));
        //No service é retornado um Page<Usuario>, porém no controller é retornado um Page<UserGetResponseDto>
        //desta forma, é necessário converter os objetos usuario do Page para UserGetResponseDto
        //para isso foi utilizado o método map
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
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid User user) {
        // Define o ID do objeto user como o ID recebido na URL
        user.setId(id);
        // Chama o serviço para atualizar o recurso com os dados fornecidos
        userService.update(user);
        // Retorna uma resposta com o status HTTP 204 (No Content), indicando que a atualização foi realizada com sucesso
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
// Mapeia requisições HTTP GET para o endpoint "/users/findById/{id}" e define a resposta no formato JSON.
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        // Método para buscar um usuário pelo ID fornecido na URL.
        return ResponseEntity.status(HttpStatus.OK)
                // Retorna uma resposta com o status HTTP 200 (OK).
                .body(objectMapperUtil.map(
                        // Converte o objeto retornado pelo serviço para um DTO antes de enviá-lo na resposta.
                        this.userService.findById(id),
                        // Chama o serviço para buscar o usuário pelo ID.
                        UserGetResponseDto.class));
        // Converte a entidade User para UserGetResponseDto antes de retornar.
    }

}