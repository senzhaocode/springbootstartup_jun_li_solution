package com.marcapo.exercise.springbootstartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcapo.exercise.springbootstartup.entity.User;
import com.marcapo.exercise.springbootstartup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> users(@RequestBody String userJson) {
        try {
            // JSON in User-Objekt umwandeln
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userJson, User.class);
            userService.saveUser(user);
        } catch (Exception e) {
            // Rückgabe eines 400-Fehlers im Falle eines Fehlers
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        // Erfolgreiche Rückgabe mit Status 200
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/users")
    public List<User> findAll() {
        // Gibt eine Liste aller Benutzer zurück
        return userService.findAll();
    }

    //Passwort nach Benutzername aktualisieren
    @PutMapping("/users")
    public String update(@RequestBody User user) {
        // Aktualisiert das Passwort anhand des Benutzernamens
        userService.updatePasswordByUsername(user);
        return "success";
    }

    @DeleteMapping("/users")
    public String deleteAll() {
        // Löscht alle Benutzer
        userService.deleteAll();
        return "success";
    }

   //Benutzer anhand des Benutzernamens finden
    @GetMapping("/users/search/findByUsername")
    public User findByUsername(String username) {
        // Gibt den Benutzer mit dem entsprechenden Benutzernamen zurück
        return userService.findByUsername(username);
    }

     //Benutzer anhand des Benutzernamens löschen
    @GetMapping("/users/search/deleteByUsername")
    public String deleteByUsername(String username) {
        // Löscht den Benutzer mit dem entsprechenden Benutzernamen
        userService.deleteByUsername(username);
        return "success";
    }
}
