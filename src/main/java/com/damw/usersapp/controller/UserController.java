package com.damw.usersapp.controller;

import com.damw.usersapp.model.User;
import com.damw.usersapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.NoSuchElementException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*") // permite llamadas desde cualquier dominio
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("Petición GET /api/v1/users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Optional<User> es un contenedor que puede contener un User o estar vacío,
    // evitando null y facilitando el manejo de ausencia de datos.
    // @GetMapping("/{id}")
    // public User getUser(@PathVariable Long id) {
    // return userService.getUserById(id)
    // .orElse(null);
    // }

    // @GetMapping("/{id}")
    // public User getUser(@PathVariable Long id) {
    // return userService.getUserById(id)
    // .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id "
    // + id));
    // }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        // userService.getUserById(id) devuelve un Optional<User>, que:
        // - Contiene el usuario si existe en BD (Optional con valor)
        // - Está vacío si no lo encuentra (Optional empty)
        // map(ResponseEntity::ok) transforma el Optional<User> en
        // Optional<ResponseEntity<User>>,
        // envolviendo el usuario en 200 OK si está presente.
        // orElse(ResponseEntity.notFound().build()) devuelve un 404 Not Found si el
        // Optional está vacío.
        log.info("Petición GET /api/v1/users/{}", id);
        return userService.getUserById(id)
                .map(ResponseEntity::ok) // si está, 200 + user
                .orElse(ResponseEntity.notFound().build()); // si no, 404
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Petición PUT /api/v1/users/{}", id);
        try {
            User updated = userService.update(id, user);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Petición PATCH /api/v1/users/{}", id);
        try {
            User patched = userService.patchUpdate(id, updates);
            return ResponseEntity.ok(patched);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Petición DELETE /api/v1/users/{}", id);
        if (userService.getUserById(id).isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
