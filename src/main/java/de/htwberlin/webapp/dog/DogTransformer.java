package de.htwberlin.webapp.dog;

import de.htwberlin.webapp.login.UserTransformer;
import de.htwberlin.webapp.persistence.CycleEntity;
import de.htwberlin.webapp.persistence.FemaleDogEntity;
import de.htwberlin.webapp.persistence.HealthRecordEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DogTransformer {

    private final UserTransformer userTransformer;

    public DogTransformer(UserTransformer userTransformer) {
        this.userTransformer = userTransformer;
    }

    public Dog transformEntity(FemaleDogEntity dogEntity) {
        var cycleIds = dogEntity.getCycles().stream().map(CycleEntity::getId).collect(Collectors.toList());
        var healthRecordIds = dogEntity.getHealthRecords().stream().map(HealthRecordEntity::getId).collect(Collectors.toList());
        return new Dog(
                dogEntity.getId(),
                dogEntity.getName(),
                dogEntity.getBirthDate(),
                dogEntity.getBreed(),
                userTransformer.transformEntity(dogEntity.getOwner()),
                cycleIds,
                healthRecordIds

        );
    }
}
