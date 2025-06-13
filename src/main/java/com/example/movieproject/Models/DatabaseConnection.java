package com.example.movieproject.Models;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/movie_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver MySQL
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return conn;
    }
}
