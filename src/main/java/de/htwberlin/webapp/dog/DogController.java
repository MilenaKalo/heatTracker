package de.htwberlin.webapp.dog;

import de.htwberlin.webapp.persistence.FemaleDogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class DogController {

    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping(path = "/api/v1/dogs")
    public ResponseEntity<List<Dog>> getDogs() {
        List<Dog> dogs = dogService.findAll();
        return ResponseEntity.ok(dogs);
    }
    @GetMapping(path = "/api/v1/users/{userId}/dogs")
    public ResponseEntity<List<Dog>> getDogsByUser(@PathVariable Long userId) {
        // Annahme: Hier verwendest du einen Service, um die Hunde für einen bestimmten Benutzer abzurufen.
        List<Dog> dogs = dogService.findByUserId(userId);
        return ResponseEntity.ok(dogs);
    }

    @GetMapping(path = "/api/v1/dogs/{id}")
    public ResponseEntity<Dog> fetchDogById(@PathVariable Long id) {
        var dog = dogService.findById(id);
        return dog != null? ResponseEntity.ok(dog) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/dogs")
    public ResponseEntity<Void> createDog(@RequestBody DogManipulationRequest request) throws URISyntaxException {
        var dog = dogService.create(request);
        URI uri = new URI("/api/v1/dogs/" + dog.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/dogs/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody DogManipulationRequest request) {
        var dog = dogService.update(id, request);
        return dog != null? ResponseEntity.ok(dog) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/dogs/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        boolean successful = dogService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
