package com.marcapo.exercise.springbootstartup.dao.impl;

import com.marcapo.exercise.springbootstartup.dao.UserDao;
import com.marcapo.exercise.springbootstartup.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    // Der MongoTemplate wird von Spring automatisch injiziert, um die Interaktionen mit MongoDB zu handhaben.
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        // Speichert den Benutzer in der MongoDB-Datenbank.
        mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        // Gibt alle Benutzer aus der Datenbank zurück.
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public void updatePasswordByUsername(User user) {
        // Erstellt eine Abfrage, um den Benutzer anhand des Benutzernamens zu finden.
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        // Aktualisiert das Passwort des gefundenen Benutzers.
        Update update = new Update().set("password", user.getPassword());
        // Führt das Update in der Datenbank aus.
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public void deleteAll() {
        // Löscht alle Benutzer aus der Datenbank.
        mongoTemplate.remove(new Query(), User.class);
    }

    // Benutzer anhand des Benutzernamens finden
    @Override
    public User findByUsername(String username) {
        // Erstellt eine Abfrage, um den Benutzer anhand des Benutzernamens zu suchen.
        Query query = new Query(Criteria.where("username").is(username));
        // Gibt den gefundenen Benutzer zurück.
        return mongoTemplate.findOne(query, User.class);
    }

    // Benutzer anhand des Benutzernamens löschen
    @Override
    public void deleteByUsername(String username) {
        // Löscht den Benutzer anhand des Benutzernamens.
        mongoTemplate.remove(new Query(Criteria.where("username").is(username)), User.class);
    }
}
