package org.example.tareatema5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.sound.sampled.AudioFileFormat;
import java.applet.AudioClip;
import java.io.*;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

public class MainController {

    @FXML
    private Button botonArtistas;

    @FXML
    private Button botonCerrar;

    @FXML
    private Button botonClientes;

    //En vez de salir directamente le he puesto un sonido, y para no tener que hacer hilos porque al sonido no le da tiempo a reproducirse simplemente hago un Alert y así suena
    public void cerrarAplicacion(ActionEvent actionEvent) throws InterruptedException {
        String sonidosalir = "src/main/resources/org/example/tareatema5/sounds/aww-8277.mp3";
        javafx.scene.media.Media sound = new Media(new File(sonidosalir).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("Vas a salir de la aplicación");
        a.showAndWait();
        System.out.println("Saliendo de la aplicación");
        System.exit(0);
    }

    public void generarClientes(ActionEvent actionEvent){
        String sonido = "src/main/resources/org/example/tareatema5/sounds/applepay.mp3";
        javafx.scene.media.Media sound = new Media(new File(sonido).toURI().toString());
        //He usado la librería de MediaPlayer para probarla y he añadido algunos sonidos
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        String urlcon="jdbc:sqlite:E:/Descargas Chrome/chinook.db";
        JasperPrint jasperPrint;
        Connection conn;

        try {
                //Con esto lanzo el informe
            conn = DriverManager.getConnection(urlcon);

            String fichero="informes/PiratasInformaticos.jasper";
            jasperPrint = JasperFillManager.fillReport(fichero, new HashMap<>(),conn);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Informe lanzado");

    }

    //Abrir el fxml de los Artistas
    public void escenaArtistas (ActionEvent actionEvent) throws IOException {

        String sonido = "src/main/resources/org/example/tareatema5/sounds/tick-tock-ahh-sound.mp3";
        javafx.scene.media.Media sound = new Media(new File(sonido).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        FXMLLoader fxmlloader = new FXMLLoader(MainController.class.getResource("artistas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlloader.load(),600,400);
        stage.setScene(scene);
        stage.setTitle("Artistas");
        stage.getIcons().add(new Image("https://cdn-icons-png.freepik.com/512/5662/5662059.png"));
        stage.setResizable(false);
        stage.show();
    }
}
