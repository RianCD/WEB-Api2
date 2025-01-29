package br.com.ifba.webapi.user.service;

import br.com.ifba.webapi.infrastrutucture.exception.BusinessException;
import br.com.ifba.webapi.user.entity.User;
import br.com.ifba.webapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Indica que esta classe é um serviço na camada de negócio e será gerenciada pelo Spring
@Service
// Gera um construtor para inicializar os campos final marcados como @NonNull
@RequiredArgsConstructor
public class UserService implements UserIService {
    // Dependência do repositório UserRepository injetada automaticamente
    private final UserRepository userRepository;

    // Método para salvar um usuário no banco de dados
    @Override
    public User save(User user) {
        // Chama o método save do UserRepository e retorna o usuário salvo
        return userRepository.save(user);
    }

    // Método para buscar todos os usuários do banco de dados
    @Override
    public List<User> findAll() {
        // Chama o método findAll do UserRepository e retorna a lista de usuários
        return userRepository.findAll();
    }

    // Implementação do método para excluir um usuário com base no ID
    @Override
    public void delete(Long id) {
        // Chama o repositório para excluir o usuário pelo ID
        userRepository.deleteById(id);
    }

    // Implementação do método para atualizar um usuário
    @Override
    public User update(User user) {
        // Chama o repositório para salvar as alterações feitas no usuário
        return userRepository.save(user);
    }

    @Override // Sobrescreve o método da interface ou classe pai
    public User findById(Long id) { // Método para buscar um usuário pelo ID
        return userRepository.findById(id) // Busca o usuário no repositório pelo ID
                .orElseThrow(() -> new BusinessException("Not found User")); // Lança uma exceção caso o usuário não seja encontrado
    }
}