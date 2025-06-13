package com.example.movieproject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label Error_lbl;

    @FXML
    private Label account_lbl;

    @FXML
    private TextField address_fld;

    @FXML
    private Button login_btn;

    @FXML
    private Button signup_btn; // Nouveau bouton Sign Up

    @FXML
    private Label login_fld;

    @FXML
    private PasswordField password_fld;

    @FXML
    public void initialize() {
        // Écouteur pour le bouton Login
        login_btn.setOnAction(event -> navigateToPage("/FXML/pageprincipale.fxml", "Page Principale"));

        // Écouteur pour le bouton Sign Up
        signup_btn.setOnAction(event -> navigateToPage("/FXML/SignUp.fxml", "Sign Up"));
    }

    private void navigateToPage(String fxmlPath, String title) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Stage stage = (Stage) login_btn.getScene().getWindow(); // Récupère la fenêtre actuelle
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Error_lbl.setText("Failed to load page: " + e.getMessage());
        }
    }

}
