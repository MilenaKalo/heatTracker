package de.htwberlin.webapp.dog;
import de.htwberlin.webapp.login.UserTransformer;
import de.htwberlin.webapp.persistence.FemaleDogEntity;
import de.htwberlin.webapp.repository.CycleRepository;
import de.htwberlin.webapp.repository.DogRepository;
import de.htwberlin.webapp.repository.HealthRecordRepository;
import de.htwberlin.webapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DogService {

    private final DogRepository dogRepository;
    private final UserTransformer userTransformer;
    private final UserRepository userRepository;

    private final DogTransformer dogTransformer;
    private final CycleRepository cycleRepository;
    private final HealthRecordRepository healthRecordRepository;

    public DogService(DogRepository dogRepository, UserTransformer userTransformer, UserRepository userRepository, DogTransformer dogTransformer,
                      CycleRepository cycleRepository,
                      HealthRecordRepository healthRecordRepository) {
        this.dogRepository = dogRepository;
        this.userTransformer = userTransformer;
        this.userRepository = userRepository;
        this.dogTransformer = dogTransformer;
        this.cycleRepository = cycleRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    public List<Dog> findAll() {
        List<FemaleDogEntity> dogs = dogRepository.findAll();
        return dogs.stream()
                .map(dogTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Dog findById(Long id) {
        var dogEntityOptional = dogRepository.findById(id);
        return dogEntityOptional.map(dogTransformer::transformEntity).orElse(null);
    }

    public Dog create(DogManipulationRequest request) {
        var owner = userRepository.findById(request.getOwnerId()).orElseThrow();
        var dogEntity = new FemaleDogEntity(request.getName(), request.getBirthDate(), request.getBreed(), owner);
        dogEntity = dogRepository.save(dogEntity);
        return dogTransformer.transformEntity(dogEntity);
    }

    public Dog update(Long id, DogManipulationRequest request) {
        var dogEntityOptional = dogRepository.findById(id);
        if (dogEntityOptional.isEmpty()) {
            return null;
        }

        var dogEntity = dogEntityOptional.get();
        dogEntity.setName(request.getName());
        dogEntity.setBirthDate(request.getBirthDate());
        dogEntity.setBreed(request.getBreed());
        var owner = userRepository.findById(request.getOwnerId()).orElseThrow();
        dogEntity.setOwner(owner);
        dogEntity = dogRepository.save(dogEntity);

        return dogTransformer.transformEntity(dogEntity);
    }
    @Transactional
    public boolean deleteById(Long id) {
        if (!dogRepository.existsById(id)) {
            return false;
        }
        cycleRepository.deleteByDogId(id);
        healthRecordRepository.deleteByDogId(id);
        dogRepository.deleteById(id);
        return true;
    }


    public List<Dog> findByUserId(Long userId) {
        List<FemaleDogEntity> dogs = dogRepository.findByOwnerId(userId);
        return dogs.stream()
                .map(dogTransformer::transformEntity)
                .collect(Collectors.toList());
    }
}

