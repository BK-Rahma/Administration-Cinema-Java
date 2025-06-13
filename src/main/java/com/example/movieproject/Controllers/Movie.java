package com.example.movieproject.Controllers;

import com.example.movieproject.Models.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    // Attributs de la classe Movie
    private int id;
    private String title;
    private String genre;
    private String duration;
    private Date publishedDate;
    private String imagePath; // Chemin de l'image

    // Constructeur par défaut
    public Movie() {}

    // Constructeur avec paramètres
    public Movie(int id, String title, String genre, String duration, Date publishedDate, String imagePath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.publishedDate = publishedDate;
        this.imagePath = imagePath;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Méthode pour insérer un film dans la base de données
    public static void insertMovie(String title, String genre, String duration, Date publishedDate, String imagePath) {
        String query = "INSERT INTO movies (movie_title, genre, duration, published_date, image_path) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, duration);
            ps.setDate(4, publishedDate);
            ps.setString(5, imagePath);

            ps.executeUpdate();
            System.out.println("Film avec image inséré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur d'insertion : " + e.getMessage());
        }
    }

    // Méthode pour supprimer un film par ID
    public static void deleteMovie(int id) {
        String query = "DELETE FROM movies WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Film supprimé avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec cet ID !");
            }
        } catch (Exception e) {
            System.out.println("Erreur de suppression : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour un film
    public static void updateMovie(int id, String title, String genre, String duration, Date publishedDate, String imagePath) {
        String query = "UPDATE movies SET movie_title = ?, genre = ?, duration = ?, published_date = ?, image_path = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, duration);
            ps.setDate(4, publishedDate);
            ps.setString(5, imagePath);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Film mis à jour avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec cet ID !");
            }
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : " + e.getMessage());
        }
    }

    // Méthode pour récupérer tous les films dans une liste
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("movie_title"),
                        rs.getString("genre"),
                        rs.getString("duration"),
                        rs.getDate("published_date"),
                        rs.getString("image_path") // Récupérer le chemin de l'image
                );
                movies.add(movie);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des films : " + e.getMessage());
        }
        return movies;
    }

    // Méthode pour afficher les films dans la console (test)
    public static void displayMovies() {
        List<Movie> movies = getAllMovies();
        for (Movie movie : movies) {
            System.out.println("ID: " + movie.getId() +
                    ", Title: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Duration: " + movie.getDuration() +
                    ", Published Date: " + movie.getPublishedDate() +
                    ", Image Path: " + movie.getImagePath());
        }
    }

    public static Movie getMovieByTitle(String title) {
        String query = "SELECT * FROM movies WHERE movie_title = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                            rs.getInt("id"),
                            rs.getString("movie_title"),
                            rs.getString("genre"),
                            rs.getString("duration"),
                            rs.getDate("published_date"),
                            rs.getString("image_path") // Récupérer le chemin de l'image
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
        return null; // Retourne null si aucun film n'est trouvé
    }
}
