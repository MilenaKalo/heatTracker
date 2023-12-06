package de.htwberlin.webapp.login;

import de.htwberlin.webapp.persistence.FemaleDogEntity;
import de.htwberlin.webapp.persistence.UserEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserTransformer {

    public User transformEntity(UserEntity userEntity) {
        var dogIds = userEntity.getDogs().stream().map(FemaleDogEntity::getId).collect(Collectors.toList());
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getFirstname(),
                userEntity.getLastname(),
                dogIds
        );
    }
}
