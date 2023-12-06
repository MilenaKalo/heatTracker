package de.htwberlin.webapp.repository;

import de.htwberlin.webapp.persistence.CycleEntity;
import de.htwberlin.webapp.persistence.HealthRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthRecordRepository  extends JpaRepository<HealthRecordEntity, Long> {

    void deleteByDogId(Long id);
    List<HealthRecordEntity> findByDogId(Long dogId);
}
