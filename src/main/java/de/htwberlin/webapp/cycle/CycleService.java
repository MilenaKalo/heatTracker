package de.htwberlin.webapp.cycle;

import de.htwberlin.webapp.dog.DogTransformer;
import de.htwberlin.webapp.persistence.CycleEntity;
import de.htwberlin.webapp.repository.CycleRepository;
import de.htwberlin.webapp.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//der Service soll hier die BusinessLogik ausführen
//und die Daten aus der Datenbank holen
//dafür brauchen wir den Repository
//und den CycleService
//der CycleService soll die Daten aus der Datenbank holen
//und in ein Cycle-Objekt umwandeln
@Service
public class CycleService {

    private final CycleRepository cycleRepository;
    private final DogRepository dogRepository;

    private final DogTransformer dogTransformer;

    public CycleService(CycleRepository cycleRepository, DogRepository dogRepository, DogTransformer dogTransformer) {
        this.cycleRepository = cycleRepository;
        this.dogRepository = dogRepository;
        this.dogTransformer = dogTransformer;
    }


    public List<Cycle> findAll() {
        List<CycleEntity> cycles = cycleRepository.findAll();
        return cycles.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Cycle findById(Long id) {
        var cycleEntityOptional = cycleRepository.findById(id);
        return cycleEntityOptional.map(this::transformEntity).orElse(null);
    }

    public Cycle create(CycleManipulationRequest request) {
        var dog = dogRepository.findById(request.getDogId()).orElseThrow();
        var cycleEntity = new CycleEntity(request.getStartdate(), request.getEnddate(),request.isSilentHeat(), request.isSplitHeat(), dog);
        cycleEntity = cycleRepository.save(cycleEntity);
        return transformEntity(cycleEntity);

    }

    public Cycle update(Long id, CycleManipulationRequest request) {
        var cycleEntityOptional = cycleRepository.findById(id);
        if (cycleEntityOptional.isEmpty()) {
            return null;
        }

        var cycleEntity = cycleEntityOptional.get();
        cycleEntity.setStartdate(request.getStartdate());
        cycleEntity.setEnddate(request.getEnddate());
        cycleEntity.setSilentHeat(request.isSilentHeat());
        cycleEntity.setSplitHeat(request.isSplitHeat());
        cycleEntity.setDog(dogRepository.findById(request.getDogId()).orElseThrow());
        cycleEntity = cycleRepository.save(cycleEntity);

        return transformEntity(cycleEntity);
    }

    public boolean deleteById(Long id) {
        if (!cycleRepository.existsById(id)) {
            return false;
        }

        cycleRepository.deleteById(id);
        return true;
    }

    private Cycle transformEntity(CycleEntity cycleEntity) {
        return new Cycle(
                cycleEntity.getId(),
                cycleEntity.getStartdate(),
                cycleEntity.getEnddate(),
                cycleEntity.isSilentHeat(),
                cycleEntity.isSplitHeat(),
                dogTransformer.transformEntity(cycleEntity.getDog())
        );
    }

    public List<Cycle> findByDogId(Long dogId) {
        List<CycleEntity> cycles = cycleRepository.findAllByDogId(dogId);
        return cycles.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }
}
