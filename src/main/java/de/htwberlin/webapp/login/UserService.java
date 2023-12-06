package de.htwberlin.webapp.login;


import de.htwberlin.webapp.persistence.UserEntity;
import de.htwberlin.webapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    public UserService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public List<User> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        var userEntityOptional = userRepository.findById(id);
        return userEntityOptional.map(userTransformer::transformEntity).orElse(null);
    }

    public User create(UserManipulationRequest request) {
        var userEntity = new UserEntity(request.getUsername(),request.getPassword(), request.getFirstname(), request.getLastname());
        userEntity = userRepository.save(userEntity);
        return userTransformer.transformEntity(userEntity);
    }

    public User authenticate(String username, String password) {
        // Hier sollten Sie den Benutzer anhand der Anmeldeinformationen überprüfen
        // Wenn die Anmeldung erfolgreich ist, geben Sie den Benutzer zurück
        // Führen Sie die Anmeldeüberprüfung durch, z. B. in Ihrem UserService
        UserEntity authenticatedUser = userRepository.findByUsernameAndPassword(username, password);
        if (authenticatedUser != null) {
            // Anmeldung erfolgreich
            return userTransformer.transformEntity(authenticatedUser);
        } else {
            // Anmeldung fehlgeschlagen
            return null;
        }
    }

    public User update(Long id, UserManipulationRequest request) {
        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()) {
            return null;
        }

        var userEntity = userEntityOptional.get();
        userEntity.setPassword(request.getPassword());
        userEntity.setUsername(request.getUsername());
        userEntity.setFirstname(request.getFirstname());
        userEntity.setLastname(request.getLastname());
        userEntity = userRepository.save(userEntity);

        return userTransformer.transformEntity(userEntity);
    }

    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }


}
