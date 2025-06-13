package com.example.movieproject.Models;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/movie_project";
        String user = "root";
        String password = ""; // Ajoute ton mot de passe ici si nécessaire

        try {
            // Charger le driver (non strictement nécessaire en JDBC 4+)
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
