package de.htwberlin.webapp.repository;

import de.htwberlin.webapp.persistence.CycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CycleRepository extends JpaRepository<CycleEntity, Long> {

    void deleteByDogId(Long id);

    List<CycleEntity> findAllByDogId(Long dogId);
}
