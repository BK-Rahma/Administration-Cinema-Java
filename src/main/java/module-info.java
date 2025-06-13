module com.example.movieproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires fontawesomefx;
    requires java.sql;

    opens com.example.movieproject to javafx.fxml;
    opens com.example.movieproject.Controllers to javafx.fxml;

    exports com.example.movieproject;
    exports com.example.movieproject.Controllers;
    exports com.example.movieproject.Views;
    exports com.example.movieproject.Models;
}