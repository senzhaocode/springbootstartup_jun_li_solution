package com.marcapo.exercise.springbootstartup.entity;

import org.springframework.data.mongodb.core.mapping.Document;

// Dokumente in der MongoDB-Sammlung "user"
@Document(collection = "user")
public class User {
    private String username;
    private String password;

    // Getter für den Benutzernamen
    public String getUsername() {
        return username;
    }

    // Setter für den Benutzernamen
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter für das Passwort
    public String getPassword() {
        return password;
    }

    // Setter für das Passwort
    public void setPassword(String password) {
        this.password = password;
    }

    // Überschreiben der toString-Methode für die Benutzerobjekte
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
