package com.marcapo.exercise.springbootstartup.dao;

import com.marcapo.exercise.springbootstartup.entity.User;

import java.util.List;

public interface UserDao {

    // Benutzer speichern
    void saveUser(User user);

    // Ruft alle Benutzer aus der Datenbank ab.
    List<User> findAll();

    // Passwort des Benutzers nach Benutzername aktualisieren
    void updatePasswordByUsername(User user);

    //Löscht alle Benutzer aus der Datenbank.
    void deleteAll();

    // Benutzer anhand des Benutzernamens finden
    User findByUsername(String username);

    // Benutzer anhand des Benutzernamens löschen
    void deleteByUsername(String username);

}
