package br.com.ifba.webapi.service;

import br.com.ifba.webapi.entity.User;
import br.com.ifba.webapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Indica que esta classe é um serviço na camada de negócio e será gerenciada pelo Spring
@Service
// Gera um construtor para inicializar os campos final marcados como @NonNull
@RequiredArgsConstructor
public class UserService {
    // Dependência do repositório UserRepository injetada automaticamente
    private final UserRepository userRepository;

    // Método para salvar um usuário no banco de dados
    public User save(User user) {
        // Chama o método save do UserRepository e retorna o usuário salvo
        return userRepository.save(user);
    }

    // Método para buscar todos os usuários do banco de dados
    public List<User> findAll() {
        // Chama o método findAll do UserRepository e retorna a lista de usuários
        return userRepository.findAll();
    }
}

