package de.htwberlin.webapp.health;



import de.htwberlin.webapp.dog.DogTransformer;
import de.htwberlin.webapp.persistence.HealthRecordEntity;
import de.htwberlin.webapp.repository.DogRepository;
import de.htwberlin.webapp.repository.HealthRecordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;

    private final DogRepository dogRepository;

    private final DogTransformer dogTransformer;

    public HealthRecordService(HealthRecordRepository healthRecordRepository, DogRepository dogRepository, DogTransformer dogTransformer) {
        this.healthRecordRepository = healthRecordRepository;
        this.dogRepository = dogRepository;
        this.dogTransformer = dogTransformer;
    }

    public List<HealthRecord> findAll() {
        List<HealthRecordEntity> healthrecord = healthRecordRepository.findAll();
        return healthrecord.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public HealthRecord findById(Long id) {
        var recordEntityOptional = healthRecordRepository.findById(id);
        return recordEntityOptional.map(this::transformEntity).orElse(null);
    }

    public HealthRecord create(HealthRecordManipulationRequest request) {
        var dog = dogRepository.findById(request.getDogId()).orElseThrow();
        var healthRecordEntity = new HealthRecordEntity(request.getStartDate(),request.getEndDate(), request.getSymptom(), request.getNote(), dog);
        healthRecordEntity = healthRecordRepository.save(healthRecordEntity);
        return transformEntity(healthRecordEntity);
    }


    public HealthRecord update(Long id, HealthRecordManipulationRequest request) {
        var healthRecordEntityOptional = healthRecordRepository.findById(id);
        if (healthRecordEntityOptional.isEmpty()) {
            return null;
        }

        var healthRecordEntity = healthRecordEntityOptional.get();
        healthRecordEntity.setEndDate(request.getEndDate());
        healthRecordEntity.setStartDate(request.getStartDate());
        healthRecordEntity.setSymptom(request.getSymptom());
        healthRecordEntity.setNote (request.getNote());
        healthRecordEntity.setDog(dogRepository.findById(request.getDogId()).orElseThrow());
        healthRecordEntity = healthRecordRepository.save(healthRecordEntity);

        return transformEntity(healthRecordEntity);
    }

    public boolean deleteById(Long id) {
        if (!healthRecordRepository.existsById(id)) {
            return false;
        }

        healthRecordRepository.deleteById(id);
        return true;
    }

    private HealthRecord transformEntity(HealthRecordEntity healthRecordEntity) {

        return new HealthRecord(
                healthRecordEntity.getId(),
                healthRecordEntity.getStartDate(),
                healthRecordEntity.getSymptom(),
                healthRecordEntity.getNote(),
                healthRecordEntity.getEndDate(),
                dogTransformer.transformEntity(healthRecordEntity.getDog())
        );
    }

    public List<HealthRecord> findByDogId(Long dogId) {
        List<HealthRecordEntity> healthrecord = healthRecordRepository.findByDogId(dogId);
        return healthrecord.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }
}
