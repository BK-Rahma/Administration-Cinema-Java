package com.example.movieproject.Controllers;

import com.example.movieproject.Models.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField email_fld;

    @FXML
    private Label email_lbl;

    @FXML
    private Label error_lbl;

    @FXML
    private TextField name_fld;

    @FXML
    private Label name_lbl;

    @FXML
    private PasswordField password_fld;

    @FXML
    private Label password_lbl;

    @FXML
    private Button submit_btn;

    @FXML
    public void initialize() {
        // Écouteur pour le bouton Submit
        submit_btn.setOnAction(event -> registerUser());
    }

    private void registerUser() {
        String name = name_fld.getText();
        String email = email_fld.getText();
        String password = password_fld.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            error_lbl.setText("Veuillez remplir tous les champs.");
            return;
        }

        // Requête SQL pour insérer un nouvel utilisateur
        String query = "INSERT INTO utilisateurs (nom, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password); // Note : Plus tard, hache le mot de passe avec BCrypt

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                error_lbl.setStyle("-fx-text-fill: green;");
                error_lbl.setText("Inscription réussie !");
                clearFields();

                // Naviguer vers la page principale après l'inscription réussie
                navigateToPage();
            } else {
                error_lbl.setStyle("-fx-text-fill: red;");
                error_lbl.setText("Échec de l'inscription.");
            }
        } catch (Exception e) {
            error_lbl.setStyle("-fx-text-fill: red;");
            error_lbl.setText("Erreur : " + e.getMessage());
        }
    }

    private void clearFields() {
        name_fld.clear();
        email_fld.clear();
        password_fld.clear();
    }

    // Méthode pour naviguer vers la page principale
    private void navigateToPage() {
        try {
            // Charger la scène de la page principale (par exemple "PagePrincipale.fxml")
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/pageprincipale.fxml"));
            Scene mainScene = new Scene(loader.load());

            // Obtenir la fenêtre (Stage) actuelle et changer la scène
            Stage stage = (Stage) submit_btn.getScene().getWindow();
            stage.setScene(mainScene);
            stage.show();
        } catch (IOException e) {
            error_lbl.setStyle("-fx-text-fill: red;");
            error_lbl.setText("Erreur de navigation vers la page principale.");
        }
    }
}
