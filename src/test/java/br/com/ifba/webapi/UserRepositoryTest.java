package br.com.ifba.webapi;

import br.com.ifba.webapi.user.entity.User;
import br.com.ifba.webapi.user.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest // Anotação que configura um ambiente de teste para JPA, utilizando um banco de dados em memória.
@DisplayName("Tests for User Repository") // Define um nome descritivo para a classe de testes.
@ActiveProfiles("test") // Define o perfil de teste para utilizar configurações específicas do application-test.properties.
@RequiredArgsConstructor(onConstructor_ = {@Autowired}) // Injeta automaticamente o repositório no construtor.
public class UserRepositoryTest {

    //    @Autowired // Injeção comentada pois a injeção está sendo feita pelo RequiredArgsConstructor.
    private final UserRepository userRepository;

    @Test
    @DisplayName("Find by username when successful") // Define um nome descritivo para o teste.
    public void findByUsername_whenSuccessful(){
        // Realiza a busca de um usuário pelo username "albertin".
        Optional<User> userFound = userRepository.findByUsername("albertin");

        // Verifica se o usuário foi encontrado.
        Assertions.assertThat(userFound.isPresent()).isTrue();
        Assertions.assertThat(userFound).isNotNull();
        Assertions.assertThat(userFound.get().getUsername()).isEqualTo("albertin");
    }

    @Test
    @DisplayName("Find by username when user not found") // Testa se um username inexistente retorna um Optional vazio.
    public void findByUsername_whenUserNotFound(){
        Optional<User> userFound = userRepository.findByUsername("non_existent_user");

        // Verifica se o usuário não foi encontrado.
        Assertions.assertThat(userFound).isNotPresent();
    }

    @Test
    @DisplayName("Find user by username and password when successful") // Testa a busca por username e senha.
    public void findByUsernameAndPassword_whenSuccessful(){
        Optional<User> userFound = userRepository.findByUsernameAndPassword("albertin", "157");

        // Verifica se o usuário foi encontrado e se os dados estão corretos.
        Assertions.assertThat(userFound.isPresent()).isTrue();
        Assertions.assertThat(userFound).isNotNull();
        Assertions.assertThat(userFound.get().getUsername()).isEqualTo("albertin");
        Assertions.assertThat(userFound.get().getPassword()).isEqualTo("157");
    }

    @Test
    @DisplayName("Find user by username and password when user not found") // Testa se um usuário inexistente não é encontrado.
    public void findByUsernameAndPassword_whenUserNotFound(){
        Optional<User> userFound = userRepository.findByUsernameAndPassword("albertin", "157");

        // Verifica se o usuário não foi encontrado.
        Assertions.assertThat(userFound).isNotPresent();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty") // Testa se a exceção é lançada ao tentar salvar um usuário sem nome.
    void save_throwConstraintViolationException_whenNameIsEmpty(){
        User user = new User(); // Cria um usuário sem definir nenhum atributo.

        // Verifica se a exceção ConstraintViolationException é lançada ao tentar salvar um usuário inválido.
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> userRepository.save(user))
                .withMessageContaining("Name cannot be empty");
    }

    @BeforeEach // Executa este método antes de cada teste para configurar um estado inicial.
    public void setUp(){
        // Cria um usuário com valores válidos.
        User user = User.builder()
                .name("Alberto Ernesto")
                .username("albertin")
                .email("bertinho157@gmail.com")
                .password("157")
                .build();

        // Salva o usuário no banco de dados em memória.
        userRepository.save(user);
    }
}
