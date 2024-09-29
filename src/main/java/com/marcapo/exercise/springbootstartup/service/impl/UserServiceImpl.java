package com.marcapo.exercise.springbootstartup.service.impl;

import com.marcapo.exercise.springbootstartup.dao.UserDao;
import com.marcapo.exercise.springbootstartup.entity.User;
import com.marcapo.exercise.springbootstartup.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // Logger für das Protokollieren von Nachrichten
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        // Nach Benutzername suchen, wenn der Benutzer existiert, eine Ausnahme werfen, sonst den Benutzer speichern
        User queryUser = this.findByUsername(user.getUsername());
        if (queryUser != null) {
            throw new RuntimeException("Der Benutzername existiert bereits");
        }
        // Passwort mit MD5 verschlüsseln
        user.setPassword(encodeMD5(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        // Ruft alle Benutzer aus der Datenbank ab
        return userDao.findAll();
    }

    @Override
    public void updatePasswordByUsername(User user) {
        User queryUser = this.findByUsername(user.getUsername());
        if (queryUser == null) {
            // Fehler, wenn der Benutzer nicht gefunden wird
            throw new RuntimeException("Benutzer existiert nicht!");
        }
        // Passwort mit MD5 verschlüsseln
        user.setPassword(encodeMD5(user.getPassword()));
        userDao.updatePasswordByUsername(user);
    }

    @Override
    public void deleteAll() {
        // Löscht alle Benutzer aus der Datenbank
        userDao.deleteAll();
    }

    @Override
    public User findByUsername(String username) {
        // Ruft den Benutzer anhand des Benutzernamens ab
        return userDao.findByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        // Löscht den Benutzer anhand des Benutzernamens aus der Datenbank
        userDao.deleteByUsername(username);
    }

    // Verschlüsselt eine Zeichenkette mit dem MD5-Algorithmus.
    private String encodeMD5(final String value) {
        try {
            if (value != null) {
                // MD5-Algorithmusinstanz abrufen
                MessageDigest md = MessageDigest.getInstance("MD5");
                // Eingabezeichenfolge in Byte-Array umwandeln
                byte[] bytesOfMessage = value.getBytes(StandardCharsets.UTF_8);
                // MD5-Hash-Berechnung durchführen
                byte[] digest = md.digest(bytesOfMessage);
                return MD5Encoder.encode(digest);
            }
        } catch (NoSuchAlgorithmException e) {
            // Protokolliert den Fehler, wenn der MD5-Algorithmus nicht gefunden wird
            this.log.error(e.getMessage(), e);
        }
        return null;
    }
}
