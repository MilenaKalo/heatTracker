package de.htwberlin.webapp.login;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/v1/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserManipulationRequest request) throws URISyntaxException {
        var user= userService.create(request);
        URI uri = new URI("/api/v1/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserManipulationRequest request) {
        var user = userService.update(id, request);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean successful = userService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/users/login")
    public ResponseEntity<User> login(@RequestBody UserManipulationRequest request) {
        // Hier sollten Sie den Benutzer anhand der Anmeldeinformationen überprüfen
        // Wenn die Anmeldung erfolgreich ist, geben Sie den Benutzer zurück
        String username = request.getUsername(); // Der Benutzername aus der Anfrage
        String password = request.getPassword(); // Das Passwort aus der Anfrage
        // Führen Sie die Anmeldeüberprüfung durch, z. B. in Ihrem UserService
        User authenticatedUser = userService.authenticate(username, password);
        if (authenticatedUser != null) {
            // Anmeldung erfolgreich
            return ResponseEntity.ok(authenticatedUser);
        } else {
            // Anmeldung fehlgeschlagen
            return ResponseEntity.notFound().build();
        }
    }
}

