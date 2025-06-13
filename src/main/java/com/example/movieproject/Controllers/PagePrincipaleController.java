package com.example.movieproject.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class PagePrincipaleController {

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableView<Movie> addMovies_tableView;

    @FXML
    private TableColumn<Movie, String> addMovies_col_movieTitle;

    @FXML
    private TableColumn<Movie, String> addMovies_col_genre;

    @FXML
    private TableColumn<Movie, String> addMovies_col_duration;

    @FXML
    private TableColumn<Movie, String> addMovies_col_date;


    @FXML
    private TextField addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_importBtn;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;


    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> availableMovies_col_date;

    @FXML
    private TableColumn<?, ?> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<?> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<?> availableMovies_specialClass_quantity;

    @FXML
    private TableView<?> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_genre;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private Label customers_movieGenre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private AnchorPane cutomers_form;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTIcket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<?, ?> editScreening_col_current;

    @FXML
    private TableColumn<?, ?> editScreening_col_duration;

    @FXML
    private TableColumn<?, ?> editScreening_col_genre;

    @FXML
    private TableColumn<?, ?> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<?> editScreening_tableView;

    @FXML
    private Button editScreening_update;

    @FXML
    private Label editSreening_title;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private Button signout;

    @FXML
    private Label title_lbl;

    @FXML
    private Label username;


    @FXML
    public void initialize() {
        // Navigation entre les sections
        dashboard_btn.setOnAction(event -> navigateToSection("dashboard"));
        addMovies_btn.setOnAction(event -> navigateToSection("addMovie"));
        availableMovies_btn.setOnAction(event -> navigateToSection("availableMovies"));
        editScreening_btn.setOnAction(event -> navigateToSection("editScreening"));
        customers_btn.setOnAction(event -> navigateToSection("customers"));

        if (signout != null) {
            signout.setOnAction(event -> handleSignOut());
        } else {
            System.out.println("Signout button is not initialized");
        }

        // Par défaut : affichage du dashboard
        navigateToSection("dashboard");

        // Fonctionnalités pour les boutons d'ajout, suppression et mise à jour de films
        addMovies_insertBtn.setOnAction(event -> {
            try {
                // Vérification que tous les champs nécessaires sont remplis
                if (addMovies_movieTitle.getText().isEmpty() ||
                        addMovies_genre.getText().isEmpty() ||
                        addMovies_duration.getText().isEmpty() ||
                        addMovies_date.getText().isEmpty() ||
                        addMovies_imageView.getImage() == null) {

                    System.out.println("Tous les champs doivent être remplis !");
                    return;
                }

                // Ajout du film avec le chemin de l'image
                Movie.insertMovie(
                        addMovies_movieTitle.getText(),
                        addMovies_genre.getText(),
                        addMovies_duration.getText(),
                        java.sql.Date.valueOf(addMovies_date.getText()), // Assurez-vous que la date soit au bon format "yyyy-MM-dd"
                        addMovies_imageView.getImage().getUrl() // Récupération du chemin de l'image
                );
                System.out.println("Film inséré avec succès !");
            } catch (Exception e) {
                System.out.println("Erreur lors de l'insertion : " + e.getMessage());
            }
            if (addMovies_imageView.getImage() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Aucune image sélectionnée !");
                alert.showAndWait();
            }
        });

        addMovies_deleteBtn.setOnAction(event -> {
            try {
                int idToDelete = Integer.parseInt(addMovies_search.getText());
                Movie.deleteMovie(idToDelete);
                System.out.println("Film supprimé avec succès !");
            } catch (Exception e) {
                System.out.println("Erreur lors de la suppression : " + e.getMessage());
            }
        });

        addMovies_updateBtn.setOnAction(event -> {
            try {
                // Vérification que tous les champs nécessaires sont remplis
                if (addMovies_movieTitle.getText().isEmpty() ||
                        addMovies_genre.getText().isEmpty() ||
                        addMovies_duration.getText().isEmpty() ||
                        addMovies_date.getText().isEmpty() ||
                        addMovies_imageView.getImage() == null) {

                    System.out.println("Tous les champs doivent être remplis !");
                    return;
                }

                // Mise à jour du film avec le chemin de l'image
                Movie.updateMovie(
                        Integer.parseInt(addMovies_search.getText()), // ID du film
                        addMovies_movieTitle.getText(),
                        addMovies_genre.getText(),
                        addMovies_duration.getText(),
                        java.sql.Date.valueOf(addMovies_date.getText()), // Format "yyyy-MM-dd"
                        addMovies_imageView.getImage().getUrl() // Chemin de l'image
                );
                System.out.println("Film mis à jour avec succès !");
            } catch (Exception e) {
                System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            }
        });

        // Configuration des colonnes
        addMovies_col_movieTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        addMovies_col_genre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenre()));
        addMovies_col_duration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration()));
        addMovies_col_date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublishedDate().toString()));

        // Charger les films dans la TableView
        loadMovies();

        // Écouteur pour le double-clic sur une ligne
        addMovies_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Vérifie si c'est un double-clic
                Movie selectedMovie = addMovies_tableView.getSelectionModel().getSelectedItem();
                if (selectedMovie != null) {
                    populateFields(selectedMovie); // Remplir les champs avec les données sélectionnées
                }
            }
        });
        addMovies_importBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    addMovies_imageView.setImage(new Image(selectedFile.toURI().toString()));
                } catch (Exception e) {
                    System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
                }
            } else {
                System.out.println("Aucun fichier sélectionné.");
            }

        });





        // Actions pour les boutons
            addMovies_insertBtn.setOnAction(event -> handleInsertMovie());
            addMovies_deleteBtn.setOnAction(event -> handleDeleteMovie());
            addMovies_updateBtn.setOnAction(event -> handleUpdateMovie());
            addMovies_clearBtn.setOnAction(event -> handleClearFields());
            addMovies_search.setOnAction(event -> handleSearchMovie());

            addMovies_importBtn.setOnAction(event -> handleImportImage());



    }
    private void loadMovies() {
        // Récupère tous les films de la base de données et les affiche dans le tableau
        addMovies_tableView.getItems().setAll(Movie.getAllMovies());
    }


    private void handleSignOut() {
        Stage stage = (Stage) signout.getScene().getWindow();
        stage.close();
    }



    // Méthode de navigation
    private void navigateToSection(String section) {
        // On cache toutes les sections
        hideAllSections();

        // On affiche la section correspondant au bouton cliqué
        switch (section) {
            case "dashboard":
                dashboard_form.setVisible(true);
                break;
            case "addMovie":
                addMovies_form.setVisible(true);
                break;
            case "availableMovies":
                availableMovies_form.setVisible(true);
                break;
            case "editScreening":
                editScreening_form.setVisible(true);
                break;
            case "customers":
                cutomers_form.setVisible(true);
                break;
        }
    }

    // Méthode pour cacher toutes les sections
    private void hideAllSections() {
        dashboard_form.setVisible(false);
        addMovies_form.setVisible(false);
        availableMovies_form.setVisible(false);
        editScreening_form.setVisible(false);
        cutomers_form.setVisible(false);
    }

    // Méthode de déconnexion
    private void signOut() {
        // Logique de déconnexion, redirection vers une autre page ou fermeture de l'application
        System.out.println("Déconnexion...");
    }

    @FXML
    private void handleDeleteMovie() {
        Movie selectedMovie = addMovies_tableView.getSelectionModel().getSelectedItem();

        if (selectedMovie == null) {
            // Alerte si aucun film n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un film à supprimer.");
            alert.showAndWait();
            return;
        }

        // Suppression du film
        Movie.deleteMovie(selectedMovie.getId());
        loadMovies(); // Recharger le tableau
        System.out.println("Film supprimé avec succès !");
    }
    @FXML
    private void handleUpdateMovie() {
        Movie selectedMovie = addMovies_tableView.getSelectionModel().getSelectedItem();

        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un film à mettre à jour.");
            alert.showAndWait();
            return;
        }

        try {
            String title = addMovies_movieTitle.getText();
            String genre = addMovies_genre.getText();
            String duration = addMovies_duration.getText();
            java.sql.Date date = java.sql.Date.valueOf(addMovies_date.getText());
            String imagePath = addMovies_imageView.getImage() != null
                    ? addMovies_imageView.getImage().getUrl()
                    : "";

            Movie.updateMovie(selectedMovie.getId(), title, genre, duration, date, imagePath);
            loadMovies();
            System.out.println("Film mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    @FXML
    private void handleInsertMovie() {
        try {
            String title = addMovies_movieTitle.getText();
            String genre = addMovies_genre.getText();
            String duration = addMovies_duration.getText();
            java.sql.Date date = java.sql.Date.valueOf(addMovies_date.getText());
            String imagePath = addMovies_imageView.getImage() != null
                    ? addMovies_imageView.getImage().getUrl()
                    : "";

            if (title.isEmpty() || genre.isEmpty() || duration.isEmpty() || imagePath.isEmpty()) {
                System.out.println("Veuillez remplir tous les champs !");
                return;
            }

            Movie.insertMovie(title, genre, duration, date, imagePath);
            loadMovies(); // Recharger les films après l'insertion
            System.out.println("Film inséré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }


    @FXML
    private void handleClearFields() {
        // Réinitialise les champs de texte
        addMovies_movieTitle.clear();
        addMovies_genre.clear();
        addMovies_duration.clear();
        addMovies_date.clear();
        addMovies_imageView.setImage(null);
        System.out.println("Champs réinitialisés !");
    }

    private void populateFields(Movie movie) {
        // Remplit les champs avec les données du film sélectionné
        addMovies_movieTitle.setText(movie.getTitle());
        addMovies_genre.setText(movie.getGenre());
        addMovies_duration.setText(movie.getDuration());
        addMovies_date.setText(movie.getPublishedDate().toString());

        // Chargement de l'image du film
        if (movie.getImagePath() != null && !movie.getImagePath().isEmpty()) {
            addMovies_imageView.setImage(new Image(movie.getImagePath()));
        } else {
            addMovies_imageView.setImage(null); // Si aucune image n'est définie
        }
    }

    @FXML
    private void handleSearchMovie() {
        String searchText = addMovies_search.getText().trim();

        if (searchText.isEmpty()) {
            // Message d'alerte si le champ de recherche est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Recherche");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un titre de film pour la recherche.");
            alert.showAndWait();
            return;
        }

        Movie movie = Movie.getMovieByTitle(searchText);

        if (movie != null) {
            // Remplit les champs texte avec les données du film trouvé
            populateFields(movie);
            System.out.println("Film trouvé : " + movie.getTitle());
        } else {
            // Message si aucun film n'est trouvé
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Résultat de la recherche");
            alert.setHeaderText(null);
            alert.setContentText("Aucun film trouvé avec ce titre.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleImportImage() {
        // Configurer un FileChooser pour sélectionner des fichiers image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Ouvrir la boîte de dialogue de sélection
        File selectedFile = fileChooser.showOpenDialog(addMovies_importBtn.getScene().getWindow());

        if (selectedFile != null) {
            // Charger l'image sélectionnée
            Image image = new Image(selectedFile.toURI().toString());
            addMovies_imageView.setImage(image);

            // Optionnel : afficher le chemin dans la console ou enregistrer dans la base de données
            System.out.println("Image importée : " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Aucun fichier sélectionné.");
        }
    }

}
