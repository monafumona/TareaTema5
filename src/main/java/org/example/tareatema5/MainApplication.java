package org.example.tareatema5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { //Aqui no he cambiado nada
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(MainApplication.class.getResource("mainview.css").toExternalForm());
        stage.getIcons().add(new Image("https://cdn-icons-png.freepik.com/512/5662/5662059.png"));
        stage.setResizable(false);
        stage.setTitle("Aplicación formularios");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
