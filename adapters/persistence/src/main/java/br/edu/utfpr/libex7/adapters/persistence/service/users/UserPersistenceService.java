package br.edu.utfpr.libex7.adapters.persistence.service.users;

import br.edu.utfpr.libex7.adapters.persistence.entity.users.UserEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceService implements IUserPersistenceService {

    private final UserRepository userRepository;
    @Override
    public UserEntity save(UserEntity user) {
        return (UserEntity) userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        userRepository.remove(id);
    }
}
