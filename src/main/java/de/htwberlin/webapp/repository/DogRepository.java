package de.htwberlin.webapp.repository;

import de.htwberlin.webapp.persistence.FemaleDogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<FemaleDogEntity, Long> {

    List<FemaleDogEntity> findByOwnerId(Long userId);

}
